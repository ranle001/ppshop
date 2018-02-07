package com.ppshop.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.portal.pojo.CartItem;

public interface CartService {
	/**
	 * 添加商品到购物车(cookies)
	 */
	public PpShopResult addCartItem(Long itemId, int num, HttpServletRequest request, HttpServletResponse response);
	
	List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);
}
