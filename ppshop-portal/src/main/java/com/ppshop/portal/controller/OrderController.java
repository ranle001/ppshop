package com.ppshop.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ppshop.portal.pojo.CartItem;
import com.ppshop.portal.service.CartService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/order-cart")
	public String showOrderCart(HttpServletRequest request, HttpServletResponse response, Model model){
		List<CartItem> cartItems = cartService.getCartItemList(request, response);
		model.addAttribute("cartList", cartItems);
		return "order-cart";
	}
}
