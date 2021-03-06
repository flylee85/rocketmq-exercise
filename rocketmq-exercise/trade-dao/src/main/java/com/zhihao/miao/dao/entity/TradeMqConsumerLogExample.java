package com.zhihao.miao.dao.entity;

import java.util.ArrayList;
import java.util.List;

public class TradeMqConsumerLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TradeMqConsumerLogExample() {
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

        public Criteria andGourpNameIsNull() {
            addCriterion("gourp_name is null");
            return (Criteria) this;
        }

        public Criteria andGourpNameIsNotNull() {
            addCriterion("gourp_name is not null");
            return (Criteria) this;
        }

        public Criteria andGourpNameEqualTo(String value) {
            addCriterion("gourp_name =", value, "gourpName");
            return (Criteria) this;
        }

        public Criteria andGourpNameNotEqualTo(String value) {
            addCriterion("gourp_name <>", value, "gourpName");
            return (Criteria) this;
        }

        public Criteria andGourpNameGreaterThan(String value) {
            addCriterion("gourp_name >", value, "gourpName");
            return (Criteria) this;
        }

        public Criteria andGourpNameGreaterThanOrEqualTo(String value) {
            addCriterion("gourp_name >=", value, "gourpName");
            return (Criteria) this;
        }

        public Criteria andGourpNameLessThan(String value) {
            addCriterion("gourp_name <", value, "gourpName");
            return (Criteria) this;
        }

        public Criteria andGourpNameLessThanOrEqualTo(String value) {
            addCriterion("gourp_name <=", value, "gourpName");
            return (Criteria) this;
        }

        public Criteria andGourpNameLike(String value) {
            addCriterion("gourp_name like", value, "gourpName");
            return (Criteria) this;
        }

        public Criteria andGourpNameNotLike(String value) {
            addCriterion("gourp_name not like", value, "gourpName");
            return (Criteria) this;
        }

        public Criteria andGourpNameIn(List<String> values) {
            addCriterion("gourp_name in", values, "gourpName");
            return (Criteria) this;
        }

        public Criteria andGourpNameNotIn(List<String> values) {
            addCriterion("gourp_name not in", values, "gourpName");
            return (Criteria) this;
        }

        public Criteria andGourpNameBetween(String value1, String value2) {
            addCriterion("gourp_name between", value1, value2, "gourpName");
            return (Criteria) this;
        }

        public Criteria andGourpNameNotBetween(String value1, String value2) {
            addCriterion("gourp_name not between", value1, value2, "gourpName");
            return (Criteria) this;
        }

        public Criteria andMsgTagIsNull() {
            addCriterion("msg_tag is null");
            return (Criteria) this;
        }

        public Criteria andMsgTagIsNotNull() {
            addCriterion("msg_tag is not null");
            return (Criteria) this;
        }

        public Criteria andMsgTagEqualTo(String value) {
            addCriterion("msg_tag =", value, "msgTag");
            return (Criteria) this;
        }

        public Criteria andMsgTagNotEqualTo(String value) {
            addCriterion("msg_tag <>", value, "msgTag");
            return (Criteria) this;
        }

        public Criteria andMsgTagGreaterThan(String value) {
            addCriterion("msg_tag >", value, "msgTag");
            return (Criteria) this;
        }

        public Criteria andMsgTagGreaterThanOrEqualTo(String value) {
            addCriterion("msg_tag >=", value, "msgTag");
            return (Criteria) this;
        }

        public Criteria andMsgTagLessThan(String value) {
            addCriterion("msg_tag <", value, "msgTag");
            return (Criteria) this;
        }

        public Criteria andMsgTagLessThanOrEqualTo(String value) {
            addCriterion("msg_tag <=", value, "msgTag");
            return (Criteria) this;
        }

        public Criteria andMsgTagLike(String value) {
            addCriterion("msg_tag like", value, "msgTag");
            return (Criteria) this;
        }

        public Criteria andMsgTagNotLike(String value) {
            addCriterion("msg_tag not like", value, "msgTag");
            return (Criteria) this;
        }

        public Criteria andMsgTagIn(List<String> values) {
            addCriterion("msg_tag in", values, "msgTag");
            return (Criteria) this;
        }

        public Criteria andMsgTagNotIn(List<String> values) {
            addCriterion("msg_tag not in", values, "msgTag");
            return (Criteria) this;
        }

        public Criteria andMsgTagBetween(String value1, String value2) {
            addCriterion("msg_tag between", value1, value2, "msgTag");
            return (Criteria) this;
        }

        public Criteria andMsgTagNotBetween(String value1, String value2) {
            addCriterion("msg_tag not between", value1, value2, "msgTag");
            return (Criteria) this;
        }

        public Criteria andMsgKeysIsNull() {
            addCriterion("msg_keys is null");
            return (Criteria) this;
        }

