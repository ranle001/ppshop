package com.ppshop.service;

import com.ppshop.pojo.TbItemDesc;

public interface ItemDescService {
	/**
	 * 根据商品ID查询商品
	 * @param itemId
	 * @return
	 */
	TbItemDesc getItemDescById(long itemId);
}
