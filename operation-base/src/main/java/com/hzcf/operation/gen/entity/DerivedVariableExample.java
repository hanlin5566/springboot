package com.hzcf.operation.gen.entity;

import com.hzcf.operation.base.enums.DataStatus;
import com.hzcf.operation.base.enums.DerivedVarStatus;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DerivedVariableExample {
    /**
     * t_derived_var
     */
    protected String orderByClause;

    /**
     * t_derived_var
     */
    protected boolean distinct;

    /**
     * t_derived_var
     */
    protected List<Criteria> oredCriteria;

    public DerivedVariableExample() {
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
     * t_derived_var 2017-11-14
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

        public Criteria andVarIdIsNull() {
            addCriterion("var_id is null");
            return (Criteria) this;
        }

        public Criteria andVarIdIsNotNull() {
            addCriterion("var_id is not null");
            return (Criteria) this;
        }

        public Criteria andVarIdEqualTo(Integer value) {
            addCriterion("var_id =", value, "varId");
            return (Criteria) this;
        }

        public Criteria andVarIdNotEqualTo(Integer value) {
            addCriterion("var_id <>", value, "varId");
            return (Criteria) this;
        }

        public Criteria andVarIdGreaterThan(Integer value) {
            addCriterion("var_id >", value, "varId");
            return (Criteria) this;
        }

        public Criteria andVarIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("var_id >=", value, "varId");
            return (Criteria) this;
        }

        public Criteria andVarIdLessThan(Integer value) {
            addCriterion("var_id <", value, "varId");
            return (Criteria) this;
        }

        public Criteria andVarIdLessThanOrEqualTo(Integer value) {
            addCriterion("var_id <=", value, "varId");
            return (Criteria) this;
        }

        public Criteria andVarIdIn(List<Integer> values) {
            addCriterion("var_id in", values, "varId");
            return (Criteria) this;
        }

        public Criteria andVarIdNotIn(List<Integer> values) {
            addCriterion("var_id not in", values, "varId");
            return (Criteria) this;
        }

        public Criteria andVarIdBetween(Integer value1, Integer value2) {
            addCriterion("var_id between", value1, value2, "varId");
            return (Criteria) this;
        }

        public Criteria andVarIdNotBetween(Integer value1, Integer value2) {
            addCriterion("var_id not between", value1, value2, "varId");
            return (Criteria) this;
        }

        public Criteria andVarNameIsNull() {
            addCriterion("var_name is null");
            return (Criteria) this;
        }

        public Criteria andVarNameIsNotNull() {
            addCriterion("var_name is not null");
            return (Criteria) this;
        }

        public Criteria andVarNameEqualTo(String value) {
            addCriterion("var_name =", value, "varName");
            return (Criteria) this;
        }

        public Criteria andVarNameNotEqualTo(String value) {
            addCriterion("var_name <>", value, "varName");
            return (Criteria) this;
        }

        public Criteria andVarNameGreaterThan(String value) {
            addCriterion("var_name >", value, "varName");
            return (Criteria) this;
        }

        public Criteria andVarNameGreaterThanOrEqualTo(String value) {
            addCriterion("var_name >=", value, "varName");
            return (Criteria) this;
        }

        public Criteria andVarNameLessThan(String value) {
            addCriterion("var_name <", value, "varName");
            return (Criteria) this;
        }

        public Criteria andVarNameLessThanOrEqualTo(String value) {
            addCriterion("var_name <=", value, "varName");
            return (Criteria) this;
        }

        public Criteria andVarNameLike(String value) {
            addCriterion("var_name like", value, "varName");
            return (Criteria) this;
        }

        public Criteria andVarNameNotLike(String value) {
            addCriterion("var_name not like", value, "varName");
            return (Criteria) this;
        }

        public Criteria andVarNameIn(List<String> values) {
            addCriterion("var_name in", values, "varName");
            return (Criteria) this;
        }

        public Criteria andVarNameNotIn(List<String> values) {
            addCriterion("var_name not in", values, "varName");
            return (Criteria) this;
        }

        public Criteria andVarNameBetween(String value1, String value2) {
            addCriterion("var_name between", value1, value2, "varName");
            return (Criteria) this;
        }

        public Criteria andVarNameNotBetween(String value1, String value2) {
            addCriterion("var_name not between", value1, value2, "varName");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andVarCodeIsNull() {
            addCriterion("var_code is null");
            return (Criteria) this;
        }

        public Criteria andVarCodeIsNotNull() {
            addCriterion("var_code is not null");
            return (Criteria) this;
        }

        public Criteria andVarCodeEqualTo(String value) {
            addCriterion("var_code =", value, "varCode");
            return (Criteria) this;
        }

        public Criteria andVarCodeNotEqualTo(String value) {
            addCriterion("var_code <>", value, "varCode");
            return (Criteria) this;
        }

        public Criteria andVarCodeGreaterThan(String value) {
            addCriterion("var_code >", value, "varCode");
            return (Criteria) this;
        }

        public Criteria andVarCodeGreaterThanOrEqualTo(String value) {
            addCriterion("var_code >=", value, "varCode");
            return (Criteria) this;
        }

        public Criteria andVarCodeLessThan(String value) {
            addCriterion("var_code <", value, "varCode");
            return (Criteria) this;
        }

        public Criteria andVarCodeLessThanOrEqualTo(String value) {
            addCriterion("var_code <=", value, "varCode");
            return (Criteria) this;
        }

        public Criteria andVarCodeLike(String value) {
            addCriterion("var_code like", value, "varCode");
            return (Criteria) this;
        }

        public Criteria andVarCodeNotLike(String value) {
            addCriterion("var_code not like", value, "varCode");
            return (Criteria) this;
        }

        public Criteria andVarCodeIn(List<String> values) {
            addCriterion("var_code in", values, "varCode");
            return (Criteria) this;
        }

        public Criteria andVarCodeNotIn(List<String> values) {
            addCriterion("var_code not in", values, "varCode");
            return (Criteria) this;
        }

        public Criteria andVarCodeBetween(String value1, String value2) {
            addCriterion("var_code between", value1, value2, "varCode");
            return (Criteria) this;
        }

        public Criteria andVarCodeNotBetween(String value1, String value2) {
            addCriterion("var_code not between", value1, value2, "varCode");
            return (Criteria) this;
        }

        public Criteria andQueryIfaceIsNull() {
            addCriterion("query_iface is null");
            return (Criteria) this;
        }

        public Criteria andQueryIfaceIsNotNull() {
            addCriterion("query_iface is not null");
            return (Criteria) this;
        }

        public Criteria andQueryIfaceEqualTo(String value) {
            addCriterion("query_iface =", value, "queryIface");
            return (Criteria) this;
        }

        public Criteria andQueryIfaceNotEqualTo(String value) {
            addCriterion("query_iface <>", value, "queryIface");
            return (Criteria) this;
        }

        public Criteria andQueryIfaceGreaterThan(String value) {
            addCriterion("query_iface >", value, "queryIface");
            return (Criteria) this;
        }

        public Criteria andQueryIfaceGreaterThanOrEqualTo(String value) {
            addCriterion("query_iface >=", value, "queryIface");
            return (Criteria) this;
        }

        public Criteria andQueryIfaceLessThan(String value) {
            addCriterion("query_iface <", value, "queryIface");
            return (Criteria) this;
        }

        public Criteria andQueryIfaceLessThanOrEqualTo(String value) {
            addCriterion("query_iface <=", value, "queryIface");
            return (Criteria) this;
        }

        public Criteria andQueryIfaceLike(String value) {
            addCriterion("query_iface like", value, "queryIface");
            return (Criteria) this;
        }

        public Criteria andQueryIfaceNotLike(String value) {
            addCriterion("query_iface not like", value, "queryIface");
            return (Criteria) this;
        }

        public Criteria andQueryIfaceIn(List<String> values) {
            addCriterion("query_iface in", values, "queryIface");
            return (Criteria) this;
        }

        public Criteria andQueryIfaceNotIn(List<String> values) {
            addCriterion("query_iface not in", values, "queryIface");
            return (Criteria) this;
        }

        public Criteria andQueryIfaceBetween(String value1, String value2) {
            addCriterion("query_iface between", value1, value2, "queryIface");
            return (Criteria) this;
        }

        public Criteria andQueryIfaceNotBetween(String value1, String value2) {
            addCriterion("query_iface not between", value1, value2, "queryIface");
            return (Criteria) this;
        }

        public Criteria andClazzNameIsNull() {
            addCriterion("clazz_name is null");
            return (Criteria) this;
        }

        public Criteria andClazzNameIsNotNull() {
            addCriterion("clazz_name is not null");
            return (Criteria) this;
        }

        public Criteria andClazzNameEqualTo(String value) {
            addCriterion("clazz_name =", value, "clazzName");
            return (Criteria) this;
        }

        public Criteria andClazzNameNotEqualTo(String value) {
            addCriterion("clazz_name <>", value, "clazzName");
            return (Criteria) this;
        }

        public Criteria andClazzNameGreaterThan(String value) {
            addCriterion("clazz_name >", value, "clazzName");
            return (Criteria) this;
        }

        public Criteria andClazzNameGreaterThanOrEqualTo(String value) {
            addCriterion("clazz_name >=", value, "clazzName");
            return (Criteria) this;
        }

        public Criteria andClazzNameLessThan(String value) {
            addCriterion("clazz_name <", value, "clazzName");
            return (Criteria) this;
        }

        public Criteria andClazzNameLessThanOrEqualTo(String value) {
            addCriterion("clazz_name <=", value, "clazzName");
            return (Criteria) this;
        }

        public Criteria andClazzNameLike(String value) {
            addCriterion("clazz_name like", value, "clazzName");
            return (Criteria) this;
        }

        public Criteria andClazzNameNotLike(String value) {
            addCriterion("clazz_name not like", value, "clazzName");
            return (Criteria) this;
        }

        public Criteria andClazzNameIn(List<String> values) {
            addCriterion("clazz_name in", values, "clazzName");
            return (Criteria) this;
        }

        public Criteria andClazzNameNotIn(List<String> values) {
            addCriterion("clazz_name not in", values, "clazzName");
            return (Criteria) this;
        }

        public Criteria andClazzNameBetween(String value1, String value2) {
            addCriterion("clazz_name between", value1, value2, "clazzName");
            return (Criteria) this;
        }

        public Criteria andClazzNameNotBetween(String value1, String value2) {
            addCriterion("clazz_name not between", value1, value2, "clazzName");
            return (Criteria) this;
        }

        public Criteria andClazzPathIsNull() {
            addCriterion("clazz_path is null");
            return (Criteria) this;
        }

        public Criteria andClazzPathIsNotNull() {
            addCriterion("clazz_path is not null");
            return (Criteria) this;
        }

        public Criteria andClazzPathEqualTo(String value) {
            addCriterion("clazz_path =", value, "clazzPath");
            return (Criteria) this;
        }

        public Criteria andClazzPathNotEqualTo(String value) {
            addCriterion("clazz_path <>", value, "clazzPath");
            return (Criteria) this;
        }

        public Criteria andClazzPathGreaterThan(String value) {
            addCriterion("clazz_path >", value, "clazzPath");
            return (Criteria) this;
        }

        public Criteria andClazzPathGreaterThanOrEqualTo(String value) {
            addCriterion("clazz_path >=", value, "clazzPath");
            return (Criteria) this;
        }

        public Criteria andClazzPathLessThan(String value) {
            addCriterion("clazz_path <", value, "clazzPath");
            return (Criteria) this;
        }

        public Criteria andClazzPathLessThanOrEqualTo(String value) {
            addCriterion("clazz_path <=", value, "clazzPath");
            return (Criteria) this;
        }

        public Criteria andClazzPathLike(String value) {
            addCriterion("clazz_path like", value, "clazzPath");
            return (Criteria) this;
        }

        public Criteria andClazzPathNotLike(String value) {
            addCriterion("clazz_path not like", value, "clazzPath");
            return (Criteria) this;
        }

        public Criteria andClazzPathIn(List<String> values) {
            addCriterion("clazz_path in", values, "clazzPath");
            return (Criteria) this;
        }

        public Criteria andClazzPathNotIn(List<String> values) {
            addCriterion("clazz_path not in", values, "clazzPath");
            return (Criteria) this;
        }

        public Criteria andClazzPathBetween(String value1, String value2) {
            addCriterion("clazz_path between", value1, value2, "clazzPath");
            return (Criteria) this;
        }

        public Criteria andClazzPathNotBetween(String value1, String value2) {
            addCriterion("clazz_path not between", value1, value2, "clazzPath");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(DerivedVarStatus value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(DerivedVarStatus value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(DerivedVarStatus value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(DerivedVarStatus value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(DerivedVarStatus value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(DerivedVarStatus value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<DerivedVarStatus> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<DerivedVarStatus> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(DerivedVarStatus value1, DerivedVarStatus value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(DerivedVarStatus value1, DerivedVarStatus value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andDataStatusIsNull() {
            addCriterion("data_status is null");
            return (Criteria) this;
        }

        public Criteria andDataStatusIsNotNull() {
            addCriterion("data_status is not null");
            return (Criteria) this;
        }

        public Criteria andDataStatusEqualTo(DataStatus value) {
            addCriterion("data_status =", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusNotEqualTo(DataStatus value) {
            addCriterion("data_status <>", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusGreaterThan(DataStatus value) {
            addCriterion("data_status >", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusGreaterThanOrEqualTo(DataStatus value) {
            addCriterion("data_status >=", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusLessThan(DataStatus value) {
            addCriterion("data_status <", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusLessThanOrEqualTo(DataStatus value) {
            addCriterion("data_status <=", value, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusIn(List<DataStatus> values) {
            addCriterion("data_status in", values, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusNotIn(List<DataStatus> values) {
            addCriterion("data_status not in", values, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusBetween(DataStatus value1, DataStatus value2) {
            addCriterion("data_status between", value1, value2, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDataStatusNotBetween(DataStatus value1, DataStatus value2) {
            addCriterion("data_status not between", value1, value2, "dataStatus");
            return (Criteria) this;
        }

        public Criteria andDeployTimeIsNull() {
            addCriterion("deploy_time is null");
            return (Criteria) this;
        }

        public Criteria andDeployTimeIsNotNull() {
            addCriterion("deploy_time is not null");
            return (Criteria) this;
        }

        public Criteria andDeployTimeEqualTo(Date value) {
            addCriterion("deploy_time =", value, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeNotEqualTo(Date value) {
            addCriterion("deploy_time <>", value, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeGreaterThan(Date value) {
            addCriterion("deploy_time >", value, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("deploy_time >=", value, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeLessThan(Date value) {
            addCriterion("deploy_time <", value, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeLessThanOrEqualTo(Date value) {
            addCriterion("deploy_time <=", value, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeIn(List<Date> values) {
            addCriterion("deploy_time in", values, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeNotIn(List<Date> values) {
            addCriterion("deploy_time not in", values, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeBetween(Date value1, Date value2) {
            addCriterion("deploy_time between", value1, value2, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeNotBetween(Date value1, Date value2) {
            addCriterion("deploy_time not between", value1, value2, "deployTime");
            return (Criteria) this;
        }

        public Criteria andCUidIsNull() {
            addCriterion("c_uid is null");
            return (Criteria) this;
        }

        public Criteria andCUidIsNotNull() {
            addCriterion("c_uid is not null");
            return (Criteria) this;
        }

        public Criteria andCUidEqualTo(Integer value) {
            addCriterion("c_uid =", value, "cUid");
            return (Criteria) this;
        }

        public Criteria andCUidNotEqualTo(Integer value) {
            addCriterion("c_uid <>", value, "cUid");
            return (Criteria) this;
        }

        public Criteria andCUidGreaterThan(Integer value) {
            addCriterion("c_uid >", value, "cUid");
            return (Criteria) this;
        }

        public Criteria andCUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("c_uid >=", value, "cUid");
            return (Criteria) this;
        }

        public Criteria andCUidLessThan(Integer value) {
            addCriterion("c_uid <", value, "cUid");
            return (Criteria) this;
        }

        public Criteria andCUidLessThanOrEqualTo(Integer value) {
            addCriterion("c_uid <=", value, "cUid");
            return (Criteria) this;
        }

        public Criteria andCUidIn(List<Integer> values) {
            addCriterion("c_uid in", values, "cUid");
            return (Criteria) this;
        }

        public Criteria andCUidNotIn(List<Integer> values) {
            addCriterion("c_uid not in", values, "cUid");
            return (Criteria) this;
        }

        public Criteria andCUidBetween(Integer value1, Integer value2) {
            addCriterion("c_uid between", value1, value2, "cUid");
            return (Criteria) this;
        }

        public Criteria andCUidNotBetween(Integer value1, Integer value2) {
            addCriterion("c_uid not between", value1, value2, "cUid");
            return (Criteria) this;
        }

        public Criteria andCTimeIsNull() {
            addCriterion("c_time is null");
            return (Criteria) this;
        }

        public Criteria andCTimeIsNotNull() {
            addCriterion("c_time is not null");
            return (Criteria) this;
        }

        public Criteria andCTimeEqualTo(Date value) {
            addCriterion("c_time =", value, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeNotEqualTo(Date value) {
            addCriterion("c_time <>", value, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeGreaterThan(Date value) {
            addCriterion("c_time >", value, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("c_time >=", value, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeLessThan(Date value) {
            addCriterion("c_time <", value, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeLessThanOrEqualTo(Date value) {
            addCriterion("c_time <=", value, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeIn(List<Date> values) {
            addCriterion("c_time in", values, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeNotIn(List<Date> values) {
            addCriterion("c_time not in", values, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeBetween(Date value1, Date value2) {
            addCriterion("c_time between", value1, value2, "cTime");
            return (Criteria) this;
        }

        public Criteria andCTimeNotBetween(Date value1, Date value2) {
            addCriterion("c_time not between", value1, value2, "cTime");
            return (Criteria) this;
        }

        public Criteria andUUidIsNull() {
            addCriterion("u_uid is null");
            return (Criteria) this;
        }

        public Criteria andUUidIsNotNull() {
            addCriterion("u_uid is not null");
            return (Criteria) this;
        }

        public Criteria andUUidEqualTo(Integer value) {
            addCriterion("u_uid =", value, "uUid");
            return (Criteria) this;
        }

        public Criteria andUUidNotEqualTo(Integer value) {
            addCriterion("u_uid <>", value, "uUid");
            return (Criteria) this;
        }

        public Criteria andUUidGreaterThan(Integer value) {
            addCriterion("u_uid >", value, "uUid");
            return (Criteria) this;
        }

        public Criteria andUUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("u_uid >=", value, "uUid");
            return (Criteria) this;
        }

        public Criteria andUUidLessThan(Integer value) {
            addCriterion("u_uid <", value, "uUid");
            return (Criteria) this;
        }

        public Criteria andUUidLessThanOrEqualTo(Integer value) {
            addCriterion("u_uid <=", value, "uUid");
            return (Criteria) this;
        }

        public Criteria andUUidIn(List<Integer> values) {
            addCriterion("u_uid in", values, "uUid");
            return (Criteria) this;
        }

        public Criteria andUUidNotIn(List<Integer> values) {
            addCriterion("u_uid not in", values, "uUid");
            return (Criteria) this;
        }

        public Criteria andUUidBetween(Integer value1, Integer value2) {
            addCriterion("u_uid between", value1, value2, "uUid");
            return (Criteria) this;
        }

        public Criteria andUUidNotBetween(Integer value1, Integer value2) {
            addCriterion("u_uid not between", value1, value2, "uUid");
            return (Criteria) this;
        }

        public Criteria andUTimeIsNull() {
            addCriterion("u_time is null");
            return (Criteria) this;
        }

        public Criteria andUTimeIsNotNull() {
            addCriterion("u_time is not null");
            return (Criteria) this;
        }

        public Criteria andUTimeEqualTo(Date value) {
            addCriterion("u_time =", value, "uTime");
            return (Criteria) this;
        }

        public Criteria andUTimeNotEqualTo(Date value) {
            addCriterion("u_time <>", value, "uTime");
            return (Criteria) this;
        }

        public Criteria andUTimeGreaterThan(Date value) {
            addCriterion("u_time >", value, "uTime");
            return (Criteria) this;
        }

        public Criteria andUTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("u_time >=", value, "uTime");
            return (Criteria) this;
        }

        public Criteria andUTimeLessThan(Date value) {
            addCriterion("u_time <", value, "uTime");
            return (Criteria) this;
        }

        public Criteria andUTimeLessThanOrEqualTo(Date value) {
            addCriterion("u_time <=", value, "uTime");
            return (Criteria) this;
        }

        public Criteria andUTimeIn(List<Date> values) {
            addCriterion("u_time in", values, "uTime");
            return (Criteria) this;
        }

        public Criteria andUTimeNotIn(List<Date> values) {
            addCriterion("u_time not in", values, "uTime");
            return (Criteria) this;
        }

        public Criteria andUTimeBetween(Date value1, Date value2) {
            addCriterion("u_time between", value1, value2, "uTime");
            return (Criteria) this;
        }

        public Criteria andUTimeNotBetween(Date value1, Date value2) {
            addCriterion("u_time not between", value1, value2, "uTime");
            return (Criteria) this;
        }
    }

    /**
     * t_derived_var
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * t_derived_var 2017-11-14
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