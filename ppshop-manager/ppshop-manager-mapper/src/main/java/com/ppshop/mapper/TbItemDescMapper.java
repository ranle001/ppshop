package com.ppshop.mapper;

import com.ppshop.pojo.TbItemDesc;



public interface TbItemDescMapper {
	void insertItemDesc(TbItemDesc desc);
	TbItemDesc quertyItemDescByItemId(long itemId);
}