package com.ppshop.sso.service;

import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.pojo.TbUser;

public interface UserService {
	PpShopResult checkData(String content, Integer type);
	
	PpShopResult createUser(TbUser tbUser);
	
	PpShopResult userLogin(String username, String password);
	
	PpShopResult getUserByToken(String token);
	
	PpShopResult removeToken(String token);
}
