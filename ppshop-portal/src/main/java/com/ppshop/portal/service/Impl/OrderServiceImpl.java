package com.ppshop.portal.service.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.common.utils.HttpClientUtil;
import com.ppshop.common.utils.JsonUtils;
import com.ppshop.portal.pojo.Order;
import com.ppshop.portal.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService {
	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;
	
	@Override
	public String createOrder(Order order) {
		//调用pp-order服务提交订单
		String json = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, JsonUtils.objectToJson(order));
		//转换
		PpShopResult result = PpShopResult.format(json);
		if (result.getStatus()==200){
			Object orderId= result.getData();
			return orderId.toString();
		}
		return "";
	}

}
