package com.zhihao.miao.order.mq.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.zhihao.miao.common.constants.TradeEnums;
import com.zhihao.miao.common.protocol.mq.CancelOrderMQ;
import com.zhihao.miao.common.rocketmq.IMessageProcessor;
import com.zhihao.miao.dao.entity.TradeOrder;
import com.zhihao.miao.dao.mapper.TradeOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//支付失败的时候接收到支付模块的失败消息将支付状态改为支付失败
public class CancelOrderProcessor implements IMessageProcessor {
    public static final Logger LOGGER= LoggerFactory.getLogger(CancelOrderProcessor.class);

    @Autowired
    private TradeOrderMapper tradeOrderMapper;

    public boolean handleMessage(MessageExt messageExt) {
        try {
            String body = new String(messageExt.getBody(),"UTF-8");
            String msgId=messageExt.getMsgId();
            String tags=messageExt.getTags();
            String keys = messageExt.getKeys();
            LOGGER.info("order CancelOrderProcessor recive message:"+messageExt);

            CancelOrderMQ cancelOrderMQ = JSON.parseObject(body,CancelOrderMQ.class);

            TradeOrder record = new TradeOrder();
            record.setOrderId(cancelOrderMQ.getOrderId());
            record.setOrderStatus(TradeEnums.OrderStatusEnum.CANCEL.getStatusCode());
            tradeOrderMapper.updateByPrimaryKeySelective(record);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
