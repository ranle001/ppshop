package com.ppshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppshop.common.pojo.EUTreeNode;
import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.service.ContentCategoryService;


@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getCategoryList (@RequestParam(value="id",defaultValue="0") Long parentId){
		List<EUTreeNode> resultList = contentCategoryService.getCategoryList(parentId);
		return resultList;		
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public PpShopResult createCategory (Long parentId, String name){
		PpShopResult ppShopResult = contentCategoryService.insertContentCategory(parentId, name);
		return ppShopResult;		
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public PpShopResult removeCategory (Long id){
		PpShopResult result = contentCategoryService.removeContentCategory(id);	
		return result;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public PpShopResult updateName (Long id, String name){
		PpShopResult result = contentCategoryService.updateContentCategoryName(id, name);	
		return result;
	}
}
