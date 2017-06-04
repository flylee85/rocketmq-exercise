package com.zhihao.miao.common.api;


import com.zhihao.miao.common.protocol.pay.CallbackPaymentReq;
import com.zhihao.miao.common.protocol.pay.CallbackPaymentRes;
import com.zhihao.miao.common.protocol.pay.CreatePaymentReq;
import com.zhihao.miao.common.protocol.pay.CreatePaymentRes;

public interface IPayApi {
    public CreatePaymentRes createPayment(CreatePaymentReq createPaymentReq);
    public CallbackPaymentRes callbackPayment(CallbackPaymentReq callbackPaymentReq);
}
