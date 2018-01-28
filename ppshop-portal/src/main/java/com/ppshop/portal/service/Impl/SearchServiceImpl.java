package com.ppshop.portal.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.common.utils.HttpClientUtil;
import com.ppshop.common.utils.JsonUtils;
import com.ppshop.portal.pojo.SearchItem;
import com.ppshop.portal.pojo.SearchResult;
import com.ppshop.portal.service.SearchService;


@Service
public class SearchServiceImpl implements SearchService{
	
	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;
	
	@Override
	public SearchResult search(String queryString, int page) {
		//参数
		Map<String, String> param = new HashMap<>();
		param.put("q", queryString);
		param.put("page", page + "");
		try {
			//调用ppshop-search
			String json = HttpClientUtil.doGet(SEARCH_BASE_URL,param);
			PpShopResult ppShopResult = PpShopResult.formatToPojo(json, SearchResult.class);
			if (ppShopResult.getStatus() == 200){
				SearchResult result = (SearchResult)ppShopResult.getData();
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return null;
	}

}
