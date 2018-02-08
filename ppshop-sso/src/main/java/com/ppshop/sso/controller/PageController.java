package com.ppshop.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 页面跳转
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/page")
public class PageController {
	/**
	 * 跳转登录页面
	 * @return
	 */
	@RequestMapping("/login")
	public String showLogin(){
		return "login";
	}
	/**
	 * 跳转注册页面
	 * @return
	 */
	@RequestMapping("/register")
	public String showRegister(){
		return "register";
	}
}
