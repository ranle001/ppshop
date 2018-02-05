package com.ppshop.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.common.utils.ExceptionUtil;
import com.ppshop.pojo.TbUser;
import com.ppshop.sso.service.UserService;
import com.ppshop.sso.utils.CookieUtils;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object checkData(@PathVariable String param,@PathVariable Integer type, String callback){
		PpShopResult result = null;
		//参数有效性教材
		if (StringUtils.isBlank(param)) {
			result = PpShopResult.build(400, "校验内容不能为空");
		}
		if (type == null) {
			result = PpShopResult.build(400, "检验内容类型不能为空");
		}
		if (type != 1 && type != 2 && type != 3) {
			result = PpShopResult.build(400, "检验内容类型错误");
		}
		//校验出错
		if (null != result) {
			if (null != callback) {
				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
				mappingJacksonValue.setJsonpFunction(callback);
				return mappingJacksonValue;
			} else {
				return result;
			}
		}
		//调用服务
		try {
			result = userService.checkData(param, type);
		} catch (Exception e) {
			result = PpShopResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		if (null != callback) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
		return result;
	}

	//创建用户
	@RequestMapping("/register")
	@ResponseBody
	public PpShopResult createUser(TbUser tbUser){
		try {
			PpShopResult result = userService.createUser(tbUser);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return PpShopResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	//用户登录
	@RequestMapping("/login")
	@ResponseBody
	public PpShopResult login(TbUser tbUser, HttpServletRequest request,
			HttpServletResponse response){
		try {
			PpShopResult result = userService.userLogin(tbUser.getUsername(), tbUser.getPassword());
			if (result.getStatus() == 200) {
				String token = (String) result.getData();
				//写入cookie
				CookieUtils.setCookie(request, response, "PP_TOKEN", token);
			}		
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return PpShopResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	@RequestMapping("/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable String token, String callback){
		PpShopResult result = null;
		try {
			result = userService.getUserByToken(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (StringUtils.isBlank(callback)) {
			return callback;
		} else {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			return mappingJacksonValue;
		}
	}
	
	@RequestMapping("/logOut/{token}")
	@ResponseBody
	public Object logOut(@PathVariable String token, String callback){
		PpShopResult result = userService.removeToken(token);
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
}
