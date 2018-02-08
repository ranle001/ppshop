package com.ppshop.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppshop.portal.pojo.SearchItemInfo;
import com.ppshop.portal.service.ItemService;



@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	/**
	 * 根据商品ID获取商品信息，展示商品页面
	 * @param itemId
	 * @param model
	 * @return
	 */
	@RequestMapping("/item/{itemId}")
	public String showItem(@PathVariable Long itemId, Model model){
		SearchItemInfo tbItem = itemService.getItemById(itemId);
		model.addAttribute("item", tbItem);
		return "item";
	}
	
	/**
	 * 取商品描述信息
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value="/item/desc/{itemId}",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemDesc(@PathVariable Long itemId){
		String result = itemService.getItemDescById(itemId);
		return result;
	}
	
	/**
	 * 取商品描述信息
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value="/item/param/{itemId}",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemParam(@PathVariable Long itemId){
		String result = itemService.getItemParamById(itemId);
		return result;
	}
	
}
