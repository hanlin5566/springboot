package com.hzcf.operation.gen.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hzcf.operation.base.enums.DataStatus;
import com.hzcf.operation.base.enums.DerivedVarStatus;
import com.hzcf.operation.base.serialize.EnumJsonSerializer;
import com.hzcf.operation.base.util.DateUtils;

/**
 * t_derived_var 
 * @author huhanlin 2017-11-14
 */
public class DerivedVariable {
    /**
     * 衍生变量主键
     */
    private Integer varId;

    /**
     * 衍生变量名称
     */
    private String varName;

    /**
     * 衍生变量描述
     */
    private String description;

    /**
     * 衍生变量代码
     */
    private String varCode;

    /**
     * 取数接口名称
     */
    private String queryIface;

    /**
     * 衍生变量需要加载的类名
     */
    private String clazzName;

    /**
     * 算法类保存的路径，新建与已编译时为本地地址，发布时为文件服务器地址。
     */
    private String clazzPath;

    /**
     * 衍生变量状态(0:未知,1：已保存，2：通过编译，3：已发布）
     */
    @JsonSerialize(using = EnumJsonSerializer.class)
    private DerivedVarStatus state;

    /**
     * 数据状态（0.未知，1.正常，-1.删除）
     */
    @JsonSerialize(using = EnumJsonSerializer.class)
    private DataStatus dataStatus;

    /**
     * 发布日期
     */
    @DateTimeFormat(pattern = DateUtils.ISO_DATE)
    private Date deployTime;

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
    @DateTimeFormat(pattern = DateUtils.ISO_DATE)
    private Date uTime;

    /**
     * 衍生变量主键
     * @return var_id 衍生变量主键
     */
    public Integer getVarId() {
        return varId;
    }

    /**
     * 衍生变量主键
     * @param varId 衍生变量主键
     */
    public void setVarId(Integer varId) {
        this.varId = varId;
    }

    /**
     * 衍生变量名称
     * @return var_name 衍生变量名称
     */
    public String getVarName() {
        return varName;
    }

    /**
     * 衍生变量名称
     * @param varName 衍生变量名称
     */
    public void setVarName(String varName) {
        this.varName = varName == null ? null : varName.trim();
    }

    /**
     * 衍生变量描述
     * @return description 衍生变量描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 衍生变量描述
     * @param description 衍生变量描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 衍生变量代码
     * @return var_code 衍生变量代码
     */
    public String getVarCode() {
        return varCode;
    }

    /**
     * 衍生变量代码
     * @param varCode 衍生变量代码
     */
    public void setVarCode(String varCode) {
        this.varCode = varCode == null ? null : varCode.trim();
    }

    /**
     * 取数接口名称
     * @return query_iface 取数接口名称
     */
    public String getQueryIface() {
        return queryIface;
    }

    /**
     * 取数接口名称
     * @param queryIface 取数接口名称
     */
    public void setQueryIface(String queryIface) {
        this.queryIface = queryIface == null ? null : queryIface.trim();
    }

    /**
     * 衍生变量需要加载的类名
     * @return clazz_name 衍生变量需要加载的类名
     */
    public String getClazzName() {
        return clazzName;
    }

    /**
     * 衍生变量需要加载的类名
     * @param clazzName 衍生变量需要加载的类名
     */
    public void setClazzName(String clazzName) {
        this.clazzName = clazzName == null ? null : clazzName.trim();
    }

    /**
     * 算法类保存的路径，新建与已编译时为本地地址，发布时为文件服务器地址。
     * @return clazz_path 算法类保存的路径，新建与已编译时为本地地址，发布时为文件服务器地址。
     */
    public String getClazzPath() {
        return clazzPath;
    }

    /**
     * 算法类保存的路径，新建与已编译时为本地地址，发布时为文件服务器地址。
     * @param clazzPath 算法类保存的路径，新建与已编译时为本地地址，发布时为文件服务器地址。
     */
    public void setClazzPath(String clazzPath) {
        this.clazzPath = clazzPath == null ? null : clazzPath.trim();
    }

    /**
     * 衍生变量状态(0:未知,1：已保存，2：通过编译，3：已发布）
     * @return state 衍生变量状态(0:未知,1：已保存，2：通过编译，3：已发布）
     */
    public DerivedVarStatus getState() {
        return state;
    }

    /**
     * 衍生变量状态(0:未知,1：已保存，2：通过编译，3：已发布）
     * @param state 衍生变量状态(0:未知,1：已保存，2：通过编译，3：已发布）
     */
    public void setState(DerivedVarStatus state) {
        this.state = state;
    }

    /**
     * 数据状态（0.未知，1.正常，-1.删除）
     * @return data_status 数据状态（0.未知，1.正常，-1.删除）
     */
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
     * 发布日期
     * @return deploy_time 发布日期
     */
    public Date getDeployTime() {
        return deployTime;
    }

    /**
     * 发布日期
     * @param deployTime 发布日期
     */
    public void setDeployTime(Date deployTime) {
        this.deployTime = deployTime;
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