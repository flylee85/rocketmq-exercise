package com.zhihao.miao.order.mq.processor;


import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.zhihao.miao.common.constants.TradeEnums;
import com.zhihao.miao.common.protocol.mq.PaidMQ;
import com.zhihao.miao.common.rocketmq.IMessageProcessor;
import com.zhihao.miao.dao.entity.TradeOrder;
import com.zhihao.miao.dao.mapper.TradeOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


//接受支付模块中确定支付成功之后将支付状态改为支付成功
public class PaidProcessor implements IMessageProcessor {
    public static final Logger LOGGER= LoggerFactory.getLogger(PaidProcessor.class);
    @Autowired
    private TradeOrderMapper tradeOrderMapper;

    public boolean handleMessage(MessageExt messageExt) {
        try {
            String body = new String(messageExt.getBody(),"UTF-8");
            String msgId=messageExt.getMsgId();
            String tags=messageExt.getTags();
            String keys = messageExt.getKeys();
            LOGGER.info("order CancelOrderProcessor recive message:"+messageExt);

            PaidMQ paidMQ = JSON.parseObject(body,PaidMQ.class);

            TradeOrder record = new TradeOrder();
            record.setOrderId(paidMQ.getOrderId());
            record.setPayStatus(TradeEnums.PayStatusEnum.PAID.getStatusCode());
            tradeOrderMapper.updateByPrimaryKeySelective(record);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
