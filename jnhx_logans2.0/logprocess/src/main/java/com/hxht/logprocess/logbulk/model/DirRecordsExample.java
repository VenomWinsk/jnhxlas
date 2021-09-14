package com.hxht.logprocess.logbulk.model;

import java.util.ArrayList;
import java.util.List;

public class DirRecordsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DirRecordsExample() {
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

        public Criteria andUsedRuleGroupIdIsNull() {
            addCriterion("used_rule_group_id is null");
            return (Criteria) this;
        }

        public Criteria andUsedRuleGroupIdIsNotNull() {
            addCriterion("used_rule_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andUsedRuleGroupIdEqualTo(String value) {
            addCriterion("used_rule_group_id =", value, "usedRuleGroupId");
            return (Criteria) this;
        }

        public Criteria andUsedRuleGroupIdNotEqualTo(String value) {
            addCriterion("used_rule_group_id <>", value, "usedRuleGroupId");
            return (Criteria) this;
        }

        public Criteria andUsedRuleGroupIdGreaterThan(String value) {
            addCriterion("used_rule_group_id >", value, "usedRuleGroupId");
            return (Criteria) this;
        }

        public Criteria andUsedRuleGroupIdGreaterThanOrEqualTo(String value) {
            addCriterion("used_rule_group_id >=", value, "usedRuleGroupId");
            return (Criteria) this;
        }

        public Criteria andUsedRuleGroupIdLessThan(String value) {
            addCriterion("used_rule_group_id <", value, "usedRuleGroupId");
            return (Criteria) this;
        }

        public Criteria andUsedRuleGroupIdLessThanOrEqualTo(String value) {
            addCriterion("used_rule_group_id <=", value, "usedRuleGroupId");
            return (Criteria) this;
        }

        public Criteria andUsedRuleGroupIdLike(String value) {
            addCriterion("used_rule_group_id like", value, "usedRuleGroupId");
            return (Criteria) this;
        }

        public Criteria andUsedRuleGroupIdNotLike(String value) {
            addCriterion("used_rule_group_id not like", value, "usedRuleGroupId");
            return (Criteria) this;
        }

        public Criteria andUsedRuleGroupIdIn(List<String> values) {
            addCriterion("used_rule_group_id in", values, "usedRuleGroupId");
            return (Criteria) this;
        }

        public Criteria andUsedRuleGroupIdNotIn(List<String> values) {
            addCriterion("used_rule_group_id not in", values, "usedRuleGroupId");
            return (Criteria) this;
        }

        public Criteria andUsedRuleGroupIdBetween(String value1, String value2) {
            addCriterion("used_rule_group_id between", value1, value2, "usedRuleGroupId");
            return (Criteria) this;
        }

        public Criteria andUsedRuleGroupIdNotBetween(String value1, String value2) {
            addCriterion("used_rule_group_id not between", value1, value2, "usedRuleGroupId");
            return (Criteria) this;
        }

        public Criteria andDirnameIsNull() {
            addCriterion("dirname is null");
            return (Criteria) this;
        }

        public Criteria andDirnameIsNotNull() {
            addCriterion("dirname is not null");
            return (Criteria) this;
        }

        public Criteria andDirnameEqualTo(String value) {
            addCriterion("dirname =", value, "dirname");
            return (Criteria) this;
        }

        public Criteria andDirnameNotEqualTo(String value) {
            addCriterion("dirname <>", value, "dirname");
            return (Criteria) this;
        }

        public Criteria andDirnameGreaterThan(String value) {
            addCriterion("dirname >", value, "dirname");
            return (Criteria) this;
        }

        public Criteria andDirnameGreaterThanOrEqualTo(String value) {
            addCriterion("dirname >=", value, "dirname");
            return (Criteria) this;
        }

        public Criteria andDirnameLessThan(String value) {
            addCriterion("dirname <", value, "dirname");
            return (Criteria) this;
        }

        public Criteria andDirnameLessThanOrEqualTo(String value) {
            addCriterion("dirname <=", value, "dirname");
            return (Criteria) this;
        }

        public Criteria andDirnameLike(String value) {
            addCriterion("dirname like", value, "dirname");
            return (Criteria) this;
        }

        public Criteria andDirnameNotLike(String value) {
            addCriterion("dirname not like", value, "dirname");
            return (Criteria) this;
        }

        public Criteria andDirnameIn(List<String> values) {
            addCriterion("dirname in", values, "dirname");
            return (Criteria) this;
        }

        public Criteria andDirnameNotIn(List<String> values) {
            addCriterion("dirname not in", values, "dirname");
            return (Criteria) this;
        }

        public Criteria andDirnameBetween(String value1, String value2) {
            addCriterion("dirname between", value1, value2, "dirname");
            return (Criteria) this;
        }

        public Criteria andDirnameNotBetween(String value1, String value2) {
            addCriterion("dirname not between", value1, value2, "dirname");
            return (Criteria) this;
        }

        public Criteria andWasscannedIsNull() {
            addCriterion("wasscanned is null");
            return (Criteria) this;
        }

        public Criteria andWasscannedIsNotNull() {
            addCriterion("wasscanned is not null");
            return (Criteria) this;
        }

        public Criteria andWasscannedEqualTo(Integer value) {
            addCriterion("wasscanned =", value, "wasscanned");
            return (Criteria) this;
        }

        public Criteria andWasscannedNotEqualTo(Integer value) {
            addCriterion("wasscanned <>", value, "wasscanned");
            return (Criteria) this;
        }

        public Criteria andWasscannedGreaterThan(Integer value) {
            addCriterion("wasscanned >", value, "wasscanned");
            return (Criteria) this;
        }

        public Criteria andWasscannedGreaterThanOrEqualTo(Integer value) {
            addCriterion("wasscanned >=", value, "wasscanned");
            return (Criteria) this;
        }

        public Criteria andWasscannedLessThan(Integer value) {
            addCriterion("wasscanned <", value, "wasscanned");
            return (Criteria) this;
        }

        public Criteria andWasscannedLessThanOrEqualTo(Integer value) {
            addCriterion("wasscanned <=", value, "wasscanned");
            return (Criteria) this;
        }

        public Criteria andWasscannedIn(List<Integer> values) {
            addCriterion("wasscanned in", values, "wasscanned");
            return (Criteria) this;
        }

        public Criteria andWasscannedNotIn(List<Integer> values) {
            addCriterion("wasscanned not in", values, "wasscanned");
            return (Criteria) this;
        }

        public Criteria andWasscannedBetween(Integer value1, Integer value2) {
            addCriterion("wasscanned between", value1, value2, "wasscanned");
            return (Criteria) this;
        }

        public Criteria andWasscannedNotBetween(Integer value1, Integer value2) {
            addCriterion("wasscanned not between", value1, value2, "wasscanned");
            return (Criteria) this;
        }

        public Criteria andDirStatusIsNull() {
            addCriterion("dir_status is null");
            return (Criteria) this;
        }

        public Criteria andDirStatusIsNotNull() {
            addCriterion("dir_status is not null");
            return (Criteria) this;
        }

        public Criteria andDirStatusEqualTo(Integer value) {
            addCriterion("dir_status =", value, "dirStatus");
            return (Criteria) this;
        }

        public Criteria andDirStatusNotEqualTo(Integer value) {
            addCriterion("dir_status <>", value, "dirStatus");
            return (Criteria) this;
        }

        public Criteria andDirStatusGreaterThan(Integer value) {
            addCriterion("dir_status >", value, "dirStatus");
            return (Criteria) this;
        }

        public Criteria andDirStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("dir_status >=", value, "dirStatus");
            return (Criteria) this;
        }

