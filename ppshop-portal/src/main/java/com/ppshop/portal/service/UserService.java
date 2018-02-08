package com.ppshop.portal.service;

import com.ppshop.pojo.TbUser;

public interface UserService {
	/**
	 * 身份认证，根据token获取用户信息
	 * @param token
	 * @return
	 */
	TbUser getUserByToken(String token);
}
