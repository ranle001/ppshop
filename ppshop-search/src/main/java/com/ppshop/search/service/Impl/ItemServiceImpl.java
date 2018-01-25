package com.ppshop.search.service.Impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.common.utils.ExceptionUtil;
import com.ppshop.search.mapper.ItemMapper;
import com.ppshop.search.pojo.Item;
import com.ppshop.search.service.ItemService;


@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemMapper itemMapper;

	@Autowired
	private SolrServer solrServer;
	
	@Override
	public PpShopResult getItemList() {
		try {
			List<Item> items= itemMapper.getItemList();
			//把商品写入索引库
			for (Item item : items){
				//创建文档对象
				SolrInputDocument document = new SolrInputDocument();
				document.setField("id", item.getId());
				document.setField("item_title", item.getTiele());
				document.setField("item_sell_point", item.getSell_point());
				document.setField("item_price", item.getPrice());
				document.setField("item_image", item.getImage());
				document.setField("item_category_name", item.getCategory_name());
				document.setField("item_desc", item.getItem_des());
				//写入索引库
				solrServer.add(document);			
			}
			solrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return PpShopResult.build(500,ExceptionUtil.getStackTrace(e));
		}
		return PpShopResult.ok();
	}

}
