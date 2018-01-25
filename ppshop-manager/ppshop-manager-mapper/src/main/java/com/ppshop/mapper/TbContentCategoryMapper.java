package com.ppshop.mapper;

import java.util.List;

import com.ppshop.pojo.TbContentCategory;


public interface TbContentCategoryMapper {
	List<TbContentCategory> getCategoryByParentId(long parentId);
	
	TbContentCategory getCategoryById(long id);
	
	void insertContentCategory(TbContentCategory tbContentCategory);
	
	void updateContentCategoryById(TbContentCategory tbContentCategory);
	
	void removeContentCategory(long id);
}