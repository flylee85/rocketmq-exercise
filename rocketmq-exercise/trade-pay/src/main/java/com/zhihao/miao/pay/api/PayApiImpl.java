package com.zhihao.miao.pay.api;


import com.zhihao.miao.common.api.IPayApi;
import com.zhihao.miao.common.constants.TradeEnums;
import com.zhihao.miao.common.protocol.pay.CallbackPaymentReq;
import com.zhihao.miao.common.protocol.pay.CallbackPaymentRes;
import com.zhihao.miao.common.protocol.pay.CreatePaymentReq;
import com.zhihao.miao.common.protocol.pay.CreatePaymentRes;
import com.zhihao.miao.pay.service.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PayApiImpl implements IPayApi {

    @Autowired
    private IPayService payService;

    @RequestMapping(value = "/createPayment", method = RequestMethod.POST)
    @ResponseBody
    public CreatePaymentRes createPayment(@RequestBody CreatePaymentReq createPaymentReq) {
        return payService.createPayment(createPaymentReq);
    }

    @RequestMapping(value = "/callbackPayment", method = RequestMethod.POST)
    @ResponseBody
    public CallbackPaymentRes callbackPayment(@RequestBody CallbackPaymentReq callbackPaymentReq) {
        CallbackPaymentRes callbackPaymentRes = new CallbackPaymentRes();
        try {
            callbackPaymentRes = payService.callbackPayment(callbackPaymentReq);
        } catch (Exception ex) {
            callbackPaymentRes.setRetCode(TradeEnums.RetEnum.FAIL.getCode());
            callbackPaymentRes.setRetInfo(ex.getMessage());
        }
        return callbackPaymentRes;
    }
}
