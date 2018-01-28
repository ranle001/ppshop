package com.ppshop.rest.service;

import com.ppshop.common.pojo.PpShopResult;

public interface ItemService {
	PpShopResult getItemBaseInfo(long itemId);
	PpShopResult getItemDesc(long itemId);
	PpShopResult getItemParam(long itemId);
}
