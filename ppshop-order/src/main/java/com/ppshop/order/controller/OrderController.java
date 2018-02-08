package com.ppshop.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.order.pojo.Order;
import com.ppshop.order.service.OrderService;

/**
 * 订单管理controller
 * @author pangkaiguang
 *
 */
@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 创建订单
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public PpShopResult createOrder(@RequestBody Order order){
		PpShopResult result = orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());
		return result;
	}
}
