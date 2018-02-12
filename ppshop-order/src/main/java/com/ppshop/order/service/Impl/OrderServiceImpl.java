package com.ppshop.order.service.Impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.common.utils.ExceptionUtil;
import com.ppshop.mapper.TbOrderItemMapper;
import com.ppshop.mapper.TbOrderMapper;
import com.ppshop.mapper.TbOrderShippingMapper;
import com.ppshop.order.service.JedisClient;
import com.ppshop.order.service.OrderService;
import com.ppshop.pojo.TbOrder;
import com.ppshop.pojo.TbOrderItem;
import com.ppshop.pojo.TbOrderShipping;

/**
 * 订单管理Service
 * @author pangkaiguang
 *
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private TbOrderMapper tbOrderMapper;
	
	@Autowired
	private TbOrderItemMapper tbOrderItemMapper;
	
	@Autowired
	private TbOrderShippingMapper tbOrderShippingMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${ORDER_GEN_KEY}")
	private String ORDER_GEN_KEY;
	
	@Value("${ORDER_INIT_ID}")
	private String ORDER_INIT_ID;
	
	@Value("${ORDER_DETAIL_GEN_KEY}")
	private String ORDER_DETAIL_GEN_KEY;
	
	@Override
	public PpShopResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping) {
		try {
			//获得订单号
			String str = jedisClient.get(ORDER_GEN_KEY);
			if (StringUtils.isBlank(str)){
				jedisClient.set(ORDER_GEN_KEY, ORDER_INIT_ID);
			}
			long orderId =  jedisClient.incr(ORDER_GEN_KEY);
			//补全POJP属性
			order.setOrderId(orderId + "");
			//1、未付款     2、已付款     3、未发货     4、已发货      5、交易成功    6、交易关闭
			order.setStatus(1);
			order.setCreateTime(new Date());
			order.setUpdateTime(new Date());
			//0：未评价   1：已评价
			order.setBuyerRate(0);
			//向订单表中插入
			tbOrderMapper.insertOrder(order);
			//插入订单明细
			for (TbOrderItem tbOrderItem : itemList){
				//补全信息
				long detailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
				tbOrderItem.setId(detailId + "");
				tbOrderItem.setOrderId(orderId + "");
				//向订单明细表中插入
				tbOrderItemMapper.insertOrderItem(tbOrderItem);
			}
			//插入物流表
			orderShipping.setOrderId(orderId + "");
			orderShipping.setCreated(new Date());
			orderShipping.setUpdated(new Date());
			tbOrderShippingMapper.insertOrderShipping(orderShipping);
			return PpShopResult.ok(orderId);
		} catch (Exception e) {
			e.printStackTrace();
			return PpShopResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

}
