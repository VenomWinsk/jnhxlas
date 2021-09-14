package com.hxht.logprocess.logbulk.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImportStatisticsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ImportStatisticsExample() {
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

        public Criteria andProjectAnalysisIdIsNull() {
            addCriterion("project_analysis_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectAnalysisIdIsNotNull() {
            addCriterion("project_analysis_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectAnalysisIdEqualTo(String value) {
            addCriterion("project_analysis_id =", value, "projectAnalysisId");
            return (Criteria) this;
        }

        public Criteria andProjectAnalysisIdNotEqualTo(String value) {
            addCriterion("project_analysis_id <>", value, "projectAnalysisId");
            return (Criteria) this;
        }

        public Criteria andProjectAnalysisIdGreaterThan(String value) {
            addCriterion("project_analysis_id >", value, "projectAnalysisId");
            return (Criteria) this;
        }

        public Criteria andProjectAnalysisIdGreaterThanOrEqualTo(String value) {
            addCriterion("project_analysis_id >=", value, "projectAnalysisId");
            return (Criteria) this;
        }

        public Criteria andProjectAnalysisIdLessThan(String value) {
            addCriterion("project_analysis_id <", value, "projectAnalysisId");
            return (Criteria) this;
        }

        public Criteria andProjectAnalysisIdLessThanOrEqualTo(String value) {
            addCriterion("project_analysis_id <=", value, "projectAnalysisId");
            return (Criteria) this;
        }

        public Criteria andProjectAnalysisIdLike(String value) {
            addCriterion("project_analysis_id like", value, "projectAnalysisId");
            return (Criteria) this;
        }

        public Criteria andProjectAnalysisIdNotLike(String value) {
            addCriterion("project_analysis_id not like", value, "projectAnalysisId");
            return (Criteria) this;
        }

        public Criteria andProjectAnalysisIdIn(List<String> values) {
            addCriterion("project_analysis_id in", values, "projectAnalysisId");
            return (Criteria) this;
        }

        public Criteria andProjectAnalysisIdNotIn(List<String> values) {
            addCriterion("project_analysis_id not in", values, "projectAnalysisId");
            return (Criteria) this;
        }

        public Criteria andProjectAnalysisIdBetween(String value1, String value2) {
            addCriterion("project_analysis_id between", value1, value2, "projectAnalysisId");
            return (Criteria) this;
        }

        public Criteria andProjectAnalysisIdNotBetween(String value1, String value2) {
            addCriterion("project_analysis_id not between", value1, value2, "projectAnalysisId");
            return (Criteria) this;
        }

        public Criteria andNodeIpIsNull() {
            addCriterion("node_ip is null");
            return (Criteria) this;
        }

        public Criteria andNodeIpIsNotNull() {
            addCriterion("node_ip is not null");
            return (Criteria) this;
        }

        public Criteria andNodeIpEqualTo(String value) {
            addCriterion("node_ip =", value, "nodeIp");
            return (Criteria) this;
        }

        public Criteria andNodeIpNotEqualTo(String value) {
            addCriterion("node_ip <>", value, "nodeIp");
            return (Criteria) this;
        }

        public Criteria andNodeIpGreaterThan(String value) {
            addCriterion("node_ip >", value, "nodeIp");
            return (Criteria) this;
        }

        public Criteria andNodeIpGreaterThanOrEqualTo(String value) {
            addCriterion("node_ip >=", value, "nodeIp");
            return (Criteria) this;
        }

        public Criteria andNodeIpLessThan(String value) {
            addCriterion("node_ip <", value, "nodeIp");
            return (Criteria) this;
        }

        public Criteria andNodeIpLessThanOrEqualTo(String value) {
            addCriterion("node_ip <=", value, "nodeIp");
            return (Criteria) this;
        }

        public Criteria andNodeIpLike(String value) {
            addCriterion("node_ip like", value, "nodeIp");
            return (Criteria) this;
        }

        public Criteria andNodeIpNotLike(String value) {
            addCriterion("node_ip not like", value, "nodeIp");
            return (Criteria) this;
        }

        public Criteria andNodeIpIn(List<String> values) {
            addCriterion("node_ip in", values, "nodeIp");
            return (Criteria) this;
        }

        public Criteria andNodeIpNotIn(List<String> values) {
            addCriterion("node_ip not in", values, "nodeIp");
            return (Criteria) this;
        }

        public Criteria andNodeIpBetween(String value1, String value2) {
            addCriterion("node_ip between", value1, value2, "nodeIp");
            return (Criteria) this;
        }

        public Criteria andNodeIpNotBetween(String value1, String value2) {
            addCriterion("node_ip not between", value1, value2, "nodeIp");
            return (Criteria) this;
        }

        public Criteria andNodeNameIsNull() {
            addCriterion("node_name is null");
            return (Criteria) this;
        }

        public Criteria andNodeNameIsNotNull() {
            addCriterion("node_name is not null");
            return (Criteria) this;
        }

        public Criteria andNodeNameEqualTo(String value) {
            addCriterion("node_name =", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotEqualTo(String value) {
            addCriterion("node_name <>", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameGreaterThan(String value) {
            addCriterion("node_name >", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameGreaterThanOrEqualTo(String value) {
            addCriterion("node_name >=", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameLessThan(String value) {
            addCriterion("node_name <", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameLessThanOrEqualTo(String value) {
            addCriterion("node_name <=", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameLike(String value) {
            addCriterion("node_name like", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotLike(String value) {
            addCriterion("node_name not like", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameIn(List<String> values) {
            addCriterion("node_name in", values, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotIn(List<String> values) {
            addCriterion("node_name not in", values, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameBetween(String value1, String value2) {
            addCriterion("node_name between", value1, value2, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotBetween(String value1, String value2) {
            addCriterion("node_name not between", value1, value2, "nodeName");
            return (Criteria) this;
        }

        public Criteria andImportBytesIsNull() {
            addCriterion("import_bytes is null");
            return (Criteria) this;
        }

        public Criteria andImportBytesIsNotNull() {
            addCriterion("import_bytes is not null");
            return (Criteria) this;
        }

        public Criteria andImportBytesEqualTo(Integer value) {
            addCriterion("import_bytes =", value, "importBytes");
            return (Criteria) this;
        }

        public Criteria andImportBytesNotEqualTo(Integer value) {
            addCriterion("import_bytes <>", value, "importBytes");
            return (Criteria) this;
        }

        public Criteria andImportBytesGreaterThan(Integer value) {
            addCriterion("import_bytes >", value, "importBytes");
            return (Criteria) this;
        }

        public Criteria andImportBytesGreaterThanOrEqualTo(Integer value) {
            addCriterion("import_bytes >=", value, "importBytes");
            return (Criteria) this;
        }

        public Criteria andImportBytesLessThan(Integer value) {
            addCriterion("import_bytes <", value, "importBytes");
            return (Criteria) this;
        }

        public Criteria andImportBytesLessThanOrEqualTo(Integer value) {
            addCriterion("import_bytes <=", value, "importBytes");
            return (Criteria) this;
        }

        public Criteria andImportBytesIn(List<Integer> values) {
            addCriterion("import_bytes in", values, "importBytes");
            return (Criteria) this;
        }

        public Criteria andImportBytesNotIn(List<Integer> values) {
            addCriterion("import_bytes not in", values, "importBytes");
            return (Criteria) this;
        }

        public Criteria andImportBytesBetween(Integer value1, Integer value2) {
            addCriterion("import_bytes between", value1, value2, "importBytes");
            return (Criteria) this;
        }

        public Criteria andImportBytesNotBetween(Integer value1, Integer value2) {
            addCriterion("import_bytes not between", value1, value2, "importBytes");
            return (Criteria) this;
        }

        public Criteria andImportCountIsNull() {
            addCriterion("import_count is null");
            return (Criteria) this;
        }

        public Criteria andImportCountIsNotNull() {
            addCriterion("import_count is not null");
            return (Criteria) this;
        }

        public Criteria andImportCountEqualTo(Integer value) {
            addCriterion("import_count =", value, "importCount");
            return (Criteria) this;
        }

        public Criteria andImportCountNotEqualTo(Integer value) {
            addCriterion("import_count <>", value, "importCount");
            return (Criteria) this;
        }

        public Criteria andImportCountGreaterThan(Integer value) {
            addCriterion("import_count >", value, "importCount");
            return (Criteria) this;
        }

        public Criteria andImportCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("import_count >=", value, "importCount");
            return (Criteria) this;
        }

        public Criteria andImportCountLessThan(Integer value) {
            addCriterion("import_count <", value, "importCount");
            return (Criteria) this;
        }

        public Criteria andImportCountLessThanOrEqualTo(Integer value) {
            addCriterion("import_count <=", value, "importCount");
            return (Criteria) this;
        }

        public Criteria andImportCountIn(List<Integer> values) {
            addCriterion("import_count in", values, "importCount");
            return (Criteria) this;
        }

        public Criteria andImportCountNotIn(List<Integer> values) {
            addCriterion("import_count not in", values, "importCount");
            return (Criteria) this;
        }

        public Criteria andImportCountBetween(Integer value1, Integer value2) {
            addCriterion("import_count between", value1, value2, "importCount");
            return (Criteria) this;
        }

        public Criteria andImportCountNotBetween(Integer value1, Integer value2) {
            addCriterion("import_count not between", value1, value2, "importCount");
            return (Criteria) this;
        }

        public Criteria andCreateAtIsNull() {
            addCriterion("create_at is null");
            return (Criteria) this;
        }

        public Criteria andCreateAtIsNotNull() {
            addCriterion("create_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreateAtEqualTo(Date value) {
            addCriterion("create_at =", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtNotEqualTo(Date value) {
            addCriterion("create_at <>", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtGreaterThan(Date value) {
            addCriterion("create_at >", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtGreaterThanOrEqualTo(Date value) {
            addCriterion("create_at >=", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtLessThan(Date value) {
            addCriterion("create_at <", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtLessThanOrEqualTo(Date value) {
            addCriterion("create_at <=", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtIn(List<Date> values) {
            addCriterion("create_at in", values, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtNotIn(List<Date> values) {
            addCriterion("create_at not in", values, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtBetween(Date value1, Date value2) {
            addCriterion("create_at between", value1, value2, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtNotBetween(Date value1, Date value2) {
            addCriterion("create_at not between", value1, value2, "createAt");
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