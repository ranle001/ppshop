package com.ppshop.service.impl;


import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppshop.common.pojo.EUDataGridResult;
import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.common.utils.ExceptionUtil;
import com.ppshop.common.utils.IDUtils;
import com.ppshop.mapper.TbItemDescMapper;
import com.ppshop.mapper.TbItemMapper;
import com.ppshop.mapper.TbItemParamItemMapper;
import com.ppshop.pojo.TbItem;
import com.ppshop.pojo.TbItemDesc;
import com.ppshop.pojo.TbItemParamItem;
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
	
	@Autowired 
	private TbItemDescMapper tbItemDescMapper;
	
	@Autowired 
	private TbItemParamItemMapper tbItemParamItemMapper;
	
	@Override
	public TbItem getItemById(long id) {
		return itemMapper.getItemById(id);
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
		PageInfo pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public PpShopResult createItem(TbItem tbItem, String desc, String itemParams) throws Exception {
		//补全商品信息
		//生产ID
		tbItem.setId(IDUtils.genItemId());
		tbItem.setStatus((byte) 1);
		tbItem.setCreated(new Date());
		tbItem.setUpdated(new Date());
		itemMapper.insertItem(tbItem);
		PpShopResult result = insertItemDesc(tbItem.getId(), desc);
		if (result.getStatus() != 200){
			throw new Exception();
		}
	    result = insertItemParam(tbItem.getId(), itemParams);
		if (result.getStatus() != 200){
			throw new Exception();
		}
		return PpShopResult.ok();
	}
	
	/**
	 * 添加商品描述
	 * @param desc
	 */
	private PpShopResult insertItemDesc(Long itemId, String desc){
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		tbItemDescMapper.insertItemDesc(itemDesc);
		return PpShopResult.ok();
	}
	
	/**
	 * 添加商品规格
	 * @param desc
	 */
	private PpShopResult insertItemParam(Long itemId, String itemParams){
		TbItemParamItem tbItemParamItem = new TbItemParamItem();
		tbItemParamItem.setItemId(itemId);
		tbItemParamItem.setParamData(itemParams);
		tbItemParamItem.setCreated(new Date());
		tbItemParamItem.setUpdated(new Date());
		tbItemParamItemMapper.insertItemParamItem(tbItemParamItem);
		return PpShopResult.ok();
	}

	@Override
	public PpShopResult deleteItem(String params) {
		try {
			String[] itemIds = params.split(",");
			for (String str : itemIds){
				//删除商品规格信息
				tbItemParamItemMapper.deleteItemParam(Long.parseLong(str));
				//删除商品描述信息
				tbItemDescMapper.deleteItemDesc(Long.parseLong(str));
				//删除商品
				itemMapper.deleteItem(Long.parseLong(str));
				//调用添加商品到solr方法
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return PpShopResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return PpShopResult.ok();
	}
}
