package com.ppshop.search.dao.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ppshop.search.dao.SearchDao;
import com.ppshop.search.pojo.SearchItem;
import com.ppshop.search.pojo.SearchResult;


@Repository
public class SearchDaoImpl implements SearchDao{
	
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public SearchResult search(SolrQuery query) throws Exception {
		
		SearchResult result = new SearchResult();
		QueryResponse queryResponse = solrServer.query(query);
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		result.setRecordCount(solrDocumentList.getNumFound());
		List<SearchItem> searchItems = new ArrayList<SearchItem>();
		Map<String, Map<String, List<String>>> highligthing = queryResponse.getHighlighting();
		for (SolrDocument solrDocument : solrDocumentList){
			//创建商品对象
			SearchItem searchItem = new SearchItem();
			searchItem.setId(Long.parseLong((String)solrDocument.get("id")));
			//取高亮显示的结果
			String title = "";
			List<String> list = highligthing.get(solrDocument.get("id")).get("item_title");
			if (list !=null && list.size()>0){
				title = list.get(0);
			} else {
				title = (String)solrDocument.get("item_title");
			}
			searchItem.setTitle(title);
			searchItem.setImage((String)solrDocument.get("item_image"));
			searchItem.setPrice((Long)solrDocument.get("item_price"));
			searchItem.setSell_point((String)solrDocument.get("item_sell_poing"));
			searchItem.setCategory_name((String)solrDocument.get("item_category_name"));
			searchItems.add(searchItem);
		}
		result.setiSearchItems(searchItems);
		return result;
	}

}
