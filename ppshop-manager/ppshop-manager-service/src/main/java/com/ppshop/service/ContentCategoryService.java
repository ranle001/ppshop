package com.ppshop.service;

import java.util.List;

import com.ppshop.common.pojo.EUTreeNode;
import com.ppshop.common.pojo.PpShopResult;

public interface ContentCategoryService {
	/**
	 * 获取内容类别列表
	 * @param parentId
	 * @return
	 */
	List<EUTreeNode> getCategoryList(long parentId);
	/**
	 * 添加内容类别
	 * @param parentId
	 * @param name
	 * @return
	 */
	PpShopResult insertContentCategory(long parentId, String name);
	/**
	 * 删除内容类别
	 * @param id
	 * @return
	 */
	PpShopResult removeContentCategory(long id);
	/**
	 * 更新内容类别
	 * @param id
	 * @param name
	 * @return
	 */
	PpShopResult updateContentCategoryName(long id, String name);
}
