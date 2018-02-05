package com.ppshop.portal.service.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.common.utils.ExceptionUtil;
import com.ppshop.common.utils.HttpClientUtil;
import com.ppshop.pojo.TbUser;
import com.ppshop.portal.service.UserService;

/**
 * 用户管理
 * @author Administrator
 *
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;
	
	@Value("${SSO_USER_TOKEN}")
	private String SSO_USER_TOKEN;
	
	@Value("${SSO_PAGE_LOGIN}")
	public String SSO_PAGE_LOGIN;

	
	@Override
	public TbUser getUserByToken(String token) {
		try {
			//调用SSO系统，根据token获取用户信息
			String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
			PpShopResult result = PpShopResult.formatToPojo(json,TbUser.class);
			if (result.getStatus() == 200) {
				TbUser tbUser = (TbUser)result.getData();
				return tbUser;
			}
		} catch (Exception e) {
			e.printStackTrace();
			PpShopResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return null;
	}

}
