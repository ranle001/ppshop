package com.ppshop.rest.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppshop.mapper.TbItemCatMapper;
import com.ppshop.pojo.TbItemCat;
import com.ppshop.rest.pojo.CatNode;
import com.ppshop.rest.pojo.CatResult;
import com.ppshop.rest.service.ItemCatService;

/**
 * 
 * <pre>
 * 商品分类服务。
 * </pre>
 * @author pangkaiguang
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */

@Service
public class ItemCatServiceImpl implements ItemCatService{
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
	@Override
	public CatResult getItemCatList() {
		CatResult catResult = new CatResult();
		catResult.setData(getCatList(0));
		return catResult;
	}
	
	
	private List<?> getCatList(long parentId){
		List<TbItemCat> tbItemCatList= tbItemCatMapper.getCatList(parentId); 
		List resultList = new ArrayList();
		for (TbItemCat tbItemCat : tbItemCatList){
			//判断是否为叶子节点
			CatNode catNode = new CatNode();
			if (tbItemCat.getIsParent()){
				if (parentId == 0){
					catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				} else {
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/"+tbItemCat.getId()+".html");
				catNode.setItem(getCatList(tbItemCat.getId()));
				resultList.add(catNode);
			} else {
				resultList.add("/product/"+tbItemCat.getId()+".html | "+tbItemCat.getName());
			}
		}
		return resultList;
	}
}
