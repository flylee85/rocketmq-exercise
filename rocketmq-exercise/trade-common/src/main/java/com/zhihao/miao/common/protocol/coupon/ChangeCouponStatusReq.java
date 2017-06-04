package com.zhihao.miao.common.protocol.coupon;

//改变优惠券状态
public class ChangeCouponStatusReq {
    private String couponId; //优惠券
    private String orderId; //是否被使用了
    private String isUsed; //是否被使用

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
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
}
