package com.ppshop.order.pojo;

import java.util.List;

import com.ppshop.pojo.TbOrder;
import com.ppshop.pojo.TbOrderItem;
import com.ppshop.pojo.TbOrderShipping;

/**
 * 订单包装类
 * @author pangkaiguang
 *
 */
public class Order extends TbOrder{
	private List<TbOrderItem> orderItems;
	private TbOrderShipping orderShipping;
	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	
}
