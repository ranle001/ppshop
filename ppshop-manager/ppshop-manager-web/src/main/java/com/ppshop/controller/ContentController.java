package com.ppshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppshop.common.pojo.EUDataGridResult;
import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.pojo.TbContent;
import com.ppshop.service.ContentService;


@Controller
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/query/list")
	@ResponseBody
	public EUDataGridResult getCategoryList (Integer page, Integer rows, Long categoryId){
		EUDataGridResult resultList = contentService.getContentList(page, rows, categoryId);
		return resultList;		
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public PpShopResult createContent (TbContent tbContent){
		PpShopResult resultList = contentService.insertContent(tbContent);
		return resultList;		
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public PpShopResult updateContent (TbContent tbContent){
		PpShopResult resultList = contentService.updateContent(tbContent);
		return resultList;		
	}
}
