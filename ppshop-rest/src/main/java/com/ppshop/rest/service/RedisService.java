package com.ppshop.rest.service;

import com.ppshop.common.pojo.PpShopResult;

public interface RedisService {
	PpShopResult syncContent(long contentCid);
}
