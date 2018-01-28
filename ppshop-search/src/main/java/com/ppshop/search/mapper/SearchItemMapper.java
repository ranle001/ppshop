package com.ppshop.search.mapper;

import java.util.List;

import com.ppshop.search.pojo.SearchItem;

public interface SearchItemMapper {
	List<SearchItem> getItemList();
	List<SearchItem> getItemListById(Long id);
}
