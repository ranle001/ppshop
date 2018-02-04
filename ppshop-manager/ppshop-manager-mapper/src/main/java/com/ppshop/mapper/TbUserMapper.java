package com.ppshop.mapper;

import java.util.List;

import com.ppshop.pojo.TbUser;

public interface TbUserMapper {
	List<TbUser> getUsersByUsername(String username);
	List<TbUser> getUsersByPhone(String phone);
	List<TbUser> getUsersEmail(String email);
	void insertUser(TbUser tbUser);
}