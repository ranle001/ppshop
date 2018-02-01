package com.ppshop.service;

import java.util.List;

import com.ppshop.common.pojo.EUDataGridResult;
import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.pojo.TbItem;

public interface ItemService {
	/**
	 * 根据商品ID查询商品
	 * @param itemId
	 * @return
	 */
	TbItem getItemById(long id);
	/**
	 * 查询全部商品
	 * @return
	 */
	List<TbItem> getItem();
	/**
	 * 商品列表查询
	 * @return EUDataGridResult（EU页面需要的格式）
	 */
	EUDataGridResult getItemList(int page, int rows);
	
	PpShopResult createItem(TbItem tbItem, String desc, String itemParams) throws Exception ;
	
	PpShopResult deleteItem(String params);
}
