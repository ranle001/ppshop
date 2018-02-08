package com.ppshop.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ppshop.portal.pojo.SearchResult;
import com.ppshop.portal.service.SearchService;


/**
 * 商品搜索Controller
 * @author Administrator
 *
 */
@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	/**
	 * 搜索商品，返回搜索信息页面
	 * @param queryString
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("/search")
	public String search(@RequestParam("q")String queryString, @RequestParam(defaultValue="1")Integer page, Model model){
		if (queryString != null){
			try {
				queryString = new String(queryString.getBytes("iso8859-1"),"utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		SearchResult result = searchService.search(queryString, page);
		model.addAttribute("query",queryString);
		model.addAttribute("totalPages",result.getPageCount());
		model.addAttribute("itemList",result.getiSearchItems());
		model.addAttribute("page",page);
		return "search";	
	}
}
