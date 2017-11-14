package com.hzcf.operation.base.entity;

import com.hzcf.operation.gen.entity.DerivedVariable;

/**
 * Create by hanlin on 2017年11月13日
 * DerivedVariable的扩展，添加了content字段，用于详情页显示文件内容。
 **/
public class DerivedVariableExt extends DerivedVariable{
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
