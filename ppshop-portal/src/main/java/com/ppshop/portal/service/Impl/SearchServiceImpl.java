package com.ppshop.portal.service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.common.utils.HttpClientUtil;
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
