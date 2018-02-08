package com.ppshop.portal.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.common.utils.CookieUtils;
import com.ppshop.common.utils.ExceptionUtil;
import com.ppshop.common.utils.HttpClientUtil;
import com.ppshop.common.utils.JsonUtils;
import com.ppshop.pojo.TbItem;
import com.ppshop.portal.pojo.CartItem;
import com.ppshop.portal.service.CartService;


/**
 * 购物车服务类
 * @author pangkaiguang
 *
 */
@Service
public class CartServiceImpl implements CartService {
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;
	
	/**
	 * 添加商品到购物车(cookies)
	 */
	@Override
	public PpShopResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
		try {
			//取商品信息
			CartItem cartItem = null;
			//取购物车商品列表
			List<CartItem> cartItems = getCartItemList(request);
			//判断购物车商品列表钟是否存在此商品
			for (CartItem cItem : cartItems) {
				if (cItem.getId().equals(itemId)){
					//如果存在
					//增加数量，不算价格
					cItem.setNum(cItem.getNum() + num);
					cartItem = cItem;
					break;
				}
			}
			//如果不存在
			if (cartItem == null) {
				cartItem = new CartItem();
				//根据商品id查询商品信息
				String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
				PpShopResult result = PpShopResult.formatToPojo(json, TbItem.class);
				if (result.getStatus() == 200) {
					TbItem tbItem = (TbItem) result.getData();
					cartItem.setId(tbItem.getId());
					cartItem.setTitle(tbItem.getTitle());
					cartItem.setNum(num);
					cartItem.setPrice(tbItem.getPrice());
					cartItem.setImage(tbItem.getImage() == null ? "":tbItem.getImage().split(",")[0]);
				}
				//添加到购物车列表
				cartItems.add(cartItem);
			}
			//把购物车列表写入cookies
			CookieUtils.setCookie(request, response, "PP_CART", JsonUtils.objectToJson(cartItems), true);
			return PpShopResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return PpShopResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	private List<CartItem> getCartItemList(HttpServletRequest request){
		String json = CookieUtils.getCookieValue(request, "PP_CART", true);
		if (StringUtils.isBlank(json)){
			return new ArrayList<>();
		}
		try {
			List<CartItem> cartItems = JsonUtils.jsonToList(json, CartItem.class);
			return cartItems;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	@Override
	public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> iCartItems = getCartItemList(request);
		return iCartItems;
	}
	
	/**
	 * 删除购物车商品
	 */
	@Override
	public PpShopResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response) {
		//从cookies取商品列表
		List<CartItem> iCartItems = getCartItemList(request);
		//从列表找到商品删除
		for (CartItem cartItem : iCartItems){
			if (cartItem.getId().equals(itemId)) {
				iCartItems.remove(cartItem);
				break;
			}
		}
		CookieUtils.setCookie(request, response, "PP_CART", JsonUtils.objectToJson(iCartItems), true);
 		return PpShopResult.ok();
	}
}
