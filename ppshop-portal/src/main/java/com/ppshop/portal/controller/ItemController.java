package com.ppshop.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppshop.pojo.TbItem;
import com.ppshop.portal.service.ItemService;



@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	public String showItem(@PathVariable Long itemId, Model model){
		TbItem tbItem = itemService.getItemById(itemId);
		model.addAttribute("item", tbItem);
		return "item";
	}
}
