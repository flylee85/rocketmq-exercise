package com.zhihao.miao.common.api;

import com.zhihao.miao.common.protocol.order.ConfirmOrderReq;
import com.zhihao.miao.common.protocol.order.ConfirmOrderRes;

public interface IOrderApi {
    public ConfirmOrderRes confirmOrder(ConfirmOrderReq confirmOrderReq);
}
