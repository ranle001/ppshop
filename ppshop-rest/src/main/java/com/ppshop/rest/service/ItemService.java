package com.ppshop.rest.service;

import com.ppshop.common.pojo.PpShopResult;

public interface ItemService {
	/**
	 * 获取商品基本信息
	 * @param itemId
	 * @return
	 */
	PpShopResult getItemBaseInfo(long itemId);
	/**
	 * 获取商品描述信息
	 * @param itemId
	 * @return
	 */
	PpShopResult getItemDesc(long itemId);
	/**
	 * 获取商品规格信息
	 * @param itemId
	 * @return
	 */
	PpShopResult getItemParam(long itemId);
}
