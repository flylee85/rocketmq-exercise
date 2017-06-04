package com.zhihao.miao.coupon.mq.processor;


import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.zhihao.miao.common.constants.TradeEnums;
import com.zhihao.miao.common.protocol.coupon.ChangeCouponStatusReq;
import com.zhihao.miao.common.protocol.coupon.ChangeCouponStatusRes;
import com.zhihao.miao.common.protocol.mq.CancelOrderMQ;
import com.zhihao.miao.common.rocketmq.IMessageProcessor;
import com.zhihao.miao.coupon.service.ICouponService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CancelOrderProcessor implements IMessageProcessor {
    public static final Logger LOGGER= LoggerFactory.getLogger(CancelOrderProcessor.class);
    @Autowired
    private ICouponService couponService;
    public boolean handleMessage(MessageExt messageExt) {
        try {
            String body = new String(messageExt.getBody(),"UTF-8");
            String msgId=messageExt.getMsgId();
            String tags=messageExt.getTags();
            String keys = messageExt.getKeys();
            LOGGER.info("coupon CancelOrderProcessor recive message:"+messageExt);

            CancelOrderMQ cancelOrderMQ = JSON.parseObject(body,CancelOrderMQ.class);
            if (StringUtils.isNotBlank(cancelOrderMQ.getCouponId())) {
                ChangeCouponStatusReq changeCouponStatusReq = new ChangeCouponStatusReq();
                changeCouponStatusReq.setOrderId(cancelOrderMQ.getOrderId());
                changeCouponStatusReq.setCouponId(cancelOrderMQ.getCouponId());
                changeCouponStatusReq.setIsUsed(TradeEnums.YesNoEnum.NO.getCode());
                ChangeCouponStatusRes changeCouponStatusRes = this.couponService.changeCouponStatus(changeCouponStatusReq);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
