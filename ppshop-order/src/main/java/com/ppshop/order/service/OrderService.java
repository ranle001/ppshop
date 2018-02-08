package com.ppshop.order.service;

import java.util.List;

import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.pojo.TbOrder;
import com.ppshop.pojo.TbOrderItem;
import com.ppshop.pojo.TbOrderShipping;

public interface OrderService {
	/**
	 * 创建订单
	 */
	PpShopResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);
}
