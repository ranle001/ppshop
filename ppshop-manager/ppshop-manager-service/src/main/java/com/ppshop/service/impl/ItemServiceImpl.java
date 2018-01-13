package com.ppshop.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppshop.mapper.TbItemMapper;
import com.ppshop.pojo.TbItem;
import com.ppshop.pojo.TbItemExample;
import com.ppshop.pojo.TbItemExample.Criteria;
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
	    TbItemExample example = new TbItemExample();
	    //添加查询条件
	    Criteria criteria = example.createCriteria();
	    criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0){
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}

}
