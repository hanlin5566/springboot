package com.hzcf.operation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hzcf.operation.base.enums.DataStatus;
import com.hzcf.operation.gen.entity.DerivedVariable;
import com.hzcf.operation.gen.entity.DerivedVariableExample;
import com.hzcf.operation.gen.mapper.DerivedVariableMapper;
import com.hzcf.operation.inter.DerivedVariableService;

/**
 * Create by hanlin on 2017年11月7日
 **/
public class DerivedVariableServiceImpl implements DerivedVariableService{
	
	@Autowired
	private DerivedVariableMapper derivedVariableMapper;
	
	@Override
	public List<DerivedVariable> getDerivedVariableList(DerivedVariable derivedVar) {
		DerivedVariableExample example = new DerivedVariableExample();
		example.createCriteria().andDataStatusEqualTo(DataStatus.NORMAL);
		derivedVariableMapper.selectByExample(example);
		return null;
	}
	
}
