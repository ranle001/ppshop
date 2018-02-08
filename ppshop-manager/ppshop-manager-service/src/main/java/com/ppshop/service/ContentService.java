package com.ppshop.service;

import com.ppshop.common.pojo.EUDataGridResult;
import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.pojo.TbContent;

public interface ContentService {
	/**
	 * 获取内容信息列表
	 */
	EUDataGridResult getContentList(int page, int rows, long categoryId);
	/**
	 * 添加内容信息
	 */
	PpShopResult insertContent(TbContent tbContent);
	/**
	 * 更新内容信息
	 */
	PpShopResult updateContent(TbContent tbContent);
}
