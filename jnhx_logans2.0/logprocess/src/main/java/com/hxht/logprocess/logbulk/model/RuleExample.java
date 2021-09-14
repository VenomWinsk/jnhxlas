package com.hxht.logprocess.logbulk.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RuleExample() {
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

        public Criteria andRuleGroupIdIsNull() {
            addCriterion("rule_group_id is null");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdIsNotNull() {
            addCriterion("rule_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdEqualTo(String value) {
            addCriterion("rule_group_id =", value, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdNotEqualTo(String value) {
            addCriterion("rule_group_id <>", value, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdGreaterThan(String value) {
            addCriterion("rule_group_id >", value, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdGreaterThanOrEqualTo(String value) {
            addCriterion("rule_group_id >=", value, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdLessThan(String value) {
            addCriterion("rule_group_id <", value, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdLessThanOrEqualTo(String value) {
            addCriterion("rule_group_id <=", value, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdLike(String value) {
            addCriterion("rule_group_id like", value, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdNotLike(String value) {
            addCriterion("rule_group_id not like", value, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdIn(List<String> values) {
            addCriterion("rule_group_id in", values, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdNotIn(List<String> values) {
            addCriterion("rule_group_id not in", values, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdBetween(String value1, String value2) {
            addCriterion("rule_group_id between", value1, value2, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdNotBetween(String value1, String value2) {
            addCriterion("rule_group_id not between", value1, value2, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andExtractRuleIsNull() {
            addCriterion("extract_rule is null");
            return (Criteria) this;
        }

        public Criteria andExtractRuleIsNotNull() {
            addCriterion("extract_rule is not null");
            return (Criteria) this;
        }

        public Criteria andExtractRuleEqualTo(String value) {
            addCriterion("extract_rule =", value, "extractRule");
            return (Criteria) this;
        }

        public Criteria andExtractRuleNotEqualTo(String value) {
            addCriterion("extract_rule <>", value, "extractRule");
            return (Criteria) this;
        }

        public Criteria andExtractRuleGreaterThan(String value) {
            addCriterion("extract_rule >", value, "extractRule");
            return (Criteria) this;
        }

        public Criteria andExtractRuleGreaterThanOrEqualTo(String value) {
            addCriterion("extract_rule >=", value, "extractRule");
            return (Criteria) this;
        }

        public Criteria andExtractRuleLessThan(String value) {
            addCriterion("extract_rule <", value, "extractRule");
            return (Criteria) this;
        }

        public Criteria andExtractRuleLessThanOrEqualTo(String value) {
            addCriterion("extract_rule <=", value, "extractRule");
            return (Criteria) this;
        }

        public Criteria andExtractRuleLike(String value) {
            addCriterion("extract_rule like", value, "extractRule");
            return (Criteria) this;
        }

        public Criteria andExtractRuleNotLike(String value) {
            addCriterion("extract_rule not like", value, "extractRule");
            return (Criteria) this;
        }

        public Criteria andExtractRuleIn(List<String> values) {
            addCriterion("extract_rule in", values, "extractRule");
            return (Criteria) this;
        }

        public Criteria andExtractRuleNotIn(List<String> values) {
            addCriterion("extract_rule not in", values, "extractRule");
            return (Criteria) this;
        }

        public Criteria andExtractRuleBetween(String value1, String value2) {
            addCriterion("extract_rule between", value1, value2, "extractRule");
            return (Criteria) this;
        }

        public Criteria andExtractRuleNotBetween(String value1, String value2) {
            addCriterion("extract_rule not between", value1, value2, "extractRule");
            return (Criteria) this;
        }

        public Criteria andSwitchRuleIsNull() {
            addCriterion("switch_rule is null");
            return (Criteria) this;
        }

        public Criteria andSwitchRuleIsNotNull() {
            addCriterion("switch_rule is not null");
            return (Criteria) this;
        }

        public Criteria andSwitchRuleEqualTo(String value) {
            addCriterion("switch_rule =", value, "switchRule");
            return (Criteria) this;
        }

        public Criteria andSwitchRuleNotEqualTo(String value) {
            addCriterion("switch_rule <>", value, "switchRule");
            return (Criteria) this;
        }

        public Criteria andSwitchRuleGreaterThan(String value) {
            addCriterion("switch_rule >", value, "switchRule");
            return (Criteria) this;
        }

        public Criteria andSwitchRuleGreaterThanOrEqualTo(String value) {
            addCriterion("switch_rule >=", value, "switchRule");
            return (Criteria) this;
        }

        public Criteria andSwitchRuleLessThan(String value) {
            addCriterion("switch_rule <", value, "switchRule");
            return (Criteria) this;
        }

        public Criteria andSwitchRuleLessThanOrEqualTo(String value) {
            addCriterion("switch_rule <=", value, "switchRule");
            return (Criteria) this;
        }

        public Criteria andSwitchRuleLike(String value) {
            addCriterion("switch_rule like", value, "switchRule");
            return (Criteria) this;
        }

        public Criteria andSwitchRuleNotLike(String value) {
            addCriterion("switch_rule not like", value, "switchRule");
            return (Criteria) this;
        }

        public Criteria andSwitchRuleIn(List<String> values) {
            addCriterion("switch_rule in", values, "switchRule");
            return (Criteria) this;
        }

        public Criteria andSwitchRuleNotIn(List<String> values) {
            addCriterion("switch_rule not in", values, "switchRule");
            return (Criteria) this;
        }

        public Criteria andSwitchRuleBetween(String value1, String value2) {
            addCriterion("switch_rule between", value1, value2, "switchRule");
            return (Criteria) this;
        }

        public Criteria andSwitchRuleNotBetween(String value1, String value2) {
            addCriterion("switch_rule not between", value1, value2, "switchRule");
            return (Criteria) this;
        }

        public Criteria andReplaceRuleIsNull() {
            addCriterion("replace_rule is null");
            return (Criteria) this;
        }

        public Criteria andReplaceRuleIsNotNull() {
            addCriterion("replace_rule is not null");
            return (Criteria) this;
        }

        public Criteria andReplaceRuleEqualTo(String value) {
            addCriterion("replace_rule =", value, "replaceRule");
            return (Criteria) this;
        }

        public Criteria andReplaceRuleNotEqualTo(String value) {
            addCriterion("replace_rule <>", value, "replaceRule");
            return (Criteria) this;
        }

        public Criteria andReplaceRuleGreaterThan(String value) {
            addCriterion("replace_rule >", value, "replaceRule");
            return (Criteria) this;
        }

        public Criteria andReplaceRuleGreaterThanOrEqualTo(String value) {
            addCriterion("replace_rule >=", value, "replaceRule");
            return (Criteria) this;
        }

        public Criteria andReplaceRuleLessThan(String value) {
            addCriterion("replace_rule <", value, "replaceRule");
            return (Criteria) this;
        }

        public Criteria andReplaceRuleLessThanOrEqualTo(String value) {
            addCriterion("replace_rule <=", value, "replaceRule");
            return (Criteria) this;
        }

        public Criteria andReplaceRuleLike(String value) {
            addCriterion("replace_rule like", value, "replaceRule");
            return (Criteria) this;
        }

        public Criteria andReplaceRuleNotLike(String value) {
            addCriterion("replace_rule not like", value, "replaceRule");
            return (Criteria) this;
        }

        public Criteria andReplaceRuleIn(List<String> values) {
            addCriterion("replace_rule in", values, "replaceRule");
            return (Criteria) this;
        }

        public Criteria andReplaceRuleNotIn(List<String> values) {
            addCriterion("replace_rule not in", values, "replaceRule");
            return (Criteria) this;
        }

        public Criteria andReplaceRuleBetween(String value1, String value2) {
            addCriterion("replace_rule between", value1, value2, "replaceRule");
            return (Criteria) this;
        }

        public Criteria andReplaceRuleNotBetween(String value1, String value2) {
            addCriterion("replace_rule not between", value1, value2, "replaceRule");
            return (Criteria) this;
        }

        public Criteria andSupplementRuleIsNull() {
            addCriterion("supplement_rule is null");
            return (Criteria) this;
        }

        public Criteria andSupplementRuleIsNotNull() {
            addCriterion("supplement_rule is not null");
            return (Criteria) this;
        }

        public Criteria andSupplementRuleEqualTo(String value) {
            addCriterion("supplement_rule =", value, "supplementRule");
            return (Criteria) this;
        }

        public Criteria andSupplementRuleNotEqualTo(String value) {
            addCriterion("supplement_rule <>", value, "supplementRule");
            return (Criteria) this;
        }

        public Criteria andSupplementRuleGreaterThan(String value) {
            addCriterion("supplement_rule >", value, "supplementRule");
            return (Criteria) this;
        }

        public Criteria andSupplementRuleGreaterThanOrEqualTo(String value) {
            addCriterion("supplement_rule >=", value, "supplementRule");
            return (Criteria) this;
        }

        public Criteria andSupplementRuleLessThan(String value) {
            addCriterion("supplement_rule <", value, "supplementRule");
            return (Criteria) this;
        }

        public Criteria andSupplementRuleLessThanOrEqualTo(String value) {
            addCriterion("supplement_rule <=", value, "supplementRule");
            return (Criteria) this;
        }

        public Criteria andSupplementRuleLike(String value) {
            addCriterion("supplement_rule like", value, "supplementRule");
            return (Criteria) this;
        }

        public Criteria andSupplementRuleNotLike(String value) {
            addCriterion("supplement_rule not like", value, "supplementRule");
            return (Criteria) this;
        }

        public Criteria andSupplementRuleIn(List<String> values) {
            addCriterion("supplement_rule in", values, "supplementRule");
            return (Criteria) this;
        }

        public Criteria andSupplementRuleNotIn(List<String> values) {
            addCriterion("supplement_rule not in", values, "supplementRule");
            return (Criteria) this;
        }

        public Criteria andSupplementRuleBetween(String value1, String value2) {
            addCriterion("supplement_rule between", value1, value2, "supplementRule");
            return (Criteria) this;
        }

        public Criteria andSupplementRuleNotBetween(String value1, String value2) {
            addCriterion("supplement_rule not between", value1, value2, "supplementRule");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("category is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("category is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(String value) {
            addCriterion("category =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(String value) {
            addCriterion("category <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(String value) {
            addCriterion("category >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("category >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(String value) {
            addCriterion("category <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(String value) {
            addCriterion("category <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLike(String value) {
            addCriterion("category like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotLike(String value) {
            addCriterion("category not like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<String> values) {
            addCriterion("category in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<String> values) {
            addCriterion("category not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(String value1, String value2) {
            addCriterion("category between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(String value1, String value2) {
            addCriterion("category not between", value1, value2, "category");
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

        public Criteria andIsEnabledIsNull() {
            addCriterion("is_enabled is null");
            return (Criteria) this;
        }

        public Criteria andIsEnabledIsNotNull() {
            addCriterion("is_enabled is not null");
            return (Criteria) this;
        }

        public Criteria andIsEnabledEqualTo(Integer value) {
            addCriterion("is_enabled =", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledNotEqualTo(Integer value) {
            addCriterion("is_enabled <>", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledGreaterThan(Integer value) {
            addCriterion("is_enabled >", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_enabled >=", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledLessThan(Integer value) {
            addCriterion("is_enabled <", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledLessThanOrEqualTo(Integer value) {
            addCriterion("is_enabled <=", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledIn(List<Integer> values) {
            addCriterion("is_enabled in", values, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledNotIn(List<Integer> values) {
            addCriterion("is_enabled not in", values, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledBetween(Integer value1, Integer value2) {
            addCriterion("is_enabled between", value1, value2, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledNotBetween(Integer value1, Integer value2) {
            addCriterion("is_enabled not between", value1, value2, "isEnabled");
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

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("gmt_modified is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("gmt_modified is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(Date value) {
            addCriterion("gmt_modified =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(Date value) {
            addCriterion("gmt_modified <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(Date value) {
            addCriterion("gmt_modified >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modified >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(Date value) {
            addCriterion("gmt_modified <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modified <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<Date> values) {
            addCriterion("gmt_modified in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<Date> values) {
            addCriterion("gmt_modified not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(Date value1, Date value2) {
            addCriterion("gmt_modified between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modified not between", value1, value2, "gmtModified");
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