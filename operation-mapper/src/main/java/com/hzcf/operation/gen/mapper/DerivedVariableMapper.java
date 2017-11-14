package com.hzcf.operation.gen.mapper;

import com.hzcf.operation.gen.entity.DerivedVariable;
import com.hzcf.operation.gen.entity.DerivedVariableExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

public interface DerivedVariableMapper {
    @Delete({
        "delete from t_derived_var",
        "where var_id = #{varId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer varId);

    @Insert({
        "insert into t_derived_var (var_name, description, ",
        "var_code, query_iface, ",
        "clazz_name, class_path, ",
        "state, data_status, ",
        "deploy_time, c_uid, ",
        "c_time, u_uid, u_time)",
        "values (#{varName,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{varCode,jdbcType=VARCHAR}, #{queryIface,jdbcType=VARCHAR}, ",
        "#{clazzName,jdbcType=VARCHAR}, #{classPath,jdbcType=VARCHAR}, ",
        "#{state,jdbcType=INTEGER}, #{dataStatus,jdbcType=INTEGER}, ",
        "#{deployTime,jdbcType=TIMESTAMP}, #{cUid,jdbcType=INTEGER}, ",
        "#{cTime,jdbcType=TIMESTAMP}, #{uUid,jdbcType=INTEGER}, #{uTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="varId", before=false, resultType=Integer.class)
    int insert(DerivedVariable record);

    @InsertProvider(type=DerivedVariableSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="varId", before=false, resultType=Integer.class)
    int insertSelective(DerivedVariable record);

    @SelectProvider(type=DerivedVariableSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="var_id", property="varId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="var_name", property="varName", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="var_code", property="varCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="query_iface", property="queryIface", jdbcType=JdbcType.VARCHAR),
        @Result(column="clazz_name", property="clazzName", jdbcType=JdbcType.VARCHAR),
        @Result(column="class_path", property="classPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="data_status", property="dataStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="deploy_time", property="deployTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="c_uid", property="cUid", jdbcType=JdbcType.INTEGER),
        @Result(column="c_time", property="cTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="u_uid", property="uUid", jdbcType=JdbcType.INTEGER),
        @Result(column="u_time", property="uTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<DerivedVariable> selectByExampleWithRowbounds(DerivedVariableExample example, RowBounds rowBounds);

    @SelectProvider(type=DerivedVariableSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="var_id", property="varId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="var_name", property="varName", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="var_code", property="varCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="query_iface", property="queryIface", jdbcType=JdbcType.VARCHAR),
        @Result(column="clazz_name", property="clazzName", jdbcType=JdbcType.VARCHAR),
        @Result(column="class_path", property="classPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="data_status", property="dataStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="deploy_time", property="deployTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="c_uid", property="cUid", jdbcType=JdbcType.INTEGER),
        @Result(column="c_time", property="cTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="u_uid", property="uUid", jdbcType=JdbcType.INTEGER),
        @Result(column="u_time", property="uTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<DerivedVariable> selectByExample(DerivedVariableExample example);

    @Select({
        "select",
        "var_id, var_name, description, var_code, query_iface, clazz_name, class_path, ",
        "state, data_status, deploy_time, c_uid, c_time, u_uid, u_time",
        "from t_derived_var",
        "where var_id = #{varId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="var_id", property="varId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="var_name", property="varName", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="var_code", property="varCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="query_iface", property="queryIface", jdbcType=JdbcType.VARCHAR),
        @Result(column="clazz_name", property="clazzName", jdbcType=JdbcType.VARCHAR),
        @Result(column="class_path", property="classPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="state", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="data_status", property="dataStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="deploy_time", property="deployTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="c_uid", property="cUid", jdbcType=JdbcType.INTEGER),
        @Result(column="c_time", property="cTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="u_uid", property="uUid", jdbcType=JdbcType.INTEGER),
        @Result(column="u_time", property="uTime", jdbcType=JdbcType.TIMESTAMP)
    })
    DerivedVariable selectByPrimaryKey(Integer varId);

    @UpdateProvider(type=DerivedVariableSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(DerivedVariable record);

    @Update({
        "update t_derived_var",
        "set var_name = #{varName,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "var_code = #{varCode,jdbcType=VARCHAR},",
          "query_iface = #{queryIface,jdbcType=VARCHAR},",
          "clazz_name = #{clazzName,jdbcType=VARCHAR},",
          "class_path = #{classPath,jdbcType=VARCHAR},",
          "state = #{state,jdbcType=INTEGER},",
          "data_status = #{dataStatus,jdbcType=INTEGER},",
          "deploy_time = #{deployTime,jdbcType=TIMESTAMP},",
          "c_uid = #{cUid,jdbcType=INTEGER},",
          "c_time = #{cTime,jdbcType=TIMESTAMP},",
          "u_uid = #{uUid,jdbcType=INTEGER},",
          "u_time = #{uTime,jdbcType=TIMESTAMP}",
        "where var_id = #{varId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(DerivedVariable record);
}