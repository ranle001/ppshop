package com.ppshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppshop.common.pojo.EUDataGridResult;
import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.pojo.TbItemParam;
import com.ppshop.service.ItemParamService;

/**
 * 商品规格Controller
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
@RequestMapping("/item/param")
public class ItemParamController {
	
	@Autowired
	private ItemParamService itemParamService;
	
	/**
	 * 获取商品规格信息
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemParamList(Integer page, Integer rows){
		EUDataGridResult result = itemParamService.getItemParamList(page, rows);
		return result;
	}
	
	/**
	 * 根据itemCatId获取商品规格信息
	 * @param itemCatId
	 * @return
	 */
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public PpShopResult getItemParamById(@PathVariable Long itemCatId){
		PpShopResult result = itemParamService.getItemParamById(itemCatId);
		return result;
	}
	
	/**
	 * 添加规格信息
	 * @param paramData
	 * @param cid
	 * @return
	 */
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public PpShopResult insertItemParam(String paramData, @PathVariable Long cid){
		TbItemParam tbItemParam = new TbItemParam();
		tbItemParam.setItemCatId(cid);
		tbItemParam.setParamData(paramData);
		PpShopResult result = itemParamService.insertItemParam(tbItemParam);
		return result;
	}
		
}
