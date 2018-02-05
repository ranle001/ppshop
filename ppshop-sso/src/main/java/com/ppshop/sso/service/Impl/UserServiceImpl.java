package com.ppshop.sso.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.common.utils.CookieUtils;
import com.ppshop.common.utils.ExceptionUtil;
import com.ppshop.common.utils.JsonUtils;
import com.ppshop.mapper.TbUserMapper;
import com.ppshop.pojo.TbUser;
import com.ppshop.sso.service.JedisClient;
import com.ppshop.sso.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private TbUserMapper tbUserMapper;

	@Autowired
	private JedisClient jedisClientSingle;
	
	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;
	
	@Value("${SSO_SESSION_EXPIRE}")
	private String SSO_SESSION_EXPIRE;
	
	@Override
	public PpShopResult checkData(String content, Integer type) {
		List<TbUser> usList = null;
		if (1 == type) {//用户名校验
			usList = tbUserMapper.getUsersByUsername(content);
		} else if (2 == type) {//电话校验
			usList = tbUserMapper.getUsersByPhone(content);
		} else {//邮件校验
			usList = tbUserMapper.getUsersEmail(content);
		}
		if (usList == null || usList.size() == 0){
			return PpShopResult.ok(true);
		}
		return PpShopResult.ok(false);
	}

	@Override
	public PpShopResult createUser(TbUser tbUser) {
		tbUser.setCreated(new Date());
		tbUser.setUpdated(new Date());
		//md5加密
		tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
		tbUserMapper.insertUser(tbUser);
		return PpShopResult.ok();
	}

	@Override
	public PpShopResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
		List<TbUser> users = tbUserMapper.getUsersByUsername(username);
		if (null == users || users.size() == 0){
			return PpShopResult.build(400, "用户名或者密码错误");
		}
		TbUser user = users.get(0);
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())){
			return PpShopResult.build(400, "用户名或者密码错误");
		}
		//生成token
		String token = UUID.randomUUID().toString();
		//保存用户之前，把用户对象中的密码清空
		user.setPassword(null);
		//把用户信息写入redis
		jedisClientSingle.set(REDIS_USER_SESSION_KEY + ":" +token, JsonUtils.objectToJson(user));
		jedisClientSingle.expire(REDIS_USER_SESSION_KEY + ":" +token, Integer.parseInt(SSO_SESSION_EXPIRE));
		//写入cookie
		CookieUtils.setCookie(request, response, "PP_TOKEN", token);
		return PpShopResult.ok(token);
	}

	@Override
	public PpShopResult getUserByToken(String token) {
		//根据token从redis中查询用户信息
		String json = jedisClientSingle.get(REDIS_USER_SESSION_KEY + ":" + token);
		if (StringUtils.isBlank(json)) {
			return PpShopResult.build(400, "此session已经过期，请重新登录");
		}
		//更新过期时间
		jedisClientSingle.expire(REDIS_USER_SESSION_KEY + ":" + token, Integer.parseInt(SSO_SESSION_EXPIRE));
		//返回用户信息
		return PpShopResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
	}
	
	@Override
	public PpShopResult removeToken(String token) {
		//从redis中删除用户信息
		try {
			jedisClientSingle.del(REDIS_USER_SESSION_KEY + ":" + token);
			return PpShopResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return PpShopResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}
