package com.company.utils;

/**
 * 分页类
 * 创建时间：2014年6月28日
 */
public class Page {
	
	private int showCount; //每页显示记录数
	private int currentPage;	//当前页
	private PageData pd = new PageData();

	public Page(){
		try {
			this.showCount = Integer.parseInt(Const.PAGE);
		} catch (Exception e) {
			this.showCount = 10;
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getShowCount() {
		return showCount;
	}
	
	public void setShowCount(int showCount) {
		
		this.showCount = showCount;
	}
	
	public PageData getPd() {
		return pd;
	}

	public void setPd(PageData pd) {
		this.pd = pd;
	}
	
}
