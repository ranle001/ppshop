package com.ppshop.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppshop.common.pojp.EUDataGridResult;
import com.ppshop.mapper.TbItemMapper;
import com.ppshop.pojo.TbItem;
import com.ppshop.service.ItemService;

/**
 * 商品管理service
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
public class ItemServiceImpl implements ItemService{
	
	@Autowired 
	private TbItemMapper itemMapper;
	
	@Override
	public TbItem getItemById(long itemId) {
		return itemMapper.getItemById(itemId);
	}

	@Override
	public List<TbItem> getItem() {
		return itemMapper.getItem();
	}
	
	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		PageHelper.startPage(page, rows);
		List<TbItem> list= itemMapper.getItem();
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>();
		result.setPage(pageInfo.getTotal());
		return result;
	}
}
