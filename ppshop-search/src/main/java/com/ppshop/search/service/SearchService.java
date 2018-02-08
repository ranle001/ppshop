package com.ppshop.search.service;

import com.ppshop.search.pojo.SearchResult;

public interface SearchService {
	/**
	 * 搜索商品
	 * @param queryString
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	SearchResult search(String queryString, int page, int rows) throws Exception;
}
