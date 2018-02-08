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

/**
 * 首页内容模块列表Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	/**
	 * 获取内容类别信息(首页、大广告、小广告)
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getCategoryList (@RequestParam(value="id",defaultValue="0") Long parentId){
		List<EUTreeNode> resultList = contentCategoryService.getCategoryList(parentId);
		return resultList;		
	}
	
	/**
	 * 创建内容类别
	 * @param parentId
	 * @param name
	 * @return
	 */
	@RequestMapping("/create")
	@ResponseBody
	public PpShopResult createCategory (Long parentId, String name){
		PpShopResult ppShopResult = contentCategoryService.insertContentCategory(parentId, name);
		return ppShopResult;		
	}
	
	/**
	 * 删除内容类别
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public PpShopResult removeCategory (Long id){
		PpShopResult result = contentCategoryService.removeContentCategory(id);	
		return result;
	}
	
	/**
	 * 更新内容类别
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public PpShopResult updateName (Long id, String name){
		PpShopResult result = contentCategoryService.updateContentCategoryName(id, name);	
		return result;
	}
}
