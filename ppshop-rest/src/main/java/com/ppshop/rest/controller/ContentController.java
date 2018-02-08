package com.ppshop.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.common.utils.ExceptionUtil;
import com.ppshop.pojo.TbContent;
import com.ppshop.rest.service.ContentService;

/**
 * 
 * <pre>
 * 内容管理
 * </pre>
 * @author pangkaiguang
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
@Controller
@RequestMapping("/content")
public class ContentController {
	@Autowired
	private ContentService contentService;
	
	/**
	 * 获取首页大广告位内容
	 * @param contentCategoryId
	 * @return
	 */
	@RequestMapping("/list/{contentCategoryId}")
	@ResponseBody
	public PpShopResult getContentList(@PathVariable Long contentCategoryId){
		try {
			List<TbContent> tbContents= contentService.getContentList(contentCategoryId);
			return PpShopResult.ok(tbContents);
		} catch (Exception e) {
			e.printStackTrace();
			return PpShopResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}
