package com.ppshop.service;

import com.ppshop.common.pojo.EUDataGridResult;
import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.pojo.TbItemParam;

public interface ItemParamService {
	/**
	 * 商品列表查询
	 * @return EUDataGridResult（EU页面需要的格式）
	 */
	EUDataGridResult getItemParamList(int page, int rows);
	
	PpShopResult getItemParamById(long cid);
	
	PpShopResult insertItemParam(TbItemParam tbItemParam);
}
