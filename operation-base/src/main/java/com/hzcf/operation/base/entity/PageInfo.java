package com.hzcf.operation.base.entity;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.apache.ibatis.session.RowBounds;

/**
 * Create by hanlin on 2017年11月7日
 **/
public class PageInfo extends RowBounds implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	private int totalCount = 0;
	
	public PageInfo() {
		super(0, 10);
	}
	
	public PageInfo(Integer offset, Integer limit) {
		super(offset == null ? 0 : offset, limit == null ? 0 : limit);
	}

	private void changeParentValue(String name, int value) {
		Field field = null;
		try {
			field = RowBounds.class.getDeclaredField(name);
			boolean origin = field.isAccessible();
			field.setAccessible(true);
			field.set(this, value);
			field.setAccessible(origin);
		} catch (Exception e) {
		}
	}
	
	public int getTotalPage() {
		if (getLimit() == 0) return 0;
		int total = totalCount / getLimit();
		return (totalCount % getLimit() == 0) ? total : total + 1;
	}

	public boolean hasPrevPage() {
		return getOffset() > 0;
	}

	public boolean hasNextPage() {
		return getPageNum() < getTotalPage();
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void setOffset(int offset) {
		changeParentValue("offset", offset);
	}

	public void setLimit(int limit) {
		changeParentValue("limit", limit);
	}
	
	public int getPageNum() {
		if (getLimit() == 0) return 0;
		return getOffset() / getLimit() + 1;
	}
	
	public void setPageNum(int pageNum) {
		setOffset((pageNum - 1) * getLimit());
	}
}
