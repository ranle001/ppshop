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
	
	/**
	 * 获取商品基本信息
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/info/{itemId}")
	@ResponseBody
	public PpShopResult getItemBaseInfo(@PathVariable Long itemId){
		PpShopResult result = itemService.getItemBaseInfo(itemId);
		return result;
	}
	
	/**
	 * 获取商品描述信息
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public PpShopResult getItemDesc(@PathVariable Long itemId){
		PpShopResult result = itemService.getItemDesc(itemId);
		return result;
	}
	
	/**
	 * 获取商品规格信息
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public PpShopResult getItemParam(@PathVariable Long itemId){
		PpShopResult result = itemService.getItemParam(itemId);
		return result;
	}
}
