package com.ppshop.portal.service;

import com.ppshop.portal.pojo.SearchItemInfo;

public interface ItemService {
	/**
	 * 获取商品信息
	 * @param itemId
	 * @return
	 */
	SearchItemInfo getItemById(long itemId);
	/**
	 * 获取商品描述信息
	 * @param itemId
	 * @return
	 */
	String getItemDescById(long itemId);
	/**
	 * 获取商品规格信息
	 * @param itemId
	 * @return
	 */
	String getItemParamById(long itemId);
}
