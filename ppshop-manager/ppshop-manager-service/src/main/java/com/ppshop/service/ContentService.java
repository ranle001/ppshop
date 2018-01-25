package com.ppshop.service;

import com.ppshop.common.pojo.EUDataGridResult;
import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.pojo.TbContent;

public interface ContentService {
	
	EUDataGridResult getContentList(int page, int rows, long categoryId);
	PpShopResult insertContent(TbContent tbContent);
	PpShopResult updateContent(TbContent tbContent);
}
