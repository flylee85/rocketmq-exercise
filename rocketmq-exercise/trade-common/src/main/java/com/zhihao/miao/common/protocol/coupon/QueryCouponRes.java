package com.zhihao.miao.common.protocol.coupon;

import com.zhihao.miao.common.protocol.BaseRes;

import java.math.BigDecimal;
import java.util.Date;

public class QueryCouponRes extends BaseRes {
    private String couponId;//优惠券id

    private BigDecimal couponPrice;//优惠券金额

    private Integer userId; //用户ID

    private String orderId; //订单id

    private String isUsed;//是否已使用 0否 1是

    private Date usedTime; //使用时间

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
