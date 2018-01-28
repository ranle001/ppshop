package com.ppshop.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.rest.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired 
	private ItemService itemService;
	
	@RequestMapping("/info/{itemId}")
	@ResponseBody
	public PpShopResult getItemBaseInfo(@PathVariable Long itemId){
		PpShopResult result = itemService.getItemBaseInfo(itemId);
		return result;
	}
	
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public PpShopResult getItemDesc(@PathVariable Long itemId){
		PpShopResult result = itemService.getItemDesc(itemId);
		return result;
	}
	
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public PpShopResult getItemParam(@PathVariable Long itemId){
		PpShopResult result = itemService.getItemParam(itemId);
		return result;
	}
}
