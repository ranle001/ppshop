package com.ppshop.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppshop.rest.pojo.CatResult;
import com.ppshop.rest.service.ItemCatService;

/**
 * 
 * <pre>
 * 商品分类列表
 * </pre>
 * @author pangkaiguang
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
@Controller
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	//spring4.1以前的方法
//	@RequestMapping(value="/itemcat/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
//	@ResponseBody
//	public String getItemCatList(String callback){
//		CatResult catResult = itemCatService.getItemCatList();
//		String json = JsonUtils.objectToJson(catResult);
//		String result = callback + "(" + json + ");";
//		return result;
//	}
	//spring4.1以后的方法
	@RequestMapping(value="/itemcat/list")
	@ResponseBody
	public Object getItemCatList(String callback){
		CatResult catResult = itemCatService.getItemCatList();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
}
