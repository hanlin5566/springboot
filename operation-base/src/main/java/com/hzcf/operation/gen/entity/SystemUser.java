package com.hzcf.operation.gen.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hzcf.operation.base.enums.DataStatus;
import com.hzcf.operation.base.serialize.EnumJsonSerializer;
import com.hzcf.operation.base.util.DateUtils;

/**
 * cbd_system_user 
 * @author huhanlin 2017-11-14
 */
public class SystemUser {
    /**
     * 用户id
     */
    private String userid;

    /**
     * 
     */
    private String sex;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String apiname;

    /**
     * 
     */
    private String apipwd;

    /**
     * 
     */
    private String deptid;

    /**
     * 
     */
    private String userpwd;

    /**
     * 
     */
    private String usersalt;

    /**
     * 
     */
    private String useremail;

    /**
     * 
     */
    private String usertel;

    /**
     * 
     */
    private String useraddress;

    /**
     * 
     */
    @JsonSerialize(using = EnumJsonSerializer.class)
    private DataStatus isvalid;

    /**
     * 
     */
    @DateTimeFormat(pattern = DateUtils.ISO_DATE)
    private Date updatetime;

    /**
     * 
     */
    @DateTimeFormat(pattern = DateUtils.ISO_DATE)
    private Date createtime;

    /**
     * 
     */
    private String updateperson;

    /**
     * 
     */
    private String createperson;

    /**
     * 
     */
    private String userphone;

    /**
     * 用户id
     * @return userId 用户id
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 用户id
     * @param userid 用户id
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * 
     * @return sex 
     */
    public String getSex() {
        return sex;
    }

    /**
     * 
     * @param sex 
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 
     * @return userName 
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 
     * @return apiName 
     */
    public String getApiname() {
        return apiname;
    }

    /**
     * 
     * @param apiname 
     */
    public void setApiname(String apiname) {
        this.apiname = apiname == null ? null : apiname.trim();
    }

    /**
     * 
     * @return apiPwd 
     */
    public String getApipwd() {
        return apipwd;
    }

    /**
     * 
     * @param apipwd 
     */
    public void setApipwd(String apipwd) {
        this.apipwd = apipwd == null ? null : apipwd.trim();
    }

    /**
     * 
     * @return deptId 
     */
    public String getDeptid() {
        return deptid;
    }

    /**
     * 
     * @param deptid 
     */
    public void setDeptid(String deptid) {
        this.deptid = deptid == null ? null : deptid.trim();
    }

    /**
     * 
     * @return userPwd 
     */
    public String getUserpwd() {
        return userpwd;
    }

    /**
     * 
     * @param userpwd 
     */
    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd == null ? null : userpwd.trim();
    }

    /**
     * 
     * @return userSalt 
     */
    public String getUsersalt() {
        return usersalt;
    }

    /**
     * 
     * @param usersalt 
     */
    public void setUsersalt(String usersalt) {
        this.usersalt = usersalt == null ? null : usersalt.trim();
    }

    /**
     * 
     * @return userEMail 
     */
    public String getUseremail() {
        return useremail;
    }

    /**
     * 
     * @param useremail 
     */
    public void setUseremail(String useremail) {
        this.useremail = useremail == null ? null : useremail.trim();
    }

    /**
     * 
     * @return userTel 
     */
    public String getUsertel() {
        return usertel;
    }

    /**
     * 
     * @param usertel 
     */
    public void setUsertel(String usertel) {
        this.usertel = usertel == null ? null : usertel.trim();
    }

    /**
     * 
     * @return userAddress 
     */
    public String getUseraddress() {
        return useraddress;
    }

    /**
     * 
     * @param useraddress 
     */
    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress == null ? null : useraddress.trim();
    }

    /**
     * 
     * @return isValid 
     */
    public DataStatus getIsvalid() {
        return isvalid;
    }

    /**
     * 
     * @param isvalid 
     */
    public void setIsvalid(DataStatus isvalid) {
        this.isvalid = isvalid;
    }

    /**
     * 
     * @return updateTime 
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 
     * @param updatetime 
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 
     * @return createTime 
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 
     * @param createtime 
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 
     * @return updatePerson 
     */
    public String getUpdateperson() {
        return updateperson;
    }

    /**
     * 
     * @param updateperson 
     */
    public void setUpdateperson(String updateperson) {
        this.updateperson = updateperson == null ? null : updateperson.trim();
    }

    /**
     * 
     * @return createPerson 
     */
    public String getCreateperson() {
        return createperson;
    }

    /**
     * 
     * @param createperson 
     */
    public void setCreateperson(String createperson) {
        this.createperson = createperson == null ? null : createperson.trim();
    }

    /**
     * 
     * @return userPhone 
     */
    public String getUserphone() {
        return userphone;
    }

    /**
     * 
     * @param userphone 
     */
    public void setUserphone(String userphone) {
        this.userphone = userphone == null ? null : userphone.trim();
    }
}