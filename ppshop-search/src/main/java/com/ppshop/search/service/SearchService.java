package com.ppshop.search.service;

import com.ppshop.search.pojo.SearchResult;

public interface SearchService {
	SearchResult search(String queryString, int page, int rows) throws Exception;
}
