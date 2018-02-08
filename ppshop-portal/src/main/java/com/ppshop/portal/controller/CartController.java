package com.ppshop.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ppshop.portal.pojo.CartItem;
import com.ppshop.portal.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	/**
	 * 添加商品到购物车
	 * @param itemId
	 * @param num
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/add/{itemId}")
	public String addCartItem(@PathVariable Long itemId, @RequestParam(defaultValue="1")Integer num,
			HttpServletRequest request, HttpServletResponse response){
		cartService.addCartItem(itemId, num, request, response);
		return "redirect:/cart/success.html";		
	}
	
	/**
	 * 添加商品到购物车后返回成功页面
	 * @return
	 */
	@RequestMapping("/success")
	public String showSuccess(){
		return "cartSuccess";
	}
	
	/**
	 * 展示购物车商品列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/showCart")
	public String showCart(HttpServletRequest request, HttpServletResponse response,Model model){
		List<CartItem> cartItems = cartService.getCartItemList(request, response);
		model.addAttribute("cartList",cartItems);
		return "cart";
	}
	
	/**
	 * 删除购物车商品
	 */
	@RequestMapping("/delete/{itemId}")
	public String deleteCartItem(HttpServletRequest request, HttpServletResponse response,@PathVariable Long itemId){
		cartService.deleteCartItem(itemId, request, response);
		return "redirect:/cart/showCart.html";
	}
}
