package com.ppshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppshop.common.pojo.PpShopResult;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@RequestMapping("/menu")
	@ResponseBody
	public PpShopResult getMenu(){
		
		return null;
	}
}
