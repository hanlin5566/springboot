package com.hzcf.operation.gen.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.hzcf.operation.base.enums.DataStatus;

public class SystemUserExample {
    /**
     * cbd_system_user
     */
    protected String orderByClause;

    /**
     * cbd_system_user
     */
    protected boolean distinct;

    /**
     * cbd_system_user
     */
    protected List<Criteria> oredCriteria;

    public SystemUserExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * cbd_system_user 2017-11-10
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andUseridIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(String value) {
            addCriterion("userId =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(String value) {
            addCriterion("userId <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(String value) {
            addCriterion("userId >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(String value) {
            addCriterion("userId >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(String value) {
            addCriterion("userId <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(String value) {
            addCriterion("userId <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLike(String value) {
            addCriterion("userId like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotLike(String value) {
            addCriterion("userId not like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<String> values) {
            addCriterion("userId in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<String> values) {
            addCriterion("userId not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(String value1, String value2) {
            addCriterion("userId between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(String value1, String value2) {
            addCriterion("userId not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("sex like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("sex not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("userName is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("userName is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("userName =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("userName <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("userName >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("userName >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("userName <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("userName <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("userName like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("userName not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("userName in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("userName not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("userName between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("userName not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andApinameIsNull() {
            addCriterion("apiName is null");
            return (Criteria) this;
        }

        public Criteria andApinameIsNotNull() {
            addCriterion("apiName is not null");
            return (Criteria) this;
        }

        public Criteria andApinameEqualTo(String value) {
            addCriterion("apiName =", value, "apiname");
            return (Criteria) this;
        }

        public Criteria andApinameNotEqualTo(String value) {
            addCriterion("apiName <>", value, "apiname");
            return (Criteria) this;
        }

        public Criteria andApinameGreaterThan(String value) {
            addCriterion("apiName >", value, "apiname");
            return (Criteria) this;
        }

        public Criteria andApinameGreaterThanOrEqualTo(String value) {
            addCriterion("apiName >=", value, "apiname");
            return (Criteria) this;
        }

        public Criteria andApinameLessThan(String value) {
            addCriterion("apiName <", value, "apiname");
            return (Criteria) this;
        }

        public Criteria andApinameLessThanOrEqualTo(String value) {
            addCriterion("apiName <=", value, "apiname");
            return (Criteria) this;
        }

        public Criteria andApinameLike(String value) {
            addCriterion("apiName like", value, "apiname");
            return (Criteria) this;
        }

        public Criteria andApinameNotLike(String value) {
            addCriterion("apiName not like", value, "apiname");
            return (Criteria) this;
        }

        public Criteria andApinameIn(List<String> values) {
            addCriterion("apiName in", values, "apiname");
            return (Criteria) this;
        }

        public Criteria andApinameNotIn(List<String> values) {
            addCriterion("apiName not in", values, "apiname");
            return (Criteria) this;
        }

        public Criteria andApinameBetween(String value1, String value2) {
            addCriterion("apiName between", value1, value2, "apiname");
            return (Criteria) this;
        }

        public Criteria andApinameNotBetween(String value1, String value2) {
            addCriterion("apiName not between", value1, value2, "apiname");
            return (Criteria) this;
        }

        public Criteria andApipwdIsNull() {
            addCriterion("apiPwd is null");
            return (Criteria) this;
        }

        public Criteria andApipwdIsNotNull() {
            addCriterion("apiPwd is not null");
            return (Criteria) this;
        }

        public Criteria andApipwdEqualTo(String value) {
            addCriterion("apiPwd =", value, "apipwd");
            return (Criteria) this;
        }

        public Criteria andApipwdNotEqualTo(String value) {
            addCriterion("apiPwd <>", value, "apipwd");
            return (Criteria) this;
        }

        public Criteria andApipwdGreaterThan(String value) {
            addCriterion("apiPwd >", value, "apipwd");
            return (Criteria) this;
        }

        public Criteria andApipwdGreaterThanOrEqualTo(String value) {
            addCriterion("apiPwd >=", value, "apipwd");
            return (Criteria) this;
        }

        public Criteria andApipwdLessThan(String value) {
            addCriterion("apiPwd <", value, "apipwd");
            return (Criteria) this;
        }

        public Criteria andApipwdLessThanOrEqualTo(String value) {
            addCriterion("apiPwd <=", value, "apipwd");
            return (Criteria) this;
        }

        public Criteria andApipwdLike(String value) {
            addCriterion("apiPwd like", value, "apipwd");
            return (Criteria) this;
        }

        public Criteria andApipwdNotLike(String value) {
            addCriterion("apiPwd not like", value, "apipwd");
            return (Criteria) this;
        }

        public Criteria andApipwdIn(List<String> values) {
            addCriterion("apiPwd in", values, "apipwd");
            return (Criteria) this;
        }

        public Criteria andApipwdNotIn(List<String> values) {
            addCriterion("apiPwd not in", values, "apipwd");
            return (Criteria) this;
        }

        public Criteria andApipwdBetween(String value1, String value2) {
            addCriterion("apiPwd between", value1, value2, "apipwd");
            return (Criteria) this;
        }

        public Criteria andApipwdNotBetween(String value1, String value2) {
            addCriterion("apiPwd not between", value1, value2, "apipwd");
            return (Criteria) this;
        }

        public Criteria andDeptidIsNull() {
            addCriterion("deptId is null");
            return (Criteria) this;
        }

        public Criteria andDeptidIsNotNull() {
            addCriterion("deptId is not null");
            return (Criteria) this;
        }

        public Criteria andDeptidEqualTo(String value) {
            addCriterion("deptId =", value, "deptid");
            return (Criteria) this;
        }

        public Criteria andDeptidNotEqualTo(String value) {
            addCriterion("deptId <>", value, "deptid");
            return (Criteria) this;
        }

        public Criteria andDeptidGreaterThan(String value) {
            addCriterion("deptId >", value, "deptid");
            return (Criteria) this;
        }

        public Criteria andDeptidGreaterThanOrEqualTo(String value) {
            addCriterion("deptId >=", value, "deptid");
            return (Criteria) this;
        }

        public Criteria andDeptidLessThan(String value) {
            addCriterion("deptId <", value, "deptid");
            return (Criteria) this;
        }

        public Criteria andDeptidLessThanOrEqualTo(String value) {
            addCriterion("deptId <=", value, "deptid");
            return (Criteria) this;
        }

        public Criteria andDeptidLike(String value) {
            addCriterion("deptId like", value, "deptid");
            return (Criteria) this;
        }

        public Criteria andDeptidNotLike(String value) {
            addCriterion("deptId not like", value, "deptid");
            return (Criteria) this;
        }

        public Criteria andDeptidIn(List<String> values) {
            addCriterion("deptId in", values, "deptid");
            return (Criteria) this;
        }

        public Criteria andDeptidNotIn(List<String> values) {
            addCriterion("deptId not in", values, "deptid");
            return (Criteria) this;
        }

        public Criteria andDeptidBetween(String value1, String value2) {
            addCriterion("deptId between", value1, value2, "deptid");
            return (Criteria) this;
        }

        public Criteria andDeptidNotBetween(String value1, String value2) {
            addCriterion("deptId not between", value1, value2, "deptid");
            return (Criteria) this;
        }

        public Criteria andUserpwdIsNull() {
            addCriterion("userPwd is null");
            return (Criteria) this;
        }

        public Criteria andUserpwdIsNotNull() {
            addCriterion("userPwd is not null");
            return (Criteria) this;
        }

        public Criteria andUserpwdEqualTo(String value) {
            addCriterion("userPwd =", value, "userpwd");
            return (Criteria) this;
        }

        public Criteria andUserpwdNotEqualTo(String value) {
            addCriterion("userPwd <>", value, "userpwd");
            return (Criteria) this;
        }

        public Criteria andUserpwdGreaterThan(String value) {
            addCriterion("userPwd >", value, "userpwd");
            return (Criteria) this;
        }

        public Criteria andUserpwdGreaterThanOrEqualTo(String value) {
            addCriterion("userPwd >=", value, "userpwd");
            return (Criteria) this;
        }

        public Criteria andUserpwdLessThan(String value) {
            addCriterion("userPwd <", value, "userpwd");
            return (Criteria) this;
        }

        public Criteria andUserpwdLessThanOrEqualTo(String value) {
            addCriterion("userPwd <=", value, "userpwd");
            return (Criteria) this;
        }

        public Criteria andUserpwdLike(String value) {
            addCriterion("userPwd like", value, "userpwd");
            return (Criteria) this;
        }

        public Criteria andUserpwdNotLike(String value) {
            addCriterion("userPwd not like", value, "userpwd");
            return (Criteria) this;
        }

        public Criteria andUserpwdIn(List<String> values) {
            addCriterion("userPwd in", values, "userpwd");
            return (Criteria) this;
        }

        public Criteria andUserpwdNotIn(List<String> values) {
            addCriterion("userPwd not in", values, "userpwd");
            return (Criteria) this;
        }

        public Criteria andUserpwdBetween(String value1, String value2) {
            addCriterion("userPwd between", value1, value2, "userpwd");
            return (Criteria) this;
        }

        public Criteria andUserpwdNotBetween(String value1, String value2) {
            addCriterion("userPwd not between", value1, value2, "userpwd");
            return (Criteria) this;
        }

        public Criteria andUsersaltIsNull() {
            addCriterion("userSalt is null");
            return (Criteria) this;
        }

        public Criteria andUsersaltIsNotNull() {
            addCriterion("userSalt is not null");
            return (Criteria) this;
        }

        public Criteria andUsersaltEqualTo(String value) {
            addCriterion("userSalt =", value, "usersalt");
            return (Criteria) this;
        }

        public Criteria andUsersaltNotEqualTo(String value) {
            addCriterion("userSalt <>", value, "usersalt");
            return (Criteria) this;
        }

        public Criteria andUsersaltGreaterThan(String value) {
            addCriterion("userSalt >", value, "usersalt");
            return (Criteria) this;
        }

        public Criteria andUsersaltGreaterThanOrEqualTo(String value) {
            addCriterion("userSalt >=", value, "usersalt");
            return (Criteria) this;
        }

        public Criteria andUsersaltLessThan(String value) {
            addCriterion("userSalt <", value, "usersalt");
            return (Criteria) this;
        }

        public Criteria andUsersaltLessThanOrEqualTo(String value) {
            addCriterion("userSalt <=", value, "usersalt");
            return (Criteria) this;
        }

        public Criteria andUsersaltLike(String value) {
            addCriterion("userSalt like", value, "usersalt");
            return (Criteria) this;
        }

        public Criteria andUsersaltNotLike(String value) {
            addCriterion("userSalt not like", value, "usersalt");
            return (Criteria) this;
        }

        public Criteria andUsersaltIn(List<String> values) {
            addCriterion("userSalt in", values, "usersalt");
            return (Criteria) this;
        }

        public Criteria andUsersaltNotIn(List<String> values) {
            addCriterion("userSalt not in", values, "usersalt");
            return (Criteria) this;
        }

        public Criteria andUsersaltBetween(String value1, String value2) {
            addCriterion("userSalt between", value1, value2, "usersalt");
            return (Criteria) this;
        }

        public Criteria andUsersaltNotBetween(String value1, String value2) {
            addCriterion("userSalt not between", value1, value2, "usersalt");
            return (Criteria) this;
        }

        public Criteria andUseremailIsNull() {
            addCriterion("userEMail is null");
            return (Criteria) this;
        }

        public Criteria andUseremailIsNotNull() {
            addCriterion("userEMail is not null");
            return (Criteria) this;
        }

        public Criteria andUseremailEqualTo(String value) {
            addCriterion("userEMail =", value, "useremail");
            return (Criteria) this;
        }

        public Criteria andUseremailNotEqualTo(String value) {
            addCriterion("userEMail <>", value, "useremail");
            return (Criteria) this;
        }

        public Criteria andUseremailGreaterThan(String value) {
            addCriterion("userEMail >", value, "useremail");
            return (Criteria) this;
        }

        public Criteria andUseremailGreaterThanOrEqualTo(String value) {
            addCriterion("userEMail >=", value, "useremail");
            return (Criteria) this;
        }

        public Criteria andUseremailLessThan(String value) {
            addCriterion("userEMail <", value, "useremail");
            return (Criteria) this;
        }

        public Criteria andUseremailLessThanOrEqualTo(String value) {
            addCriterion("userEMail <=", value, "useremail");
            return (Criteria) this;
        }

        public Criteria andUseremailLike(String value) {
            addCriterion("userEMail like", value, "useremail");
            return (Criteria) this;
        }

        public Criteria andUseremailNotLike(String value) {
            addCriterion("userEMail not like", value, "useremail");
            return (Criteria) this;
        }

        public Criteria andUseremailIn(List<String> values) {
            addCriterion("userEMail in", values, "useremail");
            return (Criteria) this;
        }

        public Criteria andUseremailNotIn(List<String> values) {
            addCriterion("userEMail not in", values, "useremail");
            return (Criteria) this;
        }

        public Criteria andUseremailBetween(String value1, String value2) {
            addCriterion("userEMail between", value1, value2, "useremail");
            return (Criteria) this;
        }

        public Criteria andUseremailNotBetween(String value1, String value2) {
            addCriterion("userEMail not between", value1, value2, "useremail");
            return (Criteria) this;
        }

        public Criteria andUsertelIsNull() {
            addCriterion("userTel is null");
            return (Criteria) this;
        }

        public Criteria andUsertelIsNotNull() {
            addCriterion("userTel is not null");
            return (Criteria) this;
        }

        public Criteria andUsertelEqualTo(String value) {
            addCriterion("userTel =", value, "usertel");
            return (Criteria) this;
        }

        public Criteria andUsertelNotEqualTo(String value) {
            addCriterion("userTel <>", value, "usertel");
            return (Criteria) this;
        }

        public Criteria andUsertelGreaterThan(String value) {
            addCriterion("userTel >", value, "usertel");
            return (Criteria) this;
        }

        public Criteria andUsertelGreaterThanOrEqualTo(String value) {
            addCriterion("userTel >=", value, "usertel");
            return (Criteria) this;
        }

        public Criteria andUsertelLessThan(String value) {
            addCriterion("userTel <", value, "usertel");
            return (Criteria) this;
        }

        public Criteria andUsertelLessThanOrEqualTo(String value) {
            addCriterion("userTel <=", value, "usertel");
            return (Criteria) this;
        }

        public Criteria andUsertelLike(String value) {
            addCriterion("userTel like", value, "usertel");
            return (Criteria) this;
        }

        public Criteria andUsertelNotLike(String value) {
            addCriterion("userTel not like", value, "usertel");
            return (Criteria) this;
        }

        public Criteria andUsertelIn(List<String> values) {
            addCriterion("userTel in", values, "usertel");
            return (Criteria) this;
        }

        public Criteria andUsertelNotIn(List<String> values) {
            addCriterion("userTel not in", values, "usertel");
            return (Criteria) this;
        }

        public Criteria andUsertelBetween(String value1, String value2) {
            addCriterion("userTel between", value1, value2, "usertel");
            return (Criteria) this;
        }

        public Criteria andUsertelNotBetween(String value1, String value2) {
            addCriterion("userTel not between", value1, value2, "usertel");
            return (Criteria) this;
        }

        public Criteria andUseraddressIsNull() {
            addCriterion("userAddress is null");
            return (Criteria) this;
        }

        public Criteria andUseraddressIsNotNull() {
            addCriterion("userAddress is not null");
            return (Criteria) this;
        }

        public Criteria andUseraddressEqualTo(String value) {
            addCriterion("userAddress =", value, "useraddress");
            return (Criteria) this;
        }

        public Criteria andUseraddressNotEqualTo(String value) {
            addCriterion("userAddress <>", value, "useraddress");
            return (Criteria) this;
        }

        public Criteria andUseraddressGreaterThan(String value) {
            addCriterion("userAddress >", value, "useraddress");
            return (Criteria) this;
        }

        public Criteria andUseraddressGreaterThanOrEqualTo(String value) {
            addCriterion("userAddress >=", value, "useraddress");
            return (Criteria) this;
        }

        public Criteria andUseraddressLessThan(String value) {
            addCriterion("userAddress <", value, "useraddress");
            return (Criteria) this;
        }

        public Criteria andUseraddressLessThanOrEqualTo(String value) {
            addCriterion("userAddress <=", value, "useraddress");
            return (Criteria) this;
        }

        public Criteria andUseraddressLike(String value) {
            addCriterion("userAddress like", value, "useraddress");
            return (Criteria) this;
        }

        public Criteria andUseraddressNotLike(String value) {
            addCriterion("userAddress not like", value, "useraddress");
            return (Criteria) this;
        }

        public Criteria andUseraddressIn(List<String> values) {
            addCriterion("userAddress in", values, "useraddress");
            return (Criteria) this;
        }

        public Criteria andUseraddressNotIn(List<String> values) {
            addCriterion("userAddress not in", values, "useraddress");
            return (Criteria) this;
        }

        public Criteria andUseraddressBetween(String value1, String value2) {
            addCriterion("userAddress between", value1, value2, "useraddress");
            return (Criteria) this;
        }

        public Criteria andUseraddressNotBetween(String value1, String value2) {
            addCriterion("userAddress not between", value1, value2, "useraddress");
            return (Criteria) this;
        }

        public Criteria andIsvalidIsNull() {
            addCriterion("isValid is null");
            return (Criteria) this;
        }

        public Criteria andIsvalidIsNotNull() {
            addCriterion("isValid is not null");
            return (Criteria) this;
        }

        public Criteria andIsvalidEqualTo(DataStatus value) {
            addCriterion("isValid =", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidNotEqualTo(DataStatus value) {
            addCriterion("isValid <>", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidGreaterThan(DataStatus value) {
            addCriterion("isValid >", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidGreaterThanOrEqualTo(DataStatus value) {
            addCriterion("isValid >=", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidLessThan(DataStatus value) {
            addCriterion("isValid <", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidLessThanOrEqualTo(DataStatus value) {
            addCriterion("isValid <=", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidLike(DataStatus value) {
            addCriterion("isValid like", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidNotLike(DataStatus value) {
            addCriterion("isValid not like", value, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidIn(List<DataStatus> values) {
            addCriterion("isValid in", values, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidNotIn(List<DataStatus> values) {
            addCriterion("isValid not in", values, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidBetween(DataStatus value1, DataStatus value2) {
            addCriterion("isValid between", value1, value2, "isvalid");
            return (Criteria) this;
        }

        public Criteria andIsvalidNotBetween(DataStatus value1, DataStatus value2) {
            addCriterion("isValid not between", value1, value2, "isvalid");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterionForJDBCDate("updateTime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("updateTime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterionForJDBCDate("updateTime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("updateTime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterionForJDBCDate("updateTime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("updateTime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterionForJDBCDate("updateTime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("updateTime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("updateTime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("updateTime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterionForJDBCDate("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterionForJDBCDate("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterionForJDBCDate("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterionForJDBCDate("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("createTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatepersonIsNull() {
            addCriterion("updatePerson is null");
            return (Criteria) this;
        }

        public Criteria andUpdatepersonIsNotNull() {
            addCriterion("updatePerson is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatepersonEqualTo(String value) {
            addCriterion("updatePerson =", value, "updateperson");
            return (Criteria) this;
        }

        public Criteria andUpdatepersonNotEqualTo(String value) {
            addCriterion("updatePerson <>", value, "updateperson");
            return (Criteria) this;
        }

        public Criteria andUpdatepersonGreaterThan(String value) {
            addCriterion("updatePerson >", value, "updateperson");
            return (Criteria) this;
        }

        public Criteria andUpdatepersonGreaterThanOrEqualTo(String value) {
            addCriterion("updatePerson >=", value, "updateperson");
            return (Criteria) this;
        }

        public Criteria andUpdatepersonLessThan(String value) {
            addCriterion("updatePerson <", value, "updateperson");
            return (Criteria) this;
        }

        public Criteria andUpdatepersonLessThanOrEqualTo(String value) {
            addCriterion("updatePerson <=", value, "updateperson");
            return (Criteria) this;
        }

        public Criteria andUpdatepersonLike(String value) {
            addCriterion("updatePerson like", value, "updateperson");
            return (Criteria) this;
        }

        public Criteria andUpdatepersonNotLike(String value) {
            addCriterion("updatePerson not like", value, "updateperson");
            return (Criteria) this;
        }

        public Criteria andUpdatepersonIn(List<String> values) {
            addCriterion("updatePerson in", values, "updateperson");
            return (Criteria) this;
        }

        public Criteria andUpdatepersonNotIn(List<String> values) {
            addCriterion("updatePerson not in", values, "updateperson");
            return (Criteria) this;
        }

        public Criteria andUpdatepersonBetween(String value1, String value2) {
            addCriterion("updatePerson between", value1, value2, "updateperson");
            return (Criteria) this;
        }

        public Criteria andUpdatepersonNotBetween(String value1, String value2) {
            addCriterion("updatePerson not between", value1, value2, "updateperson");
            return (Criteria) this;
        }

        public Criteria andCreatepersonIsNull() {
            addCriterion("createPerson is null");
            return (Criteria) this;
        }

        public Criteria andCreatepersonIsNotNull() {
            addCriterion("createPerson is not null");
            return (Criteria) this;
        }

        public Criteria andCreatepersonEqualTo(String value) {
            addCriterion("createPerson =", value, "createperson");
            return (Criteria) this;
        }

        public Criteria andCreatepersonNotEqualTo(String value) {
            addCriterion("createPerson <>", value, "createperson");
            return (Criteria) this;
        }

        public Criteria andCreatepersonGreaterThan(String value) {
            addCriterion("createPerson >", value, "createperson");
            return (Criteria) this;
        }

        public Criteria andCreatepersonGreaterThanOrEqualTo(String value) {
            addCriterion("createPerson >=", value, "createperson");
            return (Criteria) this;
        }

        public Criteria andCreatepersonLessThan(String value) {
            addCriterion("createPerson <", value, "createperson");
            return (Criteria) this;
        }

        public Criteria andCreatepersonLessThanOrEqualTo(String value) {
            addCriterion("createPerson <=", value, "createperson");
            return (Criteria) this;
        }

        public Criteria andCreatepersonLike(String value) {
            addCriterion("createPerson like", value, "createperson");
            return (Criteria) this;
        }

        public Criteria andCreatepersonNotLike(String value) {
            addCriterion("createPerson not like", value, "createperson");
            return (Criteria) this;
        }

        public Criteria andCreatepersonIn(List<String> values) {
            addCriterion("createPerson in", values, "createperson");
            return (Criteria) this;
        }

        public Criteria andCreatepersonNotIn(List<String> values) {
            addCriterion("createPerson not in", values, "createperson");
            return (Criteria) this;
        }

        public Criteria andCreatepersonBetween(String value1, String value2) {
            addCriterion("createPerson between", value1, value2, "createperson");
            return (Criteria) this;
        }

        public Criteria andCreatepersonNotBetween(String value1, String value2) {
            addCriterion("createPerson not between", value1, value2, "createperson");
            return (Criteria) this;
        }

        public Criteria andUserphoneIsNull() {
            addCriterion("userPhone is null");
            return (Criteria) this;
        }

        public Criteria andUserphoneIsNotNull() {
            addCriterion("userPhone is not null");
            return (Criteria) this;
        }

        public Criteria andUserphoneEqualTo(String value) {
            addCriterion("userPhone =", value, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneNotEqualTo(String value) {
            addCriterion("userPhone <>", value, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneGreaterThan(String value) {
            addCriterion("userPhone >", value, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneGreaterThanOrEqualTo(String value) {
            addCriterion("userPhone >=", value, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneLessThan(String value) {
            addCriterion("userPhone <", value, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneLessThanOrEqualTo(String value) {
            addCriterion("userPhone <=", value, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneLike(String value) {
            addCriterion("userPhone like", value, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneNotLike(String value) {
            addCriterion("userPhone not like", value, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneIn(List<String> values) {
            addCriterion("userPhone in", values, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneNotIn(List<String> values) {
            addCriterion("userPhone not in", values, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneBetween(String value1, String value2) {
            addCriterion("userPhone between", value1, value2, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneNotBetween(String value1, String value2) {
            addCriterion("userPhone not between", value1, value2, "userphone");
            return (Criteria) this;
        }
    }

    /**
     * cbd_system_user
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * cbd_system_user 2017-11-10
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}