package com.ppshop.rest.service;

import java.util.List;

import com.ppshop.pojo.TbContent;

public interface ContentService {
	List<TbContent> getContentList(long contentCid);
}
