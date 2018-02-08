package com.ppshop.portal.service;

import com.ppshop.portal.pojo.SearchResult;

public interface SearchService {
	/**
	 * 搜索服务
	 * @param queryString
	 * @param page
	 * @return
	 */
	SearchResult search(String queryString, int page);
}
