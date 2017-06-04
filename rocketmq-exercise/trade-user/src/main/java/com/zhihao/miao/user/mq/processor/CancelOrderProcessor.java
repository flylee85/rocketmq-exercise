package com.zhihao.miao.user.mq.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.zhihao.miao.common.constants.TradeEnums;
import com.zhihao.miao.common.protocol.mq.CancelOrderMQ;
import com.zhihao.miao.common.protocol.user.ChangeUserMoneyReq;
import com.zhihao.miao.common.rocketmq.IMessageProcessor;
import com.zhihao.miao.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * order模块在确定订单报错的时候发送消息给user模块
 */
public class CancelOrderProcessor implements IMessageProcessor {
    public static final Logger LOGGER= LoggerFactory.getLogger(CancelOrderProcessor.class);

    @Autowired
    private IUserService userService;
    public boolean handleMessage(MessageExt messageExt) {
        try {
            String body = new String(messageExt.getBody(),"UTF-8");
            String msgId=messageExt.getMsgId();
            String tags=messageExt.getTags();
            String keys = messageExt.getKeys();
            LOGGER.info("user CancelOrderProcessor recive message:"+messageExt);

            CancelOrderMQ cancelOrderMQ = JSON.parseObject(body,CancelOrderMQ.class);
            if (cancelOrderMQ.getUserMoney() != null && cancelOrderMQ.getUserMoney().compareTo(BigDecimal.ZERO) == 1) {

                ChangeUserMoneyReq changeUserMoneyReq = new ChangeUserMoneyReq();
                changeUserMoneyReq.setUserId(cancelOrderMQ.getUserId());
                changeUserMoneyReq.setMoneyLogType(TradeEnums.UserMoneyLogTypeEnum.REFUND.getCode());
                changeUserMoneyReq.setOrderId(cancelOrderMQ.getOrderId());
                changeUserMoneyReq.setUserMoney(cancelOrderMQ.getUserMoney());
                userService.changeUserMoney(changeUserMoneyReq);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
