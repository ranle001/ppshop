package com.ppshop.service;

import java.util.List;

import com.ppshop.common.pojp.EUTreeNode;

public interface ItemCatService {
	/**
	 * 商品分类列表
	 * @param parentId
	 * @return
	 */
	List<EUTreeNode> getCatList(long parentId);
}
