package com.ppshop.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppshop.common.pojo.EUTreeNode;
import com.ppshop.common.pojo.PpShopResult;
import com.ppshop.mapper.TbContentCategoryMapper;
import com.ppshop.pojo.TbContentCategory;
import com.ppshop.service.ContentCategoryService;

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
public class ContentCategoryServiceImpl implements ContentCategoryService{
	
	@Autowired
	TbContentCategoryMapper tbContentCategoryMapper;
	
	@Override
	public List<EUTreeNode> getCategoryList(long parentId) {
		List<EUTreeNode> resultList = new ArrayList<EUTreeNode>();
		List<TbContentCategory> tbContentCategoryList = tbContentCategoryMapper.getCategoryByParentId(parentId);
		for (TbContentCategory tbContentCategory : tbContentCategoryList){
			EUTreeNode euTreeNode = new EUTreeNode();
			euTreeNode.setId(tbContentCategory.getId());
			euTreeNode.setText(tbContentCategory.getName());
			euTreeNode.setState(tbContentCategory.getIsParent()?"closed":"open");
			resultList.add(euTreeNode);
		}
		return resultList;
	}

	@Override
	public PpShopResult insertContentCategory(long parentId, String name) {
		TbContentCategory tbContentCategory = new TbContentCategory();
		//主键是自增长的，先插入返回得到主键select last_insert_id();
        tbContentCategory.setName(name);
        tbContentCategory.setIsParent(false);
        tbContentCategory.setStatus(1);
        tbContentCategory.setParentId(parentId);
        tbContentCategory.setSortOrder(1);
		tbContentCategory.setCreated(new Date());
		tbContentCategory.setUpdated(new Date());
		tbContentCategoryMapper.insertContentCategory(tbContentCategory);
		//查看父节点isParent是否为true，不是就改为true
		TbContentCategory parentCat = tbContentCategoryMapper.getCategoryById(parentId);
		if (!parentCat.getIsParent()){
			parentCat.setIsParent(true);
			//更新
			tbContentCategoryMapper.updateContentCategoryById(parentCat);
		}
		return PpShopResult.ok(tbContentCategory);
	}

	@Override
	public PpShopResult removeContentCategory(long id) {
		TbContentCategory contCat = tbContentCategoryMapper.getCategoryById(id);
		Long parentId = contCat.getParentId();
		tbContentCategoryMapper.removeContentCategory(id);
		List<TbContentCategory> conList = tbContentCategoryMapper.getCategoryByParentId(parentId);
		if (conList.size() == 0){
			TbContentCategory parentCat = tbContentCategoryMapper.getCategoryById(parentId);
			parentCat.setIsParent(false);
			tbContentCategoryMapper.updateContentCategoryById(parentCat);
		}
		return PpShopResult.ok();
	}

	@Override
	public PpShopResult updateContentCategoryName(long id, String name) {
		TbContentCategory tbContentCategory = tbContentCategoryMapper.getCategoryById(id);
		tbContentCategory.setName(name);
		tbContentCategoryMapper.updateContentCategoryById(tbContentCategory);
		return PpShopResult.ok();	
	}
	
}
