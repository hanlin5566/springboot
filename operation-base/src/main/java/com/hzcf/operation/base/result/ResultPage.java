package com.hzcf.operation.base.result;

import java.util.ArrayList;
import java.util.List;

import com.hzcf.operation.base.entity.PageInfo;
import com.hzcf.operation.base.util.BeanUtils;

public class ResultPage<T> extends Result<List<T>> {

	private PageInfo pageInfo = null;
	
	public ResultPage() {
		this(new ArrayList<T>());
	}
	
	public ResultPage(List<T> data) {
		super.setData(data);
	}
	
	public ResultPage(PageInfo pageInfo) {
		this(new ArrayList<T>());
		this.pageInfo = BeanUtils.copyProperties(pageInfo, PageInfo.class);
	}
	
	public <V> ResultPage(ResultPage<V> data, Class<T> type) {
		super.setData(BeanUtils.copyListProperties(data.getData(), type));
		this.pageInfo = BeanUtils.copyProperties(data.getPageInfo(), PageInfo.class);
	}
	
	public ResultPage(List<T> data, PageInfo pageInfo) {
		super.setData(data);
		this.pageInfo = BeanUtils.copyProperties(pageInfo, PageInfo.class);
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = BeanUtils.copyProperties(pageInfo, PageInfo.class);
	}

    public ResultPage<T> addItem(T obj) {
    	getData().add(obj);
    	return this;
    }
}