package com.ppshop.service;

import com.ppshop.common.pojo.EUDataGridResult;
import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.pojo.TbItemParam;

public interface ItemParamService {
	/**
	 * 商品规格详细信息列表查询
	 * @return EUDataGridResult（EU页面需要的格式）
	 */
	EUDataGridResult getItemParamList(int page, int rows);
	/**
	 * 商品规格详细信息列表查询
	 * @return PpShopResult
	 */
	PpShopResult getItemParamById(long cid);
	/**
	 * 添加商品详细信息
	 * @param tbItemParam
	 * @return
	 */
	PpShopResult insertItemParam(TbItemParam tbItemParam);
}
