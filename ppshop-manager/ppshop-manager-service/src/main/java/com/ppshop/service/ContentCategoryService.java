package com.ppshop.service;

import java.util.List;

import com.ppshop.common.pojo.EUTreeNode;
import com.ppshop.common.pojo.PpShopResult;

public interface ContentCategoryService {
	
	List<EUTreeNode> getCategoryList(long parentId);
	PpShopResult insertContentCategory(long parentId, String name);
	PpShopResult removeContentCategory(long id);
	PpShopResult updateContentCategoryName(long id, String name);
}
