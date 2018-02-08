package com.ppshop.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.pojo.TbUser;

public interface UserService {
	/**
	 * 检查登录信息
	 * @param content
	 * @param type
	 * @return
	 */
	PpShopResult checkData(String content, Integer type);
	/**
	 * 创建用户
	 * @param tbUser
	 * @return
	 */
	PpShopResult createUser(TbUser tbUser);
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	PpShopResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);
	/**
	 * 根据token获取用户信息
	 * @param token
	 * @return
	 */
	PpShopResult getUserByToken(String token);
	/**
	 * 从redis删除用户信息
	 * @param token
	 * @return
	 */
	PpShopResult removeToken(String token);
}
