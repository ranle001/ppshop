package com.ppshop.portal.service;

import com.ppshop.pojo.TbUser;

public interface UserService {
	TbUser getUserByToken(String token);
}
