package com.ppshop.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.ppshop.search.pojo.SearchResult;

public interface SearchDao {
	SearchResult search(SolrQuery query) throws Exception;
}
