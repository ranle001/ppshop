package com.ppshop.service;

import com.ppshop.pojo.TbItemParamItem;

public interface ItemParamItemService {
	/**
	 * 根据商品ID获取商品规格详细信息,返回html格式字符串
	 * @param itemId
	 * @return
	 */
	String getItemParamItemById(long itemId);
	/**
	 * 根据商品ID获取商品规格详细信息
	 * @param itemId
	 * @return
	 */
	TbItemParamItem getItemParamItem(long itemId);
}
