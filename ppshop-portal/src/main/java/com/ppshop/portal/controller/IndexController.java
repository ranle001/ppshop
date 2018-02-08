package com.ppshop.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ppshop.portal.service.ContentService;



@Controller
public class IndexController {
	
	@Autowired 
	private ContentService contentService;
	
	/**
	 * 跳转商场首页
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String showIndex(Model model){
		String result = contentService.getContentList();
		model.addAttribute("ad1",result);
		return "index";
	}
}
