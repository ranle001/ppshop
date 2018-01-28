package com.ppshop.rest.service.Impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.common.utils.JsonUtils;
import com.ppshop.mapper.TbItemDescMapper;
import com.ppshop.mapper.TbItemMapper;
import com.ppshop.mapper.TbItemParamItemMapper;
import com.ppshop.pojo.TbItem;
import com.ppshop.pojo.TbItemDesc;
import com.ppshop.pojo.TbItemParamItem;
import com.ppshop.rest.service.ItemService;
import com.ppshop.rest.service.JedisClient;

/**
 * 商品信息管理
 * @author Administrator
 *
 */
@Service
public class ItemServiceImpl implements ItemService{
	
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	
	@Value("${REDIS_ITEM_EXIPRE}")
	private Integer REDIS_ITEM_EXIPRE;
	
	@Autowired
	private TbItemMapper tbItemMapper;
	
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	
	@Autowired
	private JedisClient jedisClientSingle;

	@Override
	public PpShopResult getItemBaseInfo(long itemId) {
		//先查缓存
		try {
			String str = jedisClientSingle.get(REDIS_ITEM_KEY + ":" + itemId + ":base");
			if (StringUtils.isNotBlank(str)){
				TbItem item = JsonUtils.jsonToPojo(str, TbItem.class);
				return PpShopResult.ok(item); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbItem item = tbItemMapper.getItemById(itemId);
		try {
			//加入缓存
			jedisClientSingle.set(REDIS_ITEM_KEY + ":" + itemId + ":base", JsonUtils.objectToJson(item));
			//设置有效期间
			jedisClientSingle.expire(REDIS_ITEM_KEY + ":" + itemId + ":base", REDIS_ITEM_EXIPRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PpShopResult.ok(item);
	}

	@Override
	public PpShopResult getItemDesc(long itemId) {
		//先查缓存
		try {
			String str = jedisClientSingle.get(REDIS_ITEM_KEY + ":" + itemId + ":desc");
			if (StringUtils.isNotBlank(str)){
				TbItemDesc itemDesc = JsonUtils.jsonToPojo(str, TbItemDesc.class);
				return PpShopResult.ok(itemDesc); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbItemDesc tbItemDesc = tbItemDescMapper.quertyItemDescByItemId(itemId);
		try {
			//加入缓存
			jedisClientSingle.set(REDIS_ITEM_KEY + ":" + itemId + ":desc", JsonUtils.objectToJson(tbItemDesc));
			//设置有效期间
			jedisClientSingle.expire(REDIS_ITEM_KEY + ":" + itemId + ":desc", REDIS_ITEM_EXIPRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PpShopResult.ok(tbItemDesc);
	}

	@Override
	public PpShopResult getItemParam(long itemId) {
		//先查缓存
		try {
			String str = jedisClientSingle.get(REDIS_ITEM_KEY + ":" + itemId + "param");
			if (StringUtils.isNotBlank(str)){
				TbItemParamItem tbItemParamItem = JsonUtils.jsonToPojo(str, TbItemParamItem.class);
				return PpShopResult.ok(tbItemParamItem); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbItemParamItem tbItemParamItem = tbItemParamItemMapper.getItemParamItemByCid(itemId);
		try {
			//加入缓存
			jedisClientSingle.set(REDIS_ITEM_KEY + ":" + itemId + ":param", JsonUtils.objectToJson(tbItemParamItem));
			//设置有效期间
			jedisClientSingle.expire(REDIS_ITEM_KEY + ":" + itemId + ":param", REDIS_ITEM_EXIPRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PpShopResult.ok(tbItemParamItem);
	}

}
