package com.hzcf.operation.base.entity;


/**
 * Create by hanlin on 2017年11月7日
 * 接收分页参数的entity
 **/
public class PageEntity{

	private int offset = 0;
	
	private int limit = 10;
	
	public PageEntity() {
		
	}
	
	public PageEntity(int offset, int limit) {
		this.offset = offset;
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public PageInfo toPageInfo() {
		return new PageInfo(offset, limit);
	}
	
	public int getPageNum() {
		if (getLimit() == 0) return 0;
		return getOffset() / getLimit() + 1;
	}
	
	public void setPageNum(int pageNum) {
		setOffset((pageNum - 1) * getLimit());
	}
}
