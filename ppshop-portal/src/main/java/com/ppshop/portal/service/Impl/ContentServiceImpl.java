package com.ppshop.portal.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.common.utils.HttpClientUtil;
import com.ppshop.common.utils.JsonUtils;
import com.ppshop.pojo.TbContent;
import com.ppshop.portal.service.ContentService;

/**
 * 
 * <pre>
 * 调用服务层服务,查询内容列表
 * </pre>
 * @author pangkaiguang
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
@Service
public class ContentServiceImpl implements ContentService{
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;
	
	@Override
	public String getContentList() {
		//调用服务层服务
		String result = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
		//把字符串转换成ppshopresult
		try {
			PpShopResult ppShopResult = PpShopResult.formatToList(result, TbContent.class);
			List<TbContent> tbContents = (List<TbContent>) ppShopResult.getData();
			//转换成JSP页面需要的json格式
			List<Map> mapList = new ArrayList<>(); 
			for (TbContent tbContent : tbContents){
			    Map map = new HashMap<>();
			    map.put("src", tbContent.getPic());
			    map.put("height", 240);
			    map.put("width", 670);
			    map.put("srcB", tbContent.getPic2());
			    map.put("widthB", 550);
			    map.put("heightB", 240);
			    map.put("href", tbContent.getUrl());
			    map.put("alt", tbContent.getSubTitle());	
			    mapList.add(map);
			}
			return JsonUtils.objectToJson(mapList);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
