package com.ppshop.service.impl;


import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ppshop.common.pojo.EUDataGridResult;
import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.common.utils.HttpClientUtil;
import com.ppshop.mapper.TbContentMapper;
import com.ppshop.pojo.TbContent;
import com.ppshop.pojo.TbItemParam;
import com.ppshop.service.ContentService;

/**
 * 内容分类管理
 * <pre>
 * 程序的中文名称。
 * </pre>
 * @author pangkaiguang
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
@Service
public class ContentServiceImpl implements ContentService{
	private static final Logger logger = LoggerFactory.getLogger(ContentServiceImpl.class);
	
	@Autowired
	private TbContentMapper tbContentMapper;
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;
	
	@Override
	public EUDataGridResult getContentList(int page, int rows, long categoryId) {
		PageHelper.startPage(page, rows);
		List<TbContent> tList = null;
		if (categoryId == -1){
			tList = tbContentMapper.getCategory();
		}else{
			tList = tbContentMapper.getCategoryByCategoryId(categoryId);
		}
		EUDataGridResult euDataGridResult = new EUDataGridResult();
		euDataGridResult.setRows(tList);
		PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>();
		euDataGridResult.setTotal(pageInfo.getTotal());
		return euDataGridResult;
	}

	@Override
	public PpShopResult insertContent(TbContent tbContent) {
		tbContent.setUpdated(new Date());
		tbContent.setCreated(new Date());
		tbContentMapper.insertContent(tbContent);
		this.cleanCache(tbContent.getCategoryId());
		return PpShopResult.ok();
	}
	
	@Override
	public PpShopResult updateContent(TbContent tbContent) {
		tbContent.setUpdated(new Date());
		tbContentMapper.updateContent(tbContent);
		this.cleanCache(tbContent.getCategoryId());
		return PpShopResult.ok();
	}
	
	/**
	 * 清空缓存
	 * @param categoryId
	 */
	public void cleanCache(Long categoryId){
		logger.info("根据分类ID："+categoryId+"清空缓存!");
		HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL + "/" + categoryId);
	}

}
