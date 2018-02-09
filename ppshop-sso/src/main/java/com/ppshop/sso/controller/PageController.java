package com.ppshop.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String showLogin(HttpServletRequest request, HttpServletResponse response, String redirect, Model model){
		model.addAttribute("redirect", redirect);
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
