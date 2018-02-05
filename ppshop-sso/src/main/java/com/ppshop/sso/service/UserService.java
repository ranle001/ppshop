package com.ppshop.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.pojo.TbUser;

public interface UserService {
	PpShopResult checkData(String content, Integer type);
	
	PpShopResult createUser(TbUser tbUser);
	
	PpShopResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);
	
	PpShopResult getUserByToken(String token);
	
	PpShopResult removeToken(String token);
}
