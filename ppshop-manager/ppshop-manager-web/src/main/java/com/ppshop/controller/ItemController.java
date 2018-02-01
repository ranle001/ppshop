package com.ppshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppshop.common.pojo.EUDataGridResult;
import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.pojo.TbItem;
import com.ppshop.pojo.TbItemDesc;
import com.ppshop.pojo.TbItemParamItem;
import com.ppshop.service.ItemDescService;
import com.ppshop.service.ItemParamItemService;
import com.ppshop.service.ItemService;

/**
 * 商品管理Controller
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
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemDescService itemDescService;
	
	@Autowired
	private ItemParamItemService itemParamItemService;
	
	@RequestMapping("/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId){
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	
	/**
	 * 获取商品列表
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows){
		EUDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	
	/**
	 * 保存商品
	 * @param tbItem
	 * @param desc
	 * @param itemParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public PpShopResult createItem(TbItem tbItem, String desc, String itemParams) throws Exception{
		PpShopResult result = itemService.createItem(tbItem, desc, itemParams);
		return result;
	}
	
	/**
	 * 获取商品描述信息
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public PpShopResult queryItemDesc(@PathVariable Long itemId){
		TbItemDesc tbItemDesc = this.itemDescService.getItemDescById(itemId);
		return PpShopResult.ok(tbItemDesc);
	}
	
	/**
	 * 获取商品规格参数
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public PpShopResult queryItemParam(@PathVariable Long itemId){
		TbItemParamItem tbItemParamItem = this.itemParamItemService.getItemParamItem(itemId);
		return PpShopResult.ok(tbItemParamItem);
	}
	
	/**
	 * 删除商品
	 * @param params
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public PpShopResult deletItem(@RequestBody String params){
		String[] itemIds = params.split(",");
		for (String str : itemIds){
		
		}
		return PpShopResult.ok();	
	}
}
