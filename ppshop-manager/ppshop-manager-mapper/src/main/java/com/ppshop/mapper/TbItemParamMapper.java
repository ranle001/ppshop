package com.ppshop.mapper;

import java.util.List;

import com.ppshop.pojo.TbItemParam;


public interface TbItemParamMapper {
	List<TbItemParam> getItemParamList();
	TbItemParam getItemParamByCid(Long cid);
	void insertItemParam(TbItemParam tbItemParam);
}