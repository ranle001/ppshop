package com.ppshop.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ppshop.common.utils.ExceptionUtil;
import com.ppshop.pojo.TbUser;
import com.ppshop.portal.pojo.CartItem;
import com.ppshop.portal.pojo.Order;
import com.ppshop.portal.service.CartService;
import com.ppshop.portal.service.OrderService;

/**
 * 订单controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 购物车列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/order-cart")
	public String showOrderCart(HttpServletRequest request, HttpServletResponse response, Model model){
		List<CartItem> cartItems = cartService.getCartItemList(request, response);
		model.addAttribute("cartList", cartItems);
		return "order-cart";
	}
		
	/**
	 * 创建订单
	 * @param order
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/create")
	public String createOrder(Order order,Model model, HttpServletRequest request){
		try {
			//补全用户信息(用户信息在拦截器里面有保存，从拦截器的request中取用户信息)
			TbUser user = (TbUser) request.getAttribute("user");
			order.setUserId(user.getId());
			order.setBuyerNick(user.getUsername());
			String orderId = orderService.createOrder(order);
			model.addAttribute("orderId", orderId);
			model.addAttribute("payment", order.getPayment());
			model.addAttribute("date", 	new DateTime().plusDays(3).toString("yyyy-MM-dd"));
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", ExceptionUtil.getStackTrace(e));
			return "error/exception";
		}
	}
}
