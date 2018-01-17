package com.ppshop.mapper;

import java.util.List;

import com.ppshop.pojo.TbItemCat;


public interface TbItemCatMapper {
	List<TbItemCat> getCatList(long parentId);
}