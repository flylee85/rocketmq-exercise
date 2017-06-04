package com.zhihao.miao.order.service;


import com.zhihao.miao.common.protocol.order.ConfirmOrderReq;
import com.zhihao.miao.common.protocol.order.ConfirmOrderRes;

//确定订单接口
public interface IOrderService {
    public ConfirmOrderRes confirmOrder(ConfirmOrderReq confirmOrderReq);
}
