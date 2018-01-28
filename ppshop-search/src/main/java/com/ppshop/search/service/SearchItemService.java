package com.ppshop.search.service;

import com.ppshop.common.pojo.PpShopResult;

public interface SearchItemService {
	PpShopResult getItemList();
	PpShopResult getItemListById(Long id);
}
