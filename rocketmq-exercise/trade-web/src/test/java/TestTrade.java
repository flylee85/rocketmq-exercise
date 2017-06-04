import com.alibaba.fastjson.JSON;
import com.zhihao.miao.common.api.*;
import com.zhihao.miao.common.constants.TradeEnums;
import com.zhihao.miao.common.protocol.coupon.QueryCouponReq;
import com.zhihao.miao.common.protocol.coupon.QueryCouponRes;
import com.zhihao.miao.common.protocol.goods.QueryGoodsReq;
import com.zhihao.miao.common.protocol.goods.QueryGoodsRes;
import com.zhihao.miao.common.protocol.order.ConfirmOrderReq;
import com.zhihao.miao.common.protocol.order.ConfirmOrderRes;
import com.zhihao.miao.common.protocol.pay.CallbackPaymentReq;
import com.zhihao.miao.common.protocol.pay.CallbackPaymentRes;
import com.zhihao.miao.common.protocol.pay.CreatePaymentReq;
import com.zhihao.miao.common.protocol.pay.CreatePaymentRes;
import com.zhihao.miao.common.protocol.user.QueryUserReq;
import com.zhihao.miao.common.protocol.user.QueryUserRes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@ContextConfiguration(locations = {"classpath:spring-rest-client.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestTrade {

    @Autowired
    private IGoodsApi goodsApi;

    @Autowired
    private ICouponApi couponApi;

    @Autowired
    private IUserApi userApi;

    @Autowired
    private IOrderApi orderApi;

    @Autowired
    private IPayApi payApi;


    @Test
    public void testUser() {
        QueryUserReq queryUserReq = new QueryUserReq();
        queryUserReq.setUserId(1);
        QueryUserRes queryUserRes = userApi.queryUserById(queryUserReq);
        System.out.println(JSON.toJSONString(queryUserRes));
    }

    @Test
    public void testGoods() {
        QueryGoodsReq queryGoodsReq = new QueryGoodsReq();
        queryGoodsReq.setGoodsId(10000);
        QueryGoodsRes queryGoodsRes = goodsApi.queryGoods(queryGoodsReq);
        System.out.println(JSON.toJSONString(queryGoodsRes));
    }

    @Test
    public void testCoupon() {
        QueryCouponReq queryCouponReq = new QueryCouponReq();
        queryCouponReq.setCouponId("123456789");
        QueryCouponRes queryCouponRes = couponApi.queryCoupon(queryCouponReq);
        System.out.println(JSON.toJSONString(queryCouponRes));
    }


    @Test
    public void testConfirmOrder() {
        ConfirmOrderReq confirmOrderReq = new ConfirmOrderReq();
        confirmOrderReq.setGoodsId(10000);
        confirmOrderReq.setUserId(1);
        confirmOrderReq.setGoodsNumber(1);
        confirmOrderReq.setAddress("北京");
        confirmOrderReq.setGoodsPrice(new BigDecimal("5000"));
        confirmOrderReq.setOrderAmount(new BigDecimal("5000"));
        confirmOrderReq.setMoneyPaid(new BigDecimal("100"));
        confirmOrderReq.setCouponId("123456789");
        ConfirmOrderRes confirmOrderRes = orderApi.confirmOrder(confirmOrderReq);
        System.out.println(JSON.toJSONString(confirmOrderRes));
    }


    @Test
    public void testCreatePayment() {
        CreatePaymentReq createPaymentReq = new CreatePaymentReq();
        createPaymentReq.setPayAmount(new BigDecimal("4800"));
        createPaymentReq.setOrderId("0761288529c242cd8380eb3bad292bb9");
        CreatePaymentRes createPaymentRes = payApi.createPayment(createPaymentReq);
        System.out.println(JSON.toJSONString(createPaymentRes));
    }

    @Test
    public void testCallbackPayment() {
        CallbackPaymentReq callbackPaymentReq = new CallbackPaymentReq();
        callbackPaymentReq.setPayId("1138b1b2767441a0bfb28696c7c663d4");
        callbackPaymentReq.setIsPaid(TradeEnums.YesNoEnum.YES.getCode());
        CallbackPaymentRes callbackPaymentRes = payApi.callbackPayment(callbackPaymentReq);
        System.out.println(JSON.toJSONString(callbackPaymentRes));
    }

}