        public Criteria andDirStatusLessThan(Integer value) {
            addCriterion("dir_status <", value, "dirStatus");
            return (Criteria) this;
        }

        public Criteria andDirStatusLessThanOrEqualTo(Integer value) {
            addCriterion("dir_status <=", value, "dirStatus");
            return (Criteria) this;
        }

        public Criteria andDirStatusIn(List<Integer> values) {
            addCriterion("dir_status in", values, "dirStatus");
            return (Criteria) this;
        }

        public Criteria andDirStatusNotIn(List<Integer> values) {
            addCriterion("dir_status not in", values, "dirStatus");
            return (Criteria) this;
        }

        public Criteria andDirStatusBetween(Integer value1, Integer value2) {
            addCriterion("dir_status between", value1, value2, "dirStatus");
            return (Criteria) this;
        }

        public Criteria andDirStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("dir_status not between", value1, value2, "dirStatus");
            return (Criteria) this;
        }

        public Criteria andFilenumsIsNull() {
            addCriterion("filenums is null");
            return (Criteria) this;
        }

        public Criteria andFilenumsIsNotNull() {
            addCriterion("filenums is not null");
            return (Criteria) this;
        }

        public Criteria andFilenumsEqualTo(Integer value) {
            addCriterion("filenums =", value, "filenums");
            return (Criteria) this;
        }

        public Criteria andFilenumsNotEqualTo(Integer value) {
            addCriterion("filenums <>", value, "filenums");
            return (Criteria) this;
        }

        public Criteria andFilenumsGreaterThan(Integer value) {
            addCriterion("filenums >", value, "filenums");
            return (Criteria) this;
        }

        public Criteria andFilenumsGreaterThanOrEqualTo(Integer value) {
            addCriterion("filenums >=", value, "filenums");
            return (Criteria) this;
        }

        public Criteria andFilenumsLessThan(Integer value) {
            addCriterion("filenums <", value, "filenums");
            return (Criteria) this;
        }

        public Criteria andFilenumsLessThanOrEqualTo(Integer value) {
            addCriterion("filenums <=", value, "filenums");
            return (Criteria) this;
        }

        public Criteria andFilenumsIn(List<Integer> values) {
            addCriterion("filenums in", values, "filenums");
            return (Criteria) this;
        }

        public Criteria andFilenumsNotIn(List<Integer> values) {
            addCriterion("filenums not in", values, "filenums");
            return (Criteria) this;
        }

        public Criteria andFilenumsBetween(Integer value1, Integer value2) {
            addCriterion("filenums between", value1, value2, "filenums");
            return (Criteria) this;
        }

        public Criteria andFilenumsNotBetween(Integer value1, Integer value2) {
            addCriterion("filenums not between", value1, value2, "filenums");
            return (Criteria) this;
        }

        public Criteria andJumpnumsIsNull() {
            addCriterion("jumpnums is null");
            return (Criteria) this;
        }

        public Criteria andJumpnumsIsNotNull() {
            addCriterion("jumpnums is not null");
            return (Criteria) this;
        }

        public Criteria andJumpnumsEqualTo(Integer value) {
            addCriterion("jumpnums =", value, "jumpnums");
            return (Criteria) this;
        }

        public Criteria andJumpnumsNotEqualTo(Integer value) {
            addCriterion("jumpnums <>", value, "jumpnums");
            return (Criteria) this;
        }

        public Criteria andJumpnumsGreaterThan(Integer value) {
            addCriterion("jumpnums >", value, "jumpnums");
            return (Criteria) this;
        }

        public Criteria andJumpnumsGreaterThanOrEqualTo(Integer value) {
            addCriterion("jumpnums >=", value, "jumpnums");
            return (Criteria) this;
        }

        public Criteria andJumpnumsLessThan(Integer value) {
            addCriterion("jumpnums <", value, "jumpnums");
            return (Criteria) this;
        }

        public Criteria andJumpnumsLessThanOrEqualTo(Integer value) {
            addCriterion("jumpnums <=", value, "jumpnums");
            return (Criteria) this;
        }

        public Criteria andJumpnumsIn(List<Integer> values) {
            addCriterion("jumpnums in", values, "jumpnums");
            return (Criteria) this;
        }

        public Criteria andJumpnumsNotIn(List<Integer> values) {
            addCriterion("jumpnums not in", values, "jumpnums");
            return (Criteria) this;
        }

        public Criteria andJumpnumsBetween(Integer value1, Integer value2) {
            addCriterion("jumpnums between", value1, value2, "jumpnums");
            return (Criteria) this;
        }

        public Criteria andJumpnumsNotBetween(Integer value1, Integer value2) {
            addCriterion("jumpnums not between", value1, value2, "jumpnums");
            return (Criteria) this;
        }

        public Criteria andProcessednumsIsNull() {
            addCriterion("processednums is null");
            return (Criteria) this;
        }

        public Criteria andProcessednumsIsNotNull() {
            addCriterion("processednums is not null");
            return (Criteria) this;
        }

        public Criteria andProcessednumsEqualTo(Integer value) {
            addCriterion("processednums =", value, "processednums");
            return (Criteria) this;
        }

        public Criteria andProcessednumsNotEqualTo(Integer value) {
            addCriterion("processednums <>", value, "processednums");
            return (Criteria) this;
        }

        public Criteria andProcessednumsGreaterThan(Integer value) {
            addCriterion("processednums >", value, "processednums");
            return (Criteria) this;
        }

        public Criteria andProcessednumsGreaterThanOrEqualTo(Integer value) {
            addCriterion("processednums >=", value, "processednums");
            return (Criteria) this;
        }

        public Criteria andProcessednumsLessThan(Integer value) {
            addCriterion("processednums <", value, "processednums");
            return (Criteria) this;
        }

        public Criteria andProcessednumsLessThanOrEqualTo(Integer value) {
            addCriterion("processednums <=", value, "processednums");
            return (Criteria) this;
        }

        public Criteria andProcessednumsIn(List<Integer> values) {
            addCriterion("processednums in", values, "processednums");
            return (Criteria) this;
        }

        public Criteria andProcessednumsNotIn(List<Integer> values) {
            addCriterion("processednums not in", values, "processednums");
            return (Criteria) this;
        }

        public Criteria andProcessednumsBetween(Integer value1, Integer value2) {
            addCriterion("processednums between", value1, value2, "processednums");
            return (Criteria) this;
        }

        public Criteria andProcessednumsNotBetween(Integer value1, Integer value2) {
            addCriterion("processednums not between", value1, value2, "processednums");
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