        public Criteria andMsgKeysIsNotNull() {
            addCriterion("msg_keys is not null");
            return (Criteria) this;
        }

        public Criteria andMsgKeysEqualTo(String value) {
            addCriterion("msg_keys =", value, "msgKeys");
            return (Criteria) this;
        }

        public Criteria andMsgKeysNotEqualTo(String value) {
            addCriterion("msg_keys <>", value, "msgKeys");
            return (Criteria) this;
        }

        public Criteria andMsgKeysGreaterThan(String value) {
            addCriterion("msg_keys >", value, "msgKeys");
            return (Criteria) this;
        }

        public Criteria andMsgKeysGreaterThanOrEqualTo(String value) {
            addCriterion("msg_keys >=", value, "msgKeys");
            return (Criteria) this;
        }

        public Criteria andMsgKeysLessThan(String value) {
            addCriterion("msg_keys <", value, "msgKeys");
            return (Criteria) this;
        }

        public Criteria andMsgKeysLessThanOrEqualTo(String value) {
            addCriterion("msg_keys <=", value, "msgKeys");
            return (Criteria) this;
        }

        public Criteria andMsgKeysLike(String value) {
            addCriterion("msg_keys like", value, "msgKeys");
            return (Criteria) this;
        }

        public Criteria andMsgKeysNotLike(String value) {
            addCriterion("msg_keys not like", value, "msgKeys");
            return (Criteria) this;
        }

        public Criteria andMsgKeysIn(List<String> values) {
            addCriterion("msg_keys in", values, "msgKeys");
            return (Criteria) this;
        }

        public Criteria andMsgKeysNotIn(List<String> values) {
            addCriterion("msg_keys not in", values, "msgKeys");
            return (Criteria) this;
        }

        public Criteria andMsgKeysBetween(String value1, String value2) {
            addCriterion("msg_keys between", value1, value2, "msgKeys");
            return (Criteria) this;
        }

        public Criteria andMsgKeysNotBetween(String value1, String value2) {
            addCriterion("msg_keys not between", value1, value2, "msgKeys");
            return (Criteria) this;
        }

        public Criteria andMsgIdIsNull() {
            addCriterion("msg_id is null");
            return (Criteria) this;
        }

        public Criteria andMsgIdIsNotNull() {
            addCriterion("msg_id is not null");
            return (Criteria) this;
        }

