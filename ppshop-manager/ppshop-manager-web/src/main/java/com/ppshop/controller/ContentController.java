package com.ppshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppshop.common.pojo.EUDataGridResult;
import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.pojo.TbContent;
import com.ppshop.service.ContentService;

/**
 * 内容信息controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	/**
	 * 根据内容类型ID获取内容信息列表
	 */
	@RequestMapping("/query/list")
	@ResponseBody
	public EUDataGridResult getCategoryList (Integer page, Integer rows, Long categoryId){
		EUDataGridResult resultList = contentService.getContentList(page, rows, categoryId);
		return resultList;		
	}
	/**
	 * 添加内容信息
	 */
	@RequestMapping("/save")
	@ResponseBody
	public PpShopResult createContent (TbContent tbContent){
		PpShopResult resultList = contentService.insertContent(tbContent);
		return resultList;		
	}
	/**
	 * 更新内容信息
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public PpShopResult updateContent (TbContent tbContent){
		PpShopResult resultList = contentService.updateContent(tbContent);
		return resultList;		
	}
}
