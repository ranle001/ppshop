package com.ppshop.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.search.service.SearchItemService;

/**
 *索引库维护 
 * @author pangkaiguang
 *
 */

@Controller
@RequestMapping("/manager")
public class SearchItemController {
	
	@Autowired
	private SearchItemService searchItemService;
	
	/**
	 * 导入全部商品书籍到索引库
	 */
	@RequestMapping("/importall")
	@ResponseBody
	public PpShopResult inportAllItems(){
		PpShopResult result = searchItemService.getItemList(); 
		return result;
	}
	
	/**
	 * 导入耽搁商品书籍到索引库
	 */
	@RequestMapping("/import")
	@ResponseBody
	public PpShopResult inportAllItems(@RequestParam Long id){
		PpShopResult result = searchItemService.getItemListById(id); 
		return result;
	}
	
}