        public Criteria andMsgIdEqualTo(String value) {
            addCriterion("msg_id =", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdNotEqualTo(String value) {
            addCriterion("msg_id <>", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdGreaterThan(String value) {
            addCriterion("msg_id >", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdGreaterThanOrEqualTo(String value) {
            addCriterion("msg_id >=", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdLessThan(String value) {
            addCriterion("msg_id <", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdLessThanOrEqualTo(String value) {
            addCriterion("msg_id <=", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdLike(String value) {
            addCriterion("msg_id like", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdNotLike(String value) {
            addCriterion("msg_id not like", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdIn(List<String> values) {
            addCriterion("msg_id in", values, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdNotIn(List<String> values) {
            addCriterion("msg_id not in", values, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdBetween(String value1, String value2) {
            addCriterion("msg_id between", value1, value2, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdNotBetween(String value1, String value2) {
            addCriterion("msg_id not between", value1, value2, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgBodyIsNull() {
            addCriterion("msg_body is null");
            return (Criteria) this;
        }

        public Criteria andMsgBodyIsNotNull() {
            addCriterion("msg_body is not null");
            return (Criteria) this;
        }

        public Criteria andMsgBodyEqualTo(String value) {
            addCriterion("msg_body =", value, "msgBody");
            return (Criteria) this;
        }

        public Criteria andMsgBodyNotEqualTo(String value) {
            addCriterion("msg_body <>", value, "msgBody");
            return (Criteria) this;
        }

        public Criteria andMsgBodyGreaterThan(String value) {
            addCriterion("msg_body >", value, "msgBody");
            return (Criteria) this;
        }

        public Criteria andMsgBodyGreaterThanOrEqualTo(String value) {
            addCriterion("msg_body >=", value, "msgBody");
            return (Criteria) this;
        }

        public Criteria andMsgBodyLessThan(String value) {
            addCriterion("msg_body <", value, "msgBody");
            return (Criteria) this;
        }

        public Criteria andMsgBodyLessThanOrEqualTo(String value) {
            addCriterion("msg_body <=", value, "msgBody");
            return (Criteria) this;
        }

        public Criteria andMsgBodyLike(String value) {
            addCriterion("msg_body like", value, "msgBody");
            return (Criteria) this;
        }

        public Criteria andMsgBodyNotLike(String value) {
            addCriterion("msg_body not like", value, "msgBody");
            return (Criteria) this;
        }

        public Criteria andMsgBodyIn(List<String> values) {
            addCriterion("msg_body in", values, "msgBody");
            return (Criteria) this;
        }

        public Criteria andMsgBodyNotIn(List<String> values) {
            addCriterion("msg_body not in", values, "msgBody");
            return (Criteria) this;
        }

        public Criteria andMsgBodyBetween(String value1, String value2) {
            addCriterion("msg_body between", value1, value2, "msgBody");
            return (Criteria) this;
        }

        public Criteria andMsgBodyNotBetween(String value1, String value2) {
            addCriterion("msg_body not between", value1, value2, "msgBody");
            return (Criteria) this;
        }

        public Criteria andConsumerStatusIsNull() {
            addCriterion("consumer_status is null");
            return (Criteria) this;
        }

        public Criteria andConsumerStatusIsNotNull() {
            addCriterion("consumer_status is not null");
            return (Criteria) this;
        }

        public Criteria andConsumerStatusEqualTo(String value) {
            addCriterion("consumer_status =", value, "consumerStatus");
            return (Criteria) this;
        }

        public Criteria andConsumerStatusNotEqualTo(String value) {
            addCriterion("consumer_status <>", value, "consumerStatus");
            return (Criteria) this;
        }

        public Criteria andConsumerStatusGreaterThan(String value) {
            addCriterion("consumer_status >", value, "consumerStatus");
            return (Criteria) this;
        }

        public Criteria andConsumerStatusGreaterThanOrEqualTo(String value) {
            addCriterion("consumer_status >=", value, "consumerStatus");
            return (Criteria) this;
        }

        public Criteria andConsumerStatusLessThan(String value) {
            addCriterion("consumer_status <", value, "consumerStatus");
            return (Criteria) this;
        }

        public Criteria andConsumerStatusLessThanOrEqualTo(String value) {
            addCriterion("consumer_status <=", value, "consumerStatus");
            return (Criteria) this;
        }

        public Criteria andConsumerStatusLike(String value) {
            addCriterion("consumer_status like", value, "consumerStatus");
            return (Criteria) this;
        }

        public Criteria andConsumerStatusNotLike(String value) {
            addCriterion("consumer_status not like", value, "consumerStatus");
            return (Criteria) this;
        }

        public Criteria andConsumerStatusIn(List<String> values) {
            addCriterion("consumer_status in", values, "consumerStatus");
            return (Criteria) this;
        }

        public Criteria andConsumerStatusNotIn(List<String> values) {
            addCriterion("consumer_status not in", values, "consumerStatus");
            return (Criteria) this;
        }

        public Criteria andConsumerStatusBetween(String value1, String value2) {
            addCriterion("consumer_status between", value1, value2, "consumerStatus");
            return (Criteria) this;
        }

        public Criteria andConsumerStatusNotBetween(String value1, String value2) {
            addCriterion("consumer_status not between", value1, value2, "consumerStatus");
            return (Criteria) this;
        }

        public Criteria andConsumerTimesIsNull() {
            addCriterion("consumer_times is null");
            return (Criteria) this;
        }

        public Criteria andConsumerTimesIsNotNull() {
            addCriterion("consumer_times is not null");
            return (Criteria) this;
        }

        public Criteria andConsumerTimesEqualTo(Integer value) {
            addCriterion("consumer_times =", value, "consumerTimes");
            return (Criteria) this;
        }

        public Criteria andConsumerTimesNotEqualTo(Integer value) {
            addCriterion("consumer_times <>", value, "consumerTimes");
            return (Criteria) this;
        }

        public Criteria andConsumerTimesGreaterThan(Integer value) {
            addCriterion("consumer_times >", value, "consumerTimes");
            return (Criteria) this;
        }

        public Criteria andConsumerTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("consumer_times >=", value, "consumerTimes");
            return (Criteria) this;
        }

        public Criteria andConsumerTimesLessThan(Integer value) {
            addCriterion("consumer_times <", value, "consumerTimes");
            return (Criteria) this;
        }

        public Criteria andConsumerTimesLessThanOrEqualTo(Integer value) {
            addCriterion("consumer_times <=", value, "consumerTimes");
            return (Criteria) this;
        }

        public Criteria andConsumerTimesIn(List<Integer> values) {
            addCriterion("consumer_times in", values, "consumerTimes");
            return (Criteria) this;
        }

        public Criteria andConsumerTimesNotIn(List<Integer> values) {
            addCriterion("consumer_times not in", values, "consumerTimes");
            return (Criteria) this;
        }

        public Criteria andConsumerTimesBetween(Integer value1, Integer value2) {
            addCriterion("consumer_times between", value1, value2, "consumerTimes");
            return (Criteria) this;
        }

        public Criteria andConsumerTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("consumer_times not between", value1, value2, "consumerTimes");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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