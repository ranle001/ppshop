package com.ppshop.rest.service.Impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ppshop.common.utils.JsonUtils;
import com.ppshop.mapper.TbContentMapper;
import com.ppshop.pojo.TbContent;
import com.ppshop.rest.service.ContentService;
import com.ppshop.rest.service.JedisClient;

/**
 * 
 * <pre>
 * 内容管理
 * </pre>
 * @author pankaiguang
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
@Service
public class ContentServiceImpl implements ContentService{
	
	@Autowired
	private TbContentMapper tbContentMapper;
	
	@Autowired
	private JedisClient jedisClientSingle;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	@Override
	public List<TbContent> getContentList(long contentCid) {
		try {
			String result = jedisClientSingle.hget(INDEX_CONTENT_REDIS_KEY, contentCid+"");
			if (StringUtils.isNotBlank(result)){
				List<TbContent> tbContents = JsonUtils.jsonToList(result, TbContent.class);
				return tbContents;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<TbContent> tbContents = tbContentMapper.getCategoryByCategoryId(contentCid);
		String cacheString = JsonUtils.objectToJson(tbContents);
		jedisClientSingle.hset(INDEX_CONTENT_REDIS_KEY , contentCid+"", cacheString);
		return tbContents;
	}

}
