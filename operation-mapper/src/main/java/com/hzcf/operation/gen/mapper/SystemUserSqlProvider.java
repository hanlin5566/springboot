package com.hzcf.operation.gen.mapper;

import com.hzcf.operation.gen.entity.SystemUser;
import com.hzcf.operation.gen.entity.SystemUserExample.Criteria;
import com.hzcf.operation.gen.entity.SystemUserExample.Criterion;
import com.hzcf.operation.gen.entity.SystemUserExample;
import java.util.List;
import org.apache.ibatis.jdbc.SQL;

public class SystemUserSqlProvider {

    public String insertSelective(SystemUser record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("cbd_system_user");
        
        if (record.getUserid() != null) {
            sql.VALUES("userId", "#{userid,jdbcType=VARCHAR}");
        }
        
        if (record.getSex() != null) {
            sql.VALUES("sex", "#{sex,jdbcType=VARCHAR}");
        }
        
        if (record.getUsername() != null) {
            sql.VALUES("userName", "#{username,jdbcType=VARCHAR}");
        }
        
        if (record.getApiname() != null) {
            sql.VALUES("apiName", "#{apiname,jdbcType=VARCHAR}");
        }
        
        if (record.getApipwd() != null) {
            sql.VALUES("apiPwd", "#{apipwd,jdbcType=VARCHAR}");
        }
        
        if (record.getDeptid() != null) {
            sql.VALUES("deptId", "#{deptid,jdbcType=VARCHAR}");
        }
        
        if (record.getUserpwd() != null) {
            sql.VALUES("userPwd", "#{userpwd,jdbcType=VARCHAR}");
        }
        
        if (record.getUsersalt() != null) {
            sql.VALUES("userSalt", "#{usersalt,jdbcType=VARCHAR}");
        }
        
        if (record.getUseremail() != null) {
            sql.VALUES("userEMail", "#{useremail,jdbcType=VARCHAR}");
        }
        
        if (record.getUsertel() != null) {
            sql.VALUES("userTel", "#{usertel,jdbcType=VARCHAR}");
        }
        
        if (record.getUseraddress() != null) {
            sql.VALUES("userAddress", "#{useraddress,jdbcType=VARCHAR}");
        }
        
        if (record.getIsvalid() != null) {
            sql.VALUES("isValid", "#{isvalid,jdbcType=CHAR}");
        }
        
        if (record.getUpdatetime() != null) {
            sql.VALUES("updateTime", "#{updatetime,jdbcType=DATE}");
        }
        
        if (record.getCreatetime() != null) {
            sql.VALUES("createTime", "#{createtime,jdbcType=DATE}");
        }
        
        if (record.getUpdateperson() != null) {
            sql.VALUES("updatePerson", "#{updateperson,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateperson() != null) {
            sql.VALUES("createPerson", "#{createperson,jdbcType=VARCHAR}");
        }
        
        if (record.getUserphone() != null) {
            sql.VALUES("userPhone", "#{userphone,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(SystemUserExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("userId");
        } else {
            sql.SELECT("userId");
        }
        sql.SELECT("sex");
        sql.SELECT("userName");
        sql.SELECT("apiName");
        sql.SELECT("apiPwd");
        sql.SELECT("deptId");
        sql.SELECT("userPwd");
        sql.SELECT("userSalt");
        sql.SELECT("userEMail");
        sql.SELECT("userTel");
        sql.SELECT("userAddress");
        sql.SELECT("isValid");
        sql.SELECT("updateTime");
        sql.SELECT("createTime");
        sql.SELECT("updatePerson");
        sql.SELECT("createPerson");
        sql.SELECT("userPhone");
        sql.FROM("cbd_system_user");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(SystemUser record) {
        SQL sql = new SQL();
        sql.UPDATE("cbd_system_user");
        
        if (record.getSex() != null) {
            sql.SET("sex = #{sex,jdbcType=VARCHAR}");
        }
        
        if (record.getUsername() != null) {
            sql.SET("userName = #{username,jdbcType=VARCHAR}");
        }
        
        if (record.getApiname() != null) {
            sql.SET("apiName = #{apiname,jdbcType=VARCHAR}");
        }
        
        if (record.getApipwd() != null) {
            sql.SET("apiPwd = #{apipwd,jdbcType=VARCHAR}");
        }
        
        if (record.getDeptid() != null) {
            sql.SET("deptId = #{deptid,jdbcType=VARCHAR}");
        }
        
        if (record.getUserpwd() != null) {
            sql.SET("userPwd = #{userpwd,jdbcType=VARCHAR}");
        }
        
        if (record.getUsersalt() != null) {
            sql.SET("userSalt = #{usersalt,jdbcType=VARCHAR}");
        }
        
        if (record.getUseremail() != null) {
            sql.SET("userEMail = #{useremail,jdbcType=VARCHAR}");
        }
        
        if (record.getUsertel() != null) {
            sql.SET("userTel = #{usertel,jdbcType=VARCHAR}");
        }
        
        if (record.getUseraddress() != null) {
            sql.SET("userAddress = #{useraddress,jdbcType=VARCHAR}");
        }
        
        if (record.getIsvalid() != null) {
            sql.SET("isValid = #{isvalid,jdbcType=CHAR}");
        }
        
        if (record.getUpdatetime() != null) {
            sql.SET("updateTime = #{updatetime,jdbcType=DATE}");
        }
        
        if (record.getCreatetime() != null) {
            sql.SET("createTime = #{createtime,jdbcType=DATE}");
        }
        
        if (record.getUpdateperson() != null) {
            sql.SET("updatePerson = #{updateperson,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateperson() != null) {
            sql.SET("createPerson = #{createperson,jdbcType=VARCHAR}");
        }
        
        if (record.getUserphone() != null) {
            sql.SET("userPhone = #{userphone,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("userId = #{userid,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, SystemUserExample example, boolean includeExamplePhrase) {
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