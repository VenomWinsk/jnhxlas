package com.hxht.logprocess.logbulk.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NodeStatusExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NodeStatusExample() {
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

        public Criteria andIsAliveIsNull() {
            addCriterion("is_alive is null");
            return (Criteria) this;
        }

        public Criteria andIsAliveIsNotNull() {
            addCriterion("is_alive is not null");
            return (Criteria) this;
        }

        public Criteria andIsAliveEqualTo(Integer value) {
            addCriterion("is_alive =", value, "isAlive");
            return (Criteria) this;
        }

        public Criteria andIsAliveNotEqualTo(Integer value) {
            addCriterion("is_alive <>", value, "isAlive");
            return (Criteria) this;
        }

        public Criteria andIsAliveGreaterThan(Integer value) {
            addCriterion("is_alive >", value, "isAlive");
            return (Criteria) this;
        }

        public Criteria andIsAliveGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_alive >=", value, "isAlive");
            return (Criteria) this;
        }

        public Criteria andIsAliveLessThan(Integer value) {
            addCriterion("is_alive <", value, "isAlive");
            return (Criteria) this;
        }

        public Criteria andIsAliveLessThanOrEqualTo(Integer value) {
            addCriterion("is_alive <=", value, "isAlive");
            return (Criteria) this;
        }

        public Criteria andIsAliveIn(List<Integer> values) {
            addCriterion("is_alive in", values, "isAlive");
            return (Criteria) this;
        }

        public Criteria andIsAliveNotIn(List<Integer> values) {
            addCriterion("is_alive not in", values, "isAlive");
            return (Criteria) this;
        }

        public Criteria andIsAliveBetween(Integer value1, Integer value2) {
            addCriterion("is_alive between", value1, value2, "isAlive");
            return (Criteria) this;
        }

        public Criteria andIsAliveNotBetween(Integer value1, Integer value2) {
            addCriterion("is_alive not between", value1, value2, "isAlive");
            return (Criteria) this;
        }

        public Criteria andCpuRateIsNull() {
            addCriterion("cpu_rate is null");
            return (Criteria) this;
        }

        public Criteria andCpuRateIsNotNull() {
            addCriterion("cpu_rate is not null");
            return (Criteria) this;
        }

        public Criteria andCpuRateEqualTo(Long value) {
            addCriterion("cpu_rate =", value, "cpuRate");
            return (Criteria) this;
        }

        public Criteria andCpuRateNotEqualTo(Long value) {
            addCriterion("cpu_rate <>", value, "cpuRate");
            return (Criteria) this;
        }

        public Criteria andCpuRateGreaterThan(Long value) {
            addCriterion("cpu_rate >", value, "cpuRate");
            return (Criteria) this;
        }

        public Criteria andCpuRateGreaterThanOrEqualTo(Long value) {
            addCriterion("cpu_rate >=", value, "cpuRate");
            return (Criteria) this;
        }

        public Criteria andCpuRateLessThan(Long value) {
            addCriterion("cpu_rate <", value, "cpuRate");
            return (Criteria) this;
        }

        public Criteria andCpuRateLessThanOrEqualTo(Long value) {
            addCriterion("cpu_rate <=", value, "cpuRate");
            return (Criteria) this;
        }

        public Criteria andCpuRateIn(List<Long> values) {
            addCriterion("cpu_rate in", values, "cpuRate");
            return (Criteria) this;
        }

        public Criteria andCpuRateNotIn(List<Long> values) {
            addCriterion("cpu_rate not in", values, "cpuRate");
            return (Criteria) this;
        }

        public Criteria andCpuRateBetween(Long value1, Long value2) {
            addCriterion("cpu_rate between", value1, value2, "cpuRate");
            return (Criteria) this;
        }

        public Criteria andCpuRateNotBetween(Long value1, Long value2) {
            addCriterion("cpu_rate not between", value1, value2, "cpuRate");
            return (Criteria) this;
        }

        public Criteria andAvalibleDistIsNull() {
            addCriterion("avalible_dist is null");
            return (Criteria) this;
        }

        public Criteria andAvalibleDistIsNotNull() {
            addCriterion("avalible_dist is not null");
            return (Criteria) this;
        }

        public Criteria andAvalibleDistEqualTo(Long value) {
            addCriterion("avalible_dist =", value, "avalibleDist");
            return (Criteria) this;
        }

        public Criteria andAvalibleDistNotEqualTo(Long value) {
            addCriterion("avalible_dist <>", value, "avalibleDist");
            return (Criteria) this;
        }

        public Criteria andAvalibleDistGreaterThan(Long value) {
            addCriterion("avalible_dist >", value, "avalibleDist");
            return (Criteria) this;
        }

        public Criteria andAvalibleDistGreaterThanOrEqualTo(Long value) {
            addCriterion("avalible_dist >=", value, "avalibleDist");
            return (Criteria) this;
        }

        public Criteria andAvalibleDistLessThan(Long value) {
            addCriterion("avalible_dist <", value, "avalibleDist");
            return (Criteria) this;
        }

        public Criteria andAvalibleDistLessThanOrEqualTo(Long value) {
            addCriterion("avalible_dist <=", value, "avalibleDist");
            return (Criteria) this;
        }

        public Criteria andAvalibleDistIn(List<Long> values) {
            addCriterion("avalible_dist in", values, "avalibleDist");
            return (Criteria) this;
        }

        public Criteria andAvalibleDistNotIn(List<Long> values) {
            addCriterion("avalible_dist not in", values, "avalibleDist");
            return (Criteria) this;
        }

        public Criteria andAvalibleDistBetween(Long value1, Long value2) {
            addCriterion("avalible_dist between", value1, value2, "avalibleDist");
            return (Criteria) this;
        }

        public Criteria andAvalibleDistNotBetween(Long value1, Long value2) {
            addCriterion("avalible_dist not between", value1, value2, "avalibleDist");
            return (Criteria) this;
        }

        public Criteria andAllDistIsNull() {
            addCriterion("all_dist is null");
            return (Criteria) this;
        }

        public Criteria andAllDistIsNotNull() {
            addCriterion("all_dist is not null");
            return (Criteria) this;
        }

        public Criteria andAllDistEqualTo(Long value) {
            addCriterion("all_dist =", value, "allDist");
            return (Criteria) this;
        }

        public Criteria andAllDistNotEqualTo(Long value) {
            addCriterion("all_dist <>", value, "allDist");
            return (Criteria) this;
        }

        public Criteria andAllDistGreaterThan(Long value) {
            addCriterion("all_dist >", value, "allDist");
            return (Criteria) this;
        }

        public Criteria andAllDistGreaterThanOrEqualTo(Long value) {
            addCriterion("all_dist >=", value, "allDist");
            return (Criteria) this;
        }

        public Criteria andAllDistLessThan(Long value) {
            addCriterion("all_dist <", value, "allDist");
            return (Criteria) this;
        }

        public Criteria andAllDistLessThanOrEqualTo(Long value) {
            addCriterion("all_dist <=", value, "allDist");
            return (Criteria) this;
        }

        public Criteria andAllDistIn(List<Long> values) {
            addCriterion("all_dist in", values, "allDist");
            return (Criteria) this;
        }

        public Criteria andAllDistNotIn(List<Long> values) {
            addCriterion("all_dist not in", values, "allDist");
            return (Criteria) this;
        }

        public Criteria andAllDistBetween(Long value1, Long value2) {
            addCriterion("all_dist between", value1, value2, "allDist");
            return (Criteria) this;
        }

        public Criteria andAllDistNotBetween(Long value1, Long value2) {
            addCriterion("all_dist not between", value1, value2, "allDist");
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