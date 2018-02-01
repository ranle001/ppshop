package com.ppshop.mapper;

import com.ppshop.pojo.TbItemParamItem;


public interface TbItemParamItemMapper {
	void insertItemParamItem(TbItemParamItem tbItemParamItem);
	TbItemParamItem getItemParamItemByCid(long itemId);
	void deleteItemParam(long itemId);
}