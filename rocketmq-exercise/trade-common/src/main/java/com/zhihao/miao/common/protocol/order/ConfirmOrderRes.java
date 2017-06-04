package com.zhihao.miao.common.protocol.order;

import com.zhihao.miao.common.protocol.BaseRes;

public class ConfirmOrderRes extends BaseRes {
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
