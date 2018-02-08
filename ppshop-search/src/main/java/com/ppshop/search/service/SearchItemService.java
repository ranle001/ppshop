package com.ppshop.search.service;

import com.ppshop.common.pojo.PpShopResult;

public interface SearchItemService {
	/**
	 * 导入商品到索引库（批量）
	 */
	PpShopResult getItemList();
	/**
	 * 根据商品ID导入商品到索引库（单个）
	 */
	PpShopResult getItemListById(Long id);
	/**
	 * 根据商品ID删除商品到索引库
	 */
	PpShopResult deleteItemById(Long id);
}
