package com.zhihao.miao.common.protocol.user;

import java.math.BigDecimal;

//更改余额请求
public class ChangeUserMoneyReq {
    private Integer userId; //用户id
    private BigDecimal userMoney; //金额
    private String moneyLogType;//日志类型 1订单付款 2订单退款
    private String orderId; //订单id

    public String getMoneyLogType() {
        return moneyLogType;
    }

    public void setMoneyLogType(String moneyLogType) {
        this.moneyLogType = moneyLogType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(BigDecimal userMoney) {
        this.userMoney = userMoney;
    }
}
