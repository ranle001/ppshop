package com.ppshop.rest.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.common.utils.ExceptionUtil;
import com.ppshop.rest.service.JedisClient;
import com.ppshop.rest.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {
	
	@Autowired
	private JedisClient jedisClientSingle;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	@Override
	public PpShopResult syncContent(long contentCid) {
		try {
			jedisClientSingle.hdel(INDEX_CONTENT_REDIS_KEY, contentCid+"");
			return PpShopResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return PpShopResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

}
