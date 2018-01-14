package com.ppshop.mapper;

import java.util.List;

import com.ppshop.common.pojp.EUTreeNode;
import com.ppshop.pojo.TbItemCat;


public interface TbItemCatMapper {
	List<TbItemCat> getCatList(long parentId);
}