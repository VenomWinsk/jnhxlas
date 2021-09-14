package com.hxht.logprocess.logbulk.model;

import java.util.ArrayList;
import java.util.List;

public class FileRecordsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FileRecordsExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFiledirIdIsNull() {
            addCriterion("filedir_id is null");
            return (Criteria) this;
        }

        public Criteria andFiledirIdIsNotNull() {
            addCriterion("filedir_id is not null");
            return (Criteria) this;
        }

        public Criteria andFiledirIdEqualTo(String value) {
            addCriterion("filedir_id =", value, "filedirId");
            return (Criteria) this;
        }

        public Criteria andFiledirIdNotEqualTo(String value) {
            addCriterion("filedir_id <>", value, "filedirId");
            return (Criteria) this;
        }

        public Criteria andFiledirIdGreaterThan(String value) {
            addCriterion("filedir_id >", value, "filedirId");
            return (Criteria) this;
        }

        public Criteria andFiledirIdGreaterThanOrEqualTo(String value) {
            addCriterion("filedir_id >=", value, "filedirId");
            return (Criteria) this;
        }

        public Criteria andFiledirIdLessThan(String value) {
            addCriterion("filedir_id <", value, "filedirId");
            return (Criteria) this;
        }

        public Criteria andFiledirIdLessThanOrEqualTo(String value) {
            addCriterion("filedir_id <=", value, "filedirId");
            return (Criteria) this;
        }

        public Criteria andFiledirIdLike(String value) {
            addCriterion("filedir_id like", value, "filedirId");
            return (Criteria) this;
        }

        public Criteria andFiledirIdNotLike(String value) {
            addCriterion("filedir_id not like", value, "filedirId");
            return (Criteria) this;
        }

        public Criteria andFiledirIdIn(List<String> values) {
            addCriterion("filedir_id in", values, "filedirId");
            return (Criteria) this;
        }

        public Criteria andFiledirIdNotIn(List<String> values) {
            addCriterion("filedir_id not in", values, "filedirId");
            return (Criteria) this;
        }

        public Criteria andFiledirIdBetween(String value1, String value2) {
            addCriterion("filedir_id between", value1, value2, "filedirId");
            return (Criteria) this;
        }

        public Criteria andFiledirIdNotBetween(String value1, String value2) {
            addCriterion("filedir_id not between", value1, value2, "filedirId");
            return (Criteria) this;
        }

        public Criteria andFilenameIsNull() {
            addCriterion("filename is null");
            return (Criteria) this;
        }

        public Criteria andFilenameIsNotNull() {
            addCriterion("filename is not null");
            return (Criteria) this;
        }

        public Criteria andFilenameEqualTo(String value) {
            addCriterion("filename =", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameNotEqualTo(String value) {
            addCriterion("filename <>", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameGreaterThan(String value) {
            addCriterion("filename >", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameGreaterThanOrEqualTo(String value) {
            addCriterion("filename >=", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameLessThan(String value) {
            addCriterion("filename <", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameLessThanOrEqualTo(String value) {
            addCriterion("filename <=", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameLike(String value) {
            addCriterion("filename like", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameNotLike(String value) {
            addCriterion("filename not like", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameIn(List<String> values) {
            addCriterion("filename in", values, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameNotIn(List<String> values) {
            addCriterion("filename not in", values, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameBetween(String value1, String value2) {
            addCriterion("filename between", value1, value2, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameNotBetween(String value1, String value2) {
            addCriterion("filename not between", value1, value2, "filename");
            return (Criteria) this;
        }

        public Criteria andRunStatusIsNull() {
            addCriterion("run_status is null");
            return (Criteria) this;
        }

        public Criteria andRunStatusIsNotNull() {
            addCriterion("run_status is not null");
            return (Criteria) this;
        }

        public Criteria andRunStatusEqualTo(Integer value) {
            addCriterion("run_status =", value, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusNotEqualTo(Integer value) {
            addCriterion("run_status <>", value, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusGreaterThan(Integer value) {
            addCriterion("run_status >", value, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("run_status >=", value, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusLessThan(Integer value) {
            addCriterion("run_status <", value, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusLessThanOrEqualTo(Integer value) {
            addCriterion("run_status <=", value, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusIn(List<Integer> values) {
            addCriterion("run_status in", values, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusNotIn(List<Integer> values) {
            addCriterion("run_status not in", values, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusBetween(Integer value1, Integer value2) {
            addCriterion("run_status between", value1, value2, "runStatus");
            return (Criteria) this;
        }

        public Criteria andRunStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("run_status not between", value1, value2, "runStatus");
            return (Criteria) this;
        }

        public Criteria andFileSizeIsNull() {
            addCriterion("file_size is null");
            return (Criteria) this;
        }

        public Criteria andFileSizeIsNotNull() {
            addCriterion("file_size is not null");
            return (Criteria) this;
        }

        public Criteria andFileSizeEqualTo(Integer value) {
            addCriterion("file_size =", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotEqualTo(Integer value) {
            addCriterion("file_size <>", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeGreaterThan(Integer value) {
            addCriterion("file_size >", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("file_size >=", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeLessThan(Integer value) {
            addCriterion("file_size <", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeLessThanOrEqualTo(Integer value) {
            addCriterion("file_size <=", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeIn(List<Integer> values) {
            addCriterion("file_size in", values, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotIn(List<Integer> values) {
            addCriterion("file_size not in", values, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeBetween(Integer value1, Integer value2) {
            addCriterion("file_size between", value1, value2, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("file_size not between", value1, value2, "fileSize");
            return (Criteria) this;
        }

        public Criteria andDataLengthIsNull() {
            addCriterion("data_length is null");
            return (Criteria) this;
        }

        public Criteria andDataLengthIsNotNull() {
            addCriterion("data_length is not null");
            return (Criteria) this;
        }

        public Criteria andDataLengthEqualTo(Integer value) {
            addCriterion("data_length =", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthNotEqualTo(Integer value) {
            addCriterion("data_length <>", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthGreaterThan(Integer value) {
            addCriterion("data_length >", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("data_length >=", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthLessThan(Integer value) {
            addCriterion("data_length <", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthLessThanOrEqualTo(Integer value) {
            addCriterion("data_length <=", value, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthIn(List<Integer> values) {
            addCriterion("data_length in", values, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthNotIn(List<Integer> values) {
            addCriterion("data_length not in", values, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthBetween(Integer value1, Integer value2) {
            addCriterion("data_length between", value1, value2, "dataLength");
            return (Criteria) this;
        }

        public Criteria andDataLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("data_length not between", value1, value2, "dataLength");
            return (Criteria) this;
        }

        public Criteria andFileCodeIsNull() {
            addCriterion("file_code is null");
            return (Criteria) this;
        }

        public Criteria andFileCodeIsNotNull() {
            addCriterion("file_code is not null");
            return (Criteria) this;
        }

        public Criteria andFileCodeEqualTo(String value) {
            addCriterion("file_code =", value, "fileCode");
            return (Criteria) this;
        }

        public Criteria andFileCodeNotEqualTo(String value) {
            addCriterion("file_code <>", value, "fileCode");
            return (Criteria) this;
        }

        public Criteria andFileCodeGreaterThan(String value) {
            addCriterion("file_code >", value, "fileCode");
            return (Criteria) this;
        }

        public Criteria andFileCodeGreaterThanOrEqualTo(String value) {
            addCriterion("file_code >=", value, "fileCode");
            return (Criteria) this;
        }

        public Criteria andFileCodeLessThan(String value) {
            addCriterion("file_code <", value, "fileCode");
            return (Criteria) this;
        }

        public Criteria andFileCodeLessThanOrEqualTo(String value) {
            addCriterion("file_code <=", value, "fileCode");
            return (Criteria) this;
        }

        public Criteria andFileCodeLike(String value) {
            addCriterion("file_code like", value, "fileCode");
            return (Criteria) this;
        }

        public Criteria andFileCodeNotLike(String value) {
            addCriterion("file_code not like", value, "fileCode");
            return (Criteria) this;
        }

        public Criteria andFileCodeIn(List<String> values) {
            addCriterion("file_code in", values, "fileCode");
            return (Criteria) this;
        }

        public Criteria andFileCodeNotIn(List<String> values) {
            addCriterion("file_code not in", values, "fileCode");
            return (Criteria) this;
        }

        public Criteria andFileCodeBetween(String value1, String value2) {
            addCriterion("file_code between", value1, value2, "fileCode");
            return (Criteria) this;
        }

        public Criteria andFileCodeNotBetween(String value1, String value2) {
            addCriterion("file_code not between", value1, value2, "fileCode");
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