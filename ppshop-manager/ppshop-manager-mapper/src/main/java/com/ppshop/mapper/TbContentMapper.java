package com.ppshop.mapper;

import java.util.List;

import com.ppshop.pojo.TbContent;


public interface TbContentMapper {
	List<TbContent> getCategory();
	
	List<TbContent> getCategoryByCategoryId(long categoryId);
	
	void insertContent(TbContent tbContent);
	
	void updateContent(TbContent tbContent);
}