package com.ppshop.portal.service;

import com.ppshop.portal.pojo.SearchItemInfo;

public interface ItemService {
	SearchItemInfo getItemById(long itemId);
	String getItemDescById(long itemId);
	String getItemParamById(long itemId);
}
