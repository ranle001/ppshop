package com.ppshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ppshop.service.ItemParamItemService;

/**
 * 商品规格详细Controller
 * <pre>
 * 程序的中文名称。
 * </pre>
 * @author pangkaiguang
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */

@Controller
public class ItemParamItemController {
	
	@Autowired
	private ItemParamItemService itemParamItemService;
	
	/**
	 * 获取商品规格详细
	 * @param itemId
	 * @param request
	 * @return
	 */
	@RequestMapping("/showItem/{itemId}")
	public String getItemParamList(@PathVariable Long itemId, HttpServletRequest request){
		String text = itemParamItemService.getItemParamItemById(itemId);
		request.setAttribute("itemParams", text);
		return "item";
	}	
}
