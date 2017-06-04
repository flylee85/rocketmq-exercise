package com.zhihao.miao.order.api;


import com.zhihao.miao.common.api.IOrderApi;
import com.zhihao.miao.common.protocol.order.ConfirmOrderReq;
import com.zhihao.miao.common.protocol.order.ConfirmOrderRes;
import com.zhihao.miao.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderApiImpl implements IOrderApi {
    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "/confirmOrder",method = RequestMethod.POST)
    @ResponseBody
    public ConfirmOrderRes confirmOrder(@RequestBody ConfirmOrderReq confirmOrderReq) {
        //
        ConfirmOrderRes confirmOrderRes = this.orderService.confirmOrder(confirmOrderReq);
        return confirmOrderRes;
    }
}
