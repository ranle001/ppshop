package com.ppshop.service;

import com.ppshop.pojo.TbItemParamItem;

public interface ItemParamItemService {
	String getItemParamItemById(long itemId);
	
	TbItemParamItem getItemParamItem(long itemId);
}
