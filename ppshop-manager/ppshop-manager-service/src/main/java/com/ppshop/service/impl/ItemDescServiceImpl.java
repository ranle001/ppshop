package com.ppshop.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppshop.mapper.TbItemDescMapper;
import com.ppshop.pojo.TbItemDesc;
import com.ppshop.service.ItemDescService;

/**
 * 商品描述管理service
 * <pre>
 * 程序的中文名称。
 * </pre>
 * @author pangkaiguang
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
@Service
public class ItemDescServiceImpl implements ItemDescService{
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	
	@Override
	public TbItemDesc getItemDescById(long itemId) {
		return tbItemDescMapper.quertyItemDescByItemId(itemId);
	}
}
