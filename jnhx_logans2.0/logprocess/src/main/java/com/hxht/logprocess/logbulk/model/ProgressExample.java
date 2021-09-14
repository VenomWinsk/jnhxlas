package com.hxht.logprocess.logbulk.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProgressExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProgressExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUseRuleGroupIdIsNull() {
            addCriterion("use_rule_group_id is null");
            return (Criteria) this;
        }

        public Criteria andUseRuleGroupIdIsNotNull() {
            addCriterion("use_rule_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andUseRuleGroupIdEqualTo(String value) {
            addCriterion("use_rule_group_id =", value, "useRuleGroupId");
            return (Criteria) this;
        }

        public Criteria andUseRuleGroupIdNotEqualTo(String value) {
            addCriterion("use_rule_group_id <>", value, "useRuleGroupId");
            return (Criteria) this;
        }

        public Criteria andUseRuleGroupIdGreaterThan(String value) {
            addCriterion("use_rule_group_id >", value, "useRuleGroupId");
            return (Criteria) this;
        }

        public Criteria andUseRuleGroupIdGreaterThanOrEqualTo(String value) {
            addCriterion("use_rule_group_id >=", value, "useRuleGroupId");
            return (Criteria) this;
        }

        public Criteria andUseRuleGroupIdLessThan(String value) {
            addCriterion("use_rule_group_id <", value, "useRuleGroupId");
            return (Criteria) this;
        }

        public Criteria andUseRuleGroupIdLessThanOrEqualTo(String value) {
            addCriterion("use_rule_group_id <=", value, "useRuleGroupId");
            return (Criteria) this;
        }

        public Criteria andUseRuleGroupIdLike(String value) {
            addCriterion("use_rule_group_id like", value, "useRuleGroupId");
            return (Criteria) this;
        }

        public Criteria andUseRuleGroupIdNotLike(String value) {
            addCriterion("use_rule_group_id not like", value, "useRuleGroupId");
            return (Criteria) this;
        }

        public Criteria andUseRuleGroupIdIn(List<String> values) {
            addCriterion("use_rule_group_id in", values, "useRuleGroupId");
            return (Criteria) this;
        }

        public Criteria andUseRuleGroupIdNotIn(List<String> values) {
            addCriterion("use_rule_group_id not in", values, "useRuleGroupId");
            return (Criteria) this;
        }

        public Criteria andUseRuleGroupIdBetween(String value1, String value2) {
            addCriterion("use_rule_group_id between", value1, value2, "useRuleGroupId");
            return (Criteria) this;
        }

        public Criteria andUseRuleGroupIdNotBetween(String value1, String value2) {
            addCriterion("use_rule_group_id not between", value1, value2, "useRuleGroupId");
            return (Criteria) this;
        }

        public Criteria andFileTotalIsNull() {
            addCriterion("file_total is null");
            return (Criteria) this;
        }

        public Criteria andFileTotalIsNotNull() {
            addCriterion("file_total is not null");
            return (Criteria) this;
        }

        public Criteria andFileTotalEqualTo(Integer value) {
            addCriterion("file_total =", value, "fileTotal");
            return (Criteria) this;
        }

        public Criteria andFileTotalNotEqualTo(Integer value) {
            addCriterion("file_total <>", value, "fileTotal");
            return (Criteria) this;
        }

        public Criteria andFileTotalGreaterThan(Integer value) {
            addCriterion("file_total >", value, "fileTotal");
            return (Criteria) this;
        }

        public Criteria andFileTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("file_total >=", value, "fileTotal");
            return (Criteria) this;
        }

        public Criteria andFileTotalLessThan(Integer value) {
            addCriterion("file_total <", value, "fileTotal");
            return (Criteria) this;
        }

        public Criteria andFileTotalLessThanOrEqualTo(Integer value) {
            addCriterion("file_total <=", value, "fileTotal");
            return (Criteria) this;
        }

        public Criteria andFileTotalIn(List<Integer> values) {
            addCriterion("file_total in", values, "fileTotal");
            return (Criteria) this;
        }

        public Criteria andFileTotalNotIn(List<Integer> values) {
            addCriterion("file_total not in", values, "fileTotal");
            return (Criteria) this;
        }

        public Criteria andFileTotalBetween(Integer value1, Integer value2) {
            addCriterion("file_total between", value1, value2, "fileTotal");
            return (Criteria) this;
        }

        public Criteria andFileTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("file_total not between", value1, value2, "fileTotal");
            return (Criteria) this;
        }

        public Criteria andPrimaryFileTotalIsNull() {
            addCriterion("primary_file_total is null");
            return (Criteria) this;
        }

        public Criteria andPrimaryFileTotalIsNotNull() {
            addCriterion("primary_file_total is not null");
            return (Criteria) this;
        }

        public Criteria andPrimaryFileTotalEqualTo(Integer value) {
            addCriterion("primary_file_total =", value, "primaryFileTotal");
            return (Criteria) this;
        }

        public Criteria andPrimaryFileTotalNotEqualTo(Integer value) {
            addCriterion("primary_file_total <>", value, "primaryFileTotal");
            return (Criteria) this;
        }

        public Criteria andPrimaryFileTotalGreaterThan(Integer value) {
            addCriterion("primary_file_total >", value, "primaryFileTotal");
            return (Criteria) this;
        }

        public Criteria andPrimaryFileTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("primary_file_total >=", value, "primaryFileTotal");
            return (Criteria) this;
        }

        public Criteria andPrimaryFileTotalLessThan(Integer value) {
            addCriterion("primary_file_total <", value, "primaryFileTotal");
            return (Criteria) this;
        }

        public Criteria andPrimaryFileTotalLessThanOrEqualTo(Integer value) {
            addCriterion("primary_file_total <=", value, "primaryFileTotal");
            return (Criteria) this;
        }

        public Criteria andPrimaryFileTotalIn(List<Integer> values) {
            addCriterion("primary_file_total in", values, "primaryFileTotal");
            return (Criteria) this;
        }

        public Criteria andPrimaryFileTotalNotIn(List<Integer> values) {
            addCriterion("primary_file_total not in", values, "primaryFileTotal");
            return (Criteria) this;
        }

        public Criteria andPrimaryFileTotalBetween(Integer value1, Integer value2) {
            addCriterion("primary_file_total between", value1, value2, "primaryFileTotal");
            return (Criteria) this;
        }

        public Criteria andPrimaryFileTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("primary_file_total not between", value1, value2, "primaryFileTotal");
            return (Criteria) this;
        }

        public Criteria andNormalFileHandleIsNull() {
            addCriterion("normal_file_handle is null");
            return (Criteria) this;
        }

        public Criteria andNormalFileHandleIsNotNull() {
            addCriterion("normal_file_handle is not null");
            return (Criteria) this;
        }

        public Criteria andNormalFileHandleEqualTo(Integer value) {
            addCriterion("normal_file_handle =", value, "normalFileHandle");
            return (Criteria) this;
        }

        public Criteria andNormalFileHandleNotEqualTo(Integer value) {
            addCriterion("normal_file_handle <>", value, "normalFileHandle");
            return (Criteria) this;
        }

        public Criteria andNormalFileHandleGreaterThan(Integer value) {
            addCriterion("normal_file_handle >", value, "normalFileHandle");
            return (Criteria) this;
        }

        public Criteria andNormalFileHandleGreaterThanOrEqualTo(Integer value) {
            addCriterion("normal_file_handle >=", value, "normalFileHandle");
            return (Criteria) this;
        }

        public Criteria andNormalFileHandleLessThan(Integer value) {
            addCriterion("normal_file_handle <", value, "normalFileHandle");
            return (Criteria) this;
        }

        public Criteria andNormalFileHandleLessThanOrEqualTo(Integer value) {
            addCriterion("normal_file_handle <=", value, "normalFileHandle");
            return (Criteria) this;
        }

        public Criteria andNormalFileHandleIn(List<Integer> values) {
            addCriterion("normal_file_handle in", values, "normalFileHandle");
            return (Criteria) this;
        }

        public Criteria andNormalFileHandleNotIn(List<Integer> values) {
            addCriterion("normal_file_handle not in", values, "normalFileHandle");
            return (Criteria) this;
        }

        public Criteria andNormalFileHandleBetween(Integer value1, Integer value2) {
            addCriterion("normal_file_handle between", value1, value2, "normalFileHandle");
            return (Criteria) this;
        }

        public Criteria andNormalFileHandleNotBetween(Integer value1, Integer value2) {
            addCriterion("normal_file_handle not between", value1, value2, "normalFileHandle");
            return (Criteria) this;
        }

        public Criteria andPrimaryFileHandleIsNull() {
            addCriterion("primary_file_handle is null");
            return (Criteria) this;
        }

        public Criteria andPrimaryFileHandleIsNotNull() {
            addCriterion("primary_file_handle is not null");
            return (Criteria) this;
        }

        public Criteria andPrimaryFileHandleEqualTo(Integer value) {
            addCriterion("primary_file_handle =", value, "primaryFileHandle");
            return (Criteria) this;
        }

        public Criteria andPrimaryFileHandleNotEqualTo(Integer value) {
            addCriterion("primary_file_handle <>", value, "primaryFileHandle");
            return (Criteria) this;
        }

        public Criteria andPrimaryFileHandleGreaterThan(Integer value) {
            addCriterion("primary_file_handle >", value, "primaryFileHandle");
            return (Criteria) this;
        }

        public Criteria andPrimaryFileHandleGreaterThanOrEqualTo(Integer value) {
            addCriterion("primary_file_handle >=", value, "primaryFileHandle");
            return (Criteria) this;
        }

        public Criteria andPrimaryFileHandleLessThan(Integer value) {
            addCriterion("primary_file_handle <", value, "primaryFileHandle");
            return (Criteria) this;
        }

        public Criteria andPrimaryFileHandleLessThanOrEqualTo(Integer value) {
            addCriterion("primary_file_handle <=", value, "primaryFileHandle");
            return (Criteria) this;
        }

        public Criteria andPrimaryFileHandleIn(List<Integer> values) {
            addCriterion("primary_file_handle in", values, "primaryFileHandle");
            return (Criteria) this;
        }

        public Criteria andPrimaryFileHandleNotIn(List<Integer> values) {
            addCriterion("primary_file_handle not in", values, "primaryFileHandle");
            return (Criteria) this;
        }

        public Criteria andPrimaryFileHandleBetween(Integer value1, Integer value2) {
            addCriterion("primary_file_handle between", value1, value2, "primaryFileHandle");
            return (Criteria) this;
        }

        public Criteria andPrimaryFileHandleNotBetween(Integer value1, Integer value2) {
            addCriterion("primary_file_handle not between", value1, value2, "primaryFileHandle");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andGwtCreateIsNull() {
            addCriterion("gwt_create is null");
            return (Criteria) this;
        }

        public Criteria andGwtCreateIsNotNull() {
            addCriterion("gwt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGwtCreateEqualTo(Date value) {
            addCriterion("gwt_create =", value, "gwtCreate");
            return (Criteria) this;
        }

        public Criteria andGwtCreateNotEqualTo(Date value) {
            addCriterion("gwt_create <>", value, "gwtCreate");
            return (Criteria) this;
        }

        public Criteria andGwtCreateGreaterThan(Date value) {
            addCriterion("gwt_create >", value, "gwtCreate");
            return (Criteria) this;
        }

        public Criteria andGwtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gwt_create >=", value, "gwtCreate");
            return (Criteria) this;
        }

        public Criteria andGwtCreateLessThan(Date value) {
            addCriterion("gwt_create <", value, "gwtCreate");
            return (Criteria) this;
        }

        public Criteria andGwtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gwt_create <=", value, "gwtCreate");
            return (Criteria) this;
        }

        public Criteria andGwtCreateIn(List<Date> values) {
            addCriterion("gwt_create in", values, "gwtCreate");
            return (Criteria) this;
        }

        public Criteria andGwtCreateNotIn(List<Date> values) {
            addCriterion("gwt_create not in", values, "gwtCreate");
            return (Criteria) this;
        }

        public Criteria andGwtCreateBetween(Date value1, Date value2) {
            addCriterion("gwt_create between", value1, value2, "gwtCreate");
            return (Criteria) this;
        }

        public Criteria andGwtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gwt_create not between", value1, value2, "gwtCreate");
            return (Criteria) this;
        }

        public Criteria andGwtModifiedIsNull() {
            addCriterion("gwt_modified is null");
            return (Criteria) this;
        }

        public Criteria andGwtModifiedIsNotNull() {
            addCriterion("gwt_modified is not null");
            return (Criteria) this;
        }

        public Criteria andGwtModifiedEqualTo(Date value) {
            addCriterion("gwt_modified =", value, "gwtModified");
            return (Criteria) this;
        }

        public Criteria andGwtModifiedNotEqualTo(Date value) {
            addCriterion("gwt_modified <>", value, "gwtModified");
            return (Criteria) this;
        }

        public Criteria andGwtModifiedGreaterThan(Date value) {
            addCriterion("gwt_modified >", value, "gwtModified");
            return (Criteria) this;
        }

        public Criteria andGwtModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("gwt_modified >=", value, "gwtModified");
            return (Criteria) this;
        }

        public Criteria andGwtModifiedLessThan(Date value) {
            addCriterion("gwt_modified <", value, "gwtModified");
            return (Criteria) this;
        }

        public Criteria andGwtModifiedLessThanOrEqualTo(Date value) {
            addCriterion("gwt_modified <=", value, "gwtModified");
            return (Criteria) this;
        }

        public Criteria andGwtModifiedIn(List<Date> values) {
            addCriterion("gwt_modified in", values, "gwtModified");
            return (Criteria) this;
        }

        public Criteria andGwtModifiedNotIn(List<Date> values) {
            addCriterion("gwt_modified not in", values, "gwtModified");
            return (Criteria) this;
        }

        public Criteria andGwtModifiedBetween(Date value1, Date value2) {
            addCriterion("gwt_modified between", value1, value2, "gwtModified");
            return (Criteria) this;
        }

        public Criteria andGwtModifiedNotBetween(Date value1, Date value2) {
            addCriterion("gwt_modified not between", value1, value2, "gwtModified");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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