package com.hzcf.operation.gen.mapper;

import com.hzcf.operation.gen.entity.SystemUser;
import com.hzcf.operation.gen.entity.SystemUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

public interface SystemUserMapper {
    @Delete({
        "delete from cbd_system_user",
        "where userId = #{userid,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String userid);

    @Insert({
        "insert into cbd_system_user (userId, sex, ",
        "userName, apiName, ",
        "apiPwd, deptId, ",
        "userPwd, userSalt, ",
        "userEMail, userTel, ",
        "userAddress, isValid, ",
        "updateTime, createTime, ",
        "updatePerson, createPerson, ",
        "userPhone)",
        "values (#{userid,jdbcType=VARCHAR}, #{sex,jdbcType=VARCHAR}, ",
        "#{username,jdbcType=VARCHAR}, #{apiname,jdbcType=VARCHAR}, ",
        "#{apipwd,jdbcType=VARCHAR}, #{deptid,jdbcType=VARCHAR}, ",
        "#{userpwd,jdbcType=VARCHAR}, #{usersalt,jdbcType=VARCHAR}, ",
        "#{useremail,jdbcType=VARCHAR}, #{usertel,jdbcType=VARCHAR}, ",
        "#{useraddress,jdbcType=VARCHAR}, #{isvalid,jdbcType=CHAR}, ",
        "#{updatetime,jdbcType=DATE}, #{createtime,jdbcType=DATE}, ",
        "#{updateperson,jdbcType=VARCHAR}, #{createperson,jdbcType=VARCHAR}, ",
        "#{userphone,jdbcType=VARCHAR})"
    })
    int insert(SystemUser record);

    @InsertProvider(type=SystemUserSqlProvider.class, method="insertSelective")
    int insertSelective(SystemUser record);

    @SelectProvider(type=SystemUserSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="userId", property="userid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="sex", property="sex", jdbcType=JdbcType.VARCHAR),
        @Result(column="userName", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="apiName", property="apiname", jdbcType=JdbcType.VARCHAR),
        @Result(column="apiPwd", property="apipwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="deptId", property="deptid", jdbcType=JdbcType.VARCHAR),
        @Result(column="userPwd", property="userpwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="userSalt", property="usersalt", jdbcType=JdbcType.VARCHAR),
        @Result(column="userEMail", property="useremail", jdbcType=JdbcType.VARCHAR),
        @Result(column="userTel", property="usertel", jdbcType=JdbcType.VARCHAR),
        @Result(column="userAddress", property="useraddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="isValid", property="isvalid", jdbcType=JdbcType.CHAR),
        @Result(column="updateTime", property="updatetime", jdbcType=JdbcType.DATE),
        @Result(column="createTime", property="createtime", jdbcType=JdbcType.DATE),
        @Result(column="updatePerson", property="updateperson", jdbcType=JdbcType.VARCHAR),
        @Result(column="createPerson", property="createperson", jdbcType=JdbcType.VARCHAR),
        @Result(column="userPhone", property="userphone", jdbcType=JdbcType.VARCHAR)
    })
    List<SystemUser> selectByExampleWithRowbounds(SystemUserExample example, RowBounds rowBounds);

    @SelectProvider(type=SystemUserSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="userId", property="userid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="sex", property="sex", jdbcType=JdbcType.VARCHAR),
        @Result(column="userName", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="apiName", property="apiname", jdbcType=JdbcType.VARCHAR),
        @Result(column="apiPwd", property="apipwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="deptId", property="deptid", jdbcType=JdbcType.VARCHAR),
        @Result(column="userPwd", property="userpwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="userSalt", property="usersalt", jdbcType=JdbcType.VARCHAR),
        @Result(column="userEMail", property="useremail", jdbcType=JdbcType.VARCHAR),
        @Result(column="userTel", property="usertel", jdbcType=JdbcType.VARCHAR),
        @Result(column="userAddress", property="useraddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="isValid", property="isvalid", jdbcType=JdbcType.CHAR),
        @Result(column="updateTime", property="updatetime", jdbcType=JdbcType.DATE),
        @Result(column="createTime", property="createtime", jdbcType=JdbcType.DATE),
        @Result(column="updatePerson", property="updateperson", jdbcType=JdbcType.VARCHAR),
        @Result(column="createPerson", property="createperson", jdbcType=JdbcType.VARCHAR),
        @Result(column="userPhone", property="userphone", jdbcType=JdbcType.VARCHAR)
    })
    List<SystemUser> selectByExample(SystemUserExample example);

    @Select({
        "select",
        "userId, sex, userName, apiName, apiPwd, deptId, userPwd, userSalt, userEMail, ",
        "userTel, userAddress, isValid, updateTime, createTime, updatePerson, createPerson, ",
        "userPhone",
        "from cbd_system_user",
        "where userId = #{userid,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="userId", property="userid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="sex", property="sex", jdbcType=JdbcType.VARCHAR),
        @Result(column="userName", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="apiName", property="apiname", jdbcType=JdbcType.VARCHAR),
        @Result(column="apiPwd", property="apipwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="deptId", property="deptid", jdbcType=JdbcType.VARCHAR),
        @Result(column="userPwd", property="userpwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="userSalt", property="usersalt", jdbcType=JdbcType.VARCHAR),
        @Result(column="userEMail", property="useremail", jdbcType=JdbcType.VARCHAR),
        @Result(column="userTel", property="usertel", jdbcType=JdbcType.VARCHAR),
        @Result(column="userAddress", property="useraddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="isValid", property="isvalid", jdbcType=JdbcType.CHAR),
        @Result(column="updateTime", property="updatetime", jdbcType=JdbcType.DATE),
        @Result(column="createTime", property="createtime", jdbcType=JdbcType.DATE),
        @Result(column="updatePerson", property="updateperson", jdbcType=JdbcType.VARCHAR),
        @Result(column="createPerson", property="createperson", jdbcType=JdbcType.VARCHAR),
        @Result(column="userPhone", property="userphone", jdbcType=JdbcType.VARCHAR)
    })
    SystemUser selectByPrimaryKey(String userid);

    @UpdateProvider(type=SystemUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SystemUser record);

    @Update({
        "update cbd_system_user",
        "set sex = #{sex,jdbcType=VARCHAR},",
          "userName = #{username,jdbcType=VARCHAR},",
          "apiName = #{apiname,jdbcType=VARCHAR},",
          "apiPwd = #{apipwd,jdbcType=VARCHAR},",
          "deptId = #{deptid,jdbcType=VARCHAR},",
          "userPwd = #{userpwd,jdbcType=VARCHAR},",
          "userSalt = #{usersalt,jdbcType=VARCHAR},",
          "userEMail = #{useremail,jdbcType=VARCHAR},",
          "userTel = #{usertel,jdbcType=VARCHAR},",
          "userAddress = #{useraddress,jdbcType=VARCHAR},",
          "isValid = #{isvalid,jdbcType=CHAR},",
          "updateTime = #{updatetime,jdbcType=DATE},",
          "createTime = #{createtime,jdbcType=DATE},",
          "updatePerson = #{updateperson,jdbcType=VARCHAR},",
          "createPerson = #{createperson,jdbcType=VARCHAR},",
          "userPhone = #{userphone,jdbcType=VARCHAR}",
        "where userId = #{userid,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(SystemUser record);
}