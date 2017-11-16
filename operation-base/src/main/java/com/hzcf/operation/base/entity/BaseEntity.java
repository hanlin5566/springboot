package com.hzcf.operation.base.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hzcf.operation.base.enums.DataStatus;
import com.hzcf.operation.base.serialize.EnumJsonSerializer;
import com.hzcf.operation.base.util.DateUtils;

/**
 * Create by hanlin on 2017年11月8日
 *  TODO:1.Entity的枚举返回给前台时需要添加@JsonSerialize(using = EnumJsonSerializer.class)，否则返回给前端的为Text。
 *  	 2.添加枚举时需要在EnumTypeHandler中，添加相应枚举类。（后期将扫描修改为包扫描）
 *  	 3.日期类型需要添加@DateTimeFormat(pattern = DateUtils.ISO_DATE)注解，否则接收的字符日期类型，无法传入controller。
 *  	 4.有时间需要将mybatis-gentrator生成bean自动根据参数类型，添加相应注解。 done ，但需要引包。
 *  	 5.插入或者更新时空字段不传递至持久层
 *       6.统一的日志管理，目前BaseEnumTypeHandler有无法处理的枚举时，有散落的异常。PageinnationInterceptor分页拦截器有散落的异常
 *         Reflections 反射的帮助类，有散落的异常。
 **/
public class BaseEntity {
	/**
     * 数据状态（0.未知，1.正常，-1.删除）
     */
    @JsonSerialize(using = EnumJsonSerializer.class)
    private DataStatus dataStatus;

    /**
     * 创建用户
     */
    private Integer cUid;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = DateUtils.ISO_DATE)
    private Date cTime;

    /**
     * 修改人
     */
    private Integer uUid;

    /**
     * 修改时间
     */
    private Date uTime;

    /**
     * 数据状态（0.未知，1.正常，-1.删除）
     * @return data_status 数据状态（0.未知，1.正常，-1.删除）
     */
    @JsonSerialize(using = EnumJsonSerializer.class)
    public DataStatus getDataStatus() {
        return dataStatus;
    }

    /**
     * 数据状态（0.未知，1.正常，-1.删除）
     * @param dataStatus 数据状态（0.未知，1.正常，-1.删除）
     */
    public void setDataStatus(DataStatus dataStatus) {
        this.dataStatus = dataStatus;
    }

    /**
     * 创建用户
     * @return c_uid 创建用户
     */
    public Integer getcUid() {
        return cUid;
    }

    /**
     * 创建用户
     * @param cUid 创建用户
     */
    public void setcUid(Integer cUid) {
        this.cUid = cUid;
    }

    /**
     * 创建时间
     * @return c_time 创建时间
     */
    public Date getcTime() {
        return cTime;
    }

    /**
     * 创建时间
     * @param cTime 创建时间
     */
    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    /**
     * 修改人
     * @return u_uid 修改人
     */
    public Integer getuUid() {
        return uUid;
    }

    /**
     * 修改人
     * @param uUid 修改人
     */
    public void setuUid(Integer uUid) {
        this.uUid = uUid;
    }

    /**
     * 修改时间
     * @return u_time 修改时间
     */
    public Date getuTime() {
        return uTime;
    }

    /**
     * 修改时间
     * @param uTime 修改时间
     */
    public void setuTime(Date uTime) {
        this.uTime = uTime;
    }    
}
