package com.ppshop.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ppshop.common.utils.CookieUtils;
import com.ppshop.pojo.TbUser;
import com.ppshop.portal.service.Impl.UserServiceImpl;

public class LoginInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserServiceImpl userService;
	
	

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception)
			throws Exception {
		//返回ModelAndView之后
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView)
			throws Exception {
		//在handler执行之后，返回ModelAndView之前

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		//在handler执行之前
		//判断用户是否登录
		//从cookies中取token
		String token = CookieUtils.getCookieValue(request, "PP_TOKEN");		
		//根据token换取用户信息，调用sso系统的接口
		TbUser tbUser = userService.getUserByToken(token);
		//取不到用户信息
		if (null == tbUser){
			//跳转到登录页面，把用户请求的url作为参数传递到登录页面
			response.sendRedirect(userService.SSO_BASE_URL+userService.SSO_PAGE_LOGIN 
					+ "?redirect=" + request.getRequestURI());
			//返回false
			return false;
		}
		//取到用户信息
		//返回值决定handler是否执行，true 执行，false 不执行
		return true;
	}

}
