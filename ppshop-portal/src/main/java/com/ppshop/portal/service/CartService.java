package com.ppshop.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.portal.pojo.CartItem;

public interface CartService {
	/**
	 * 添加商品到购物车
	 */
	public PpShopResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 获取购物车商品商品列表
	 */
	List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 删除购物车商品
	 */
	PpShopResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);
}
