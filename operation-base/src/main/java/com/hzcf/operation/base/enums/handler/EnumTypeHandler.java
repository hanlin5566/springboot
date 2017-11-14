package com.hzcf.operation.base.enums.handler;

import org.apache.ibatis.type.MappedTypes;

import com.hzcf.operation.base.enums.DataStatus;
import com.hzcf.operation.base.enums.DerivedVarStatus;
import com.hzcf.operation.base.result.ResponseCode;

/**
 * Create by hanlin on 2017年11月7日
 **/
//TODO:有时间修改为扫描包名注册枚举类
@MappedTypes(value = {  DataStatus.class, ResponseCode.class,DerivedVarStatus.class})  
public class EnumTypeHandler<E extends Enum<E>> extends BaseEnumTypeHandler<E> {  
    public EnumTypeHandler(Class<E> type) {  
        super(type);  
    }  
} 
