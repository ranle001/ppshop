package com.ppshop.mapper;

import java.util.List;

import com.ppshop.pojo.TbItem;


public interface TbItemMapper {
	TbItem getItemById(long itemId);
	List<TbItem> getItem();
	void insertItem(TbItem tbItem);
}