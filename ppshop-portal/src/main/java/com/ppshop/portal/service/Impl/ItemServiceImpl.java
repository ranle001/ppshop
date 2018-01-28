package com.ppshop.portal.service.Impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.common.utils.HttpClientUtil;
import com.ppshop.pojo.TbItem;
import com.ppshop.portal.pojo.SearchItem;
import com.ppshop.portal.service.ItemService;

/**
 * 商品信息管理
 * @author Administrator
 *
 */

@Service
public class ItemServiceImpl implements ItemService{
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${ITEM_INFO_URL}")
	private String ITEM_INFO_URL;
	
	@Value("${ITEM_DESC_URL}")
	private String ITEM_DESC_URL;
	
	@Value("${ITEM_PARAM_URL}")
	private String ITEM_PARAM_URL;
	
	@Override
	public TbItem getItemById(long itemId) {
		try {
			//调用rest服务查询商品信息	
			String json = HttpClientUtil.doGet(REST_BASE_URL+ITEM_INFO_URL+itemId);
			if (StringUtils.isNoneBlank(json)) {
				PpShopResult result = PpShopResult.formatToPojo(json, TbItem.class);
				if (result.getStatus() == 200){
					TbItem tbItem = (TbItem) result.getData();
					return tbItem;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}

}
