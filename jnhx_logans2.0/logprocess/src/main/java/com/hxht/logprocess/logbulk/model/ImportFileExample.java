package com.hxht.logprocess.logbulk.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImportFileExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ImportFileExample() {
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

        public Criteria andFileNameIsNull() {
            addCriterion("file_name is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("file_name is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("file_name =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("file_name <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("file_name >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("file_name >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("file_name <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("file_name <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("file_name like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("file_name not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("file_name in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("file_name not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("file_name between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("file_name not between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNull() {
            addCriterion("file_path is null");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNotNull() {
            addCriterion("file_path is not null");
            return (Criteria) this;
        }

        public Criteria andFilePathEqualTo(String value) {
            addCriterion("file_path =", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotEqualTo(String value) {
            addCriterion("file_path <>", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThan(String value) {
            addCriterion("file_path >", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThanOrEqualTo(String value) {
            addCriterion("file_path >=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThan(String value) {
            addCriterion("file_path <", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThanOrEqualTo(String value) {
            addCriterion("file_path <=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLike(String value) {
            addCriterion("file_path like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotLike(String value) {
            addCriterion("file_path not like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathIn(List<String> values) {
            addCriterion("file_path in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotIn(List<String> values) {
            addCriterion("file_path not in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathBetween(String value1, String value2) {
            addCriterion("file_path between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotBetween(String value1, String value2) {
            addCriterion("file_path not between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andIsAllFileIsNull() {
            addCriterion("is_all_file is null");
            return (Criteria) this;
        }

        public Criteria andIsAllFileIsNotNull() {
            addCriterion("is_all_file is not null");
            return (Criteria) this;
        }

        public Criteria andIsAllFileEqualTo(Integer value) {
            addCriterion("is_all_file =", value, "isAllFile");
            return (Criteria) this;
        }

        public Criteria andIsAllFileNotEqualTo(Integer value) {
            addCriterion("is_all_file <>", value, "isAllFile");
            return (Criteria) this;
        }

        public Criteria andIsAllFileGreaterThan(Integer value) {
            addCriterion("is_all_file >", value, "isAllFile");
            return (Criteria) this;
        }

        public Criteria andIsAllFileGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_all_file >=", value, "isAllFile");
            return (Criteria) this;
        }

        public Criteria andIsAllFileLessThan(Integer value) {
            addCriterion("is_all_file <", value, "isAllFile");
            return (Criteria) this;
        }

        public Criteria andIsAllFileLessThanOrEqualTo(Integer value) {
            addCriterion("is_all_file <=", value, "isAllFile");
            return (Criteria) this;
        }

        public Criteria andIsAllFileIn(List<Integer> values) {
            addCriterion("is_all_file in", values, "isAllFile");
            return (Criteria) this;
        }

        public Criteria andIsAllFileNotIn(List<Integer> values) {
            addCriterion("is_all_file not in", values, "isAllFile");
            return (Criteria) this;
        }

        public Criteria andIsAllFileBetween(Integer value1, Integer value2) {
            addCriterion("is_all_file between", value1, value2, "isAllFile");
            return (Criteria) this;
        }

        public Criteria andIsAllFileNotBetween(Integer value1, Integer value2) {
            addCriterion("is_all_file not between", value1, value2, "isAllFile");
            return (Criteria) this;
        }

        public Criteria andOffsetIsNull() {
            addCriterion("offset is null");
            return (Criteria) this;
        }

        public Criteria andOffsetIsNotNull() {
            addCriterion("offset is not null");
            return (Criteria) this;
        }

        public Criteria andOffsetEqualTo(Integer value) {
            addCriterion("offset =", value, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetNotEqualTo(Integer value) {
            addCriterion("offset <>", value, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetGreaterThan(Integer value) {
            addCriterion("offset >", value, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetGreaterThanOrEqualTo(Integer value) {
            addCriterion("offset >=", value, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetLessThan(Integer value) {
            addCriterion("offset <", value, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetLessThanOrEqualTo(Integer value) {
            addCriterion("offset <=", value, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetIn(List<Integer> values) {
            addCriterion("offset in", values, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetNotIn(List<Integer> values) {
            addCriterion("offset not in", values, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetBetween(Integer value1, Integer value2) {
            addCriterion("offset between", value1, value2, "offset");
            return (Criteria) this;
        }

        public Criteria andOffsetNotBetween(Integer value1, Integer value2) {
            addCriterion("offset not between", value1, value2, "offset");
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