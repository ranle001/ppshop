package com.ppshop.search.pojo;

import java.util.List;

/**
 * 搜索通用实体类
 * @author Administrator
 *
 */
public class SearchResult {
	//商品列表
	private List<SearchItem> iSearchItems;
	//总记录数
	private long recordCount;
	//总页数
	private long pageCount;
	//当前页
	private long curPage;
	public List<SearchItem> getiSearchItems() {
		return iSearchItems;
	}
	public void setiSearchItems(List<SearchItem> iSearchItems) {
		this.iSearchItems = iSearchItems;
	}
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public long getPageCount() {
		return pageCount;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	public long getCurPage() {
		return curPage;
	}
	public void setCurPage(long curPage) {
		this.curPage = curPage;
	}
	
}
