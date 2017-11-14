package com.hzcf.operation.gen.mapper;

import com.hzcf.operation.gen.entity.DerivedVariable;
import com.hzcf.operation.gen.entity.DerivedVariableExample.Criteria;
import com.hzcf.operation.gen.entity.DerivedVariableExample.Criterion;
import com.hzcf.operation.gen.entity.DerivedVariableExample;
import java.util.List;
import org.apache.ibatis.jdbc.SQL;

public class DerivedVariableSqlProvider {

    public String insertSelective(DerivedVariable record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_derived_var");
        
        if (record.getVarName() != null) {
            sql.VALUES("var_name", "#{varName,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=VARCHAR}");
        }
        
        if (record.getVarCode() != null) {
            sql.VALUES("var_code", "#{varCode,jdbcType=VARCHAR}");
        }
        
        if (record.getQueryIface() != null) {
            sql.VALUES("query_iface", "#{queryIface,jdbcType=VARCHAR}");
        }
        
        if (record.getClazzName() != null) {
            sql.VALUES("clazz_name", "#{clazzName,jdbcType=VARCHAR}");
        }
        
        if (record.getClazzPath() != null) {
            sql.VALUES("clazz_path", "#{classPath,jdbcType=VARCHAR}");
        }
        
        if (record.getState() != null) {
            sql.VALUES("state", "#{state,jdbcType=INTEGER}");
        }
        
        if (record.getDataStatus() != null) {
            sql.VALUES("data_status", "#{dataStatus,jdbcType=INTEGER}");
        }
        
        if (record.getDeployTime() != null) {
            sql.VALUES("deploy_time", "#{deployTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getcUid() != null) {
            sql.VALUES("c_uid", "#{cUid,jdbcType=INTEGER}");
        }
        
        if (record.getcTime() != null) {
            sql.VALUES("c_time", "#{cTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getuUid() != null) {
            sql.VALUES("u_uid", "#{uUid,jdbcType=INTEGER}");
        }
        
        if (record.getuTime() != null) {
            sql.VALUES("u_time", "#{uTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String selectByExample(DerivedVariableExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("var_id");
        } else {
            sql.SELECT("var_id");
        }
        sql.SELECT("var_name");
        sql.SELECT("description");
        sql.SELECT("var_code");
        sql.SELECT("query_iface");
        sql.SELECT("clazz_name");
        sql.SELECT("class_path");
        sql.SELECT("state");
        sql.SELECT("data_status");
        sql.SELECT("deploy_time");
        sql.SELECT("c_uid");
        sql.SELECT("c_time");
        sql.SELECT("u_uid");
        sql.SELECT("u_time");
        sql.FROM("t_derived_var");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(DerivedVariable record) {
        SQL sql = new SQL();
        sql.UPDATE("t_derived_var");
        
        if (record.getVarName() != null) {
            sql.SET("var_name = #{varName,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=VARCHAR}");
        }
        
        if (record.getVarCode() != null) {
            sql.SET("var_code = #{varCode,jdbcType=VARCHAR}");
        }
        
        if (record.getQueryIface() != null) {
            sql.SET("query_iface = #{queryIface,jdbcType=VARCHAR}");
        }
        
        if (record.getClazzName() != null) {
            sql.SET("clazz_name = #{clazzName,jdbcType=VARCHAR}");
        }
        
        if (record.getClazzPath() != null) {
            sql.SET("clazz_path = #{classPath,jdbcType=VARCHAR}");
        }
        
        if (record.getState() != null) {
            sql.SET("state = #{state,jdbcType=INTEGER}");
        }
        
        if (record.getDataStatus() != null) {
            sql.SET("data_status = #{dataStatus,jdbcType=INTEGER}");
        }
        
        if (record.getDeployTime() != null) {
            sql.SET("deploy_time = #{deployTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getcUid() != null) {
            sql.SET("c_uid = #{cUid,jdbcType=INTEGER}");
        }
        
        if (record.getcTime() != null) {
            sql.SET("c_time = #{cTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getuUid() != null) {
            sql.SET("u_uid = #{uUid,jdbcType=INTEGER}");
        }
        
        if (record.getuTime() != null) {
            sql.SET("u_time = #{uTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("var_id = #{varId,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, DerivedVariableExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}