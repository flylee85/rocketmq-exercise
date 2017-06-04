package com.zhihao.miao.goods.mq.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.common.message.MessageClientExt;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.zhihao.miao.common.constants.MQEnums;
import com.zhihao.miao.common.protocol.goods.AddGoodsNumberReq;
import com.zhihao.miao.common.protocol.mq.CancelOrderMQ;
import com.zhihao.miao.common.rocketmq.IMessageProcessor;
import com.zhihao.miao.dao.entity.TradeMqConsumerLog;
import com.zhihao.miao.dao.entity.TradeMqConsumerLogExample;
import com.zhihao.miao.dao.entity.TradeMqConsumerLogKey;
import com.zhihao.miao.dao.mapper.TradeMqConsumerLogMapper;
import com.zhihao.miao.goods.service.IGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class CancelOrderProcessor implements IMessageProcessor {
    public static final Logger LOGGER= LoggerFactory.getLogger(CancelOrderProcessor.class);
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private TradeMqConsumerLogMapper tradeMqConsumerLogMapper;

    public boolean handleMessage(MessageExt messageExt) {
        TradeMqConsumerLog mqConsumerLog=null;
        try {
            String groupName="goods_orderTopic_cancel_group";
            String body = new String(messageExt.getBody(),"UTF-8");
            String msgId=messageExt.getMsgId();
            //rocketmq3.5.8,需要使用此形式获取的msgId，可以在监控台查询
            if (messageExt instanceof MessageClientExt) {
                msgId = ((MessageClientExt) messageExt).getOffsetMsgId();
            }
            String tags=messageExt.getTags();
            String keys = messageExt.getKeys();
            LOGGER.info("goods CancelOrderProcessor recive message:"+messageExt);


            TradeMqConsumerLogKey key = new TradeMqConsumerLogKey();
            key.setGourpName(groupName);
            key.setMsgTag(tags);
            key.setMsgKeys(keys);

            //先去数据中去查询有没有消费日志
            mqConsumerLog = this.tradeMqConsumerLogMapper.selectByPrimaryKey(key);
            if (mqConsumerLog != null) {
                //查询该条消息的状态,如果是已经处理过了则直接返回成功，如果返回正在处理，表示现在有另外的线程在处理这条消息，
                String consumerStatus = mqConsumerLog.getConsumerStatus();
                if (MQEnums.ConsumerStatusEnum.SUCCESS.getStatusCode().equals(consumerStatus)) {
                    //返回成功，重复的处理消息
                    LOGGER.warn("已经处理过，不用再处理了");
                    return true;
                }else if (MQEnums.ConsumerStatusEnum.PROCESSING.getStatusCode().equals(consumerStatus)) {
                    //返回失败，说明有消费者正在处理当中，稍后再试
                    LOGGER.warn("正在处理，稍后再试");
                    return false;
                }else if (MQEnums.ConsumerStatusEnum.FAIL.getStatusCode().equals(consumerStatus)) {
                    if (mqConsumerLog.getConsumerTimes() >= 3) {
                        //让这个消息不再重试，转人工处理
                        LOGGER.warn("超过3次，不再处理");
                        return true;
                    }
                    //更新处理中状态
                    TradeMqConsumerLog updateMqConsumerLog = new TradeMqConsumerLog();
                    updateMqConsumerLog.setGourpName(mqConsumerLog.getGourpName());
                    updateMqConsumerLog.setMsgTag(mqConsumerLog.getMsgTag());
                    updateMqConsumerLog.setMsgKeys(mqConsumerLog.getMsgKeys());
                    updateMqConsumerLog.setConsumerStatus(MQEnums.ConsumerStatusEnum.PROCESSING.getStatusCode());
                    //防止并发
                    TradeMqConsumerLogExample example = new TradeMqConsumerLogExample();
                    example.createCriteria().andGourpNameEqualTo(mqConsumerLog.getGourpName())
                            .andMsgKeysEqualTo(mqConsumerLog.getMsgKeys())
                            .andMsgTagEqualTo(mqConsumerLog.getMsgTag())
                            .andConsumerTimesEqualTo(mqConsumerLog.getConsumerTimes());
                    //乐观锁的方式防止并发更新
                    int i = this.tradeMqConsumerLogMapper.updateByExampleSelective(updateMqConsumerLog, example);
                    if (i <= 0) {
                        LOGGER.warn("并发更新处理状态，一会儿重试");
                        return false;
                    }
                }
            }else{
                //新插入去重表，并发时用主键冲突控制
                try{
                    mqConsumerLog = new TradeMqConsumerLog();
                    mqConsumerLog.setGourpName(groupName);
                    mqConsumerLog.setMsgKeys(keys);
                    mqConsumerLog.setMsgTag(tags);
                    mqConsumerLog.setMsgId(msgId);
                    mqConsumerLog.setConsumerTimes(0);
                    mqConsumerLog.setMsgBody(body);
                    mqConsumerLog.setConsumerStatus(MQEnums.ConsumerStatusEnum.PROCESSING.getStatusCode());
                    this.tradeMqConsumerLogMapper.insertSelective(mqConsumerLog);
                }catch(Exception ex){
                    LOGGER.warn("主键冲突，说明有订阅正正在处理，稍后再试");
                    return false;
                }
            }

            //业务逻辑处理
            CancelOrderMQ cancelOrderMQ = JSON.parseObject(body, CancelOrderMQ.class);
            AddGoodsNumberReq addGoodsNumberReq = new AddGoodsNumberReq();
            addGoodsNumberReq.setGoodsId(cancelOrderMQ.getGoodsId());
            addGoodsNumberReq.setGoodsNumber(cancelOrderMQ.getGoodsNumber());
            addGoodsNumberReq.setOrderId(cancelOrderMQ.getOrderId());
            goodsService.addGoodsNumber(addGoodsNumberReq);

            //更新消息状态为处理成功
            TradeMqConsumerLog updateMqConsumerLog = new TradeMqConsumerLog();
            updateMqConsumerLog.setGourpName(mqConsumerLog.getGourpName());
            updateMqConsumerLog.setMsgTag(mqConsumerLog.getMsgTag());
            updateMqConsumerLog.setMsgKeys(mqConsumerLog.getMsgKeys());
            updateMqConsumerLog.setConsumerStatus(MQEnums.ConsumerStatusEnum.SUCCESS.getStatusCode());
            updateMqConsumerLog.setConsumerTimes(mqConsumerLog.getConsumerTimes()+1); //处理次数+1
            this.tradeMqConsumerLogMapper.updateByPrimaryKeySelective(updateMqConsumerLog);

            return true;
        } catch (Exception e) {
            //更新消息状态为处理失败
            TradeMqConsumerLog updateMqConsumerLog = new TradeMqConsumerLog();
            updateMqConsumerLog.setGourpName(mqConsumerLog.getGourpName());
            updateMqConsumerLog.setMsgTag(mqConsumerLog.getMsgTag());
            updateMqConsumerLog.setMsgKeys(mqConsumerLog.getMsgKeys());
            updateMqConsumerLog.setConsumerStatus(MQEnums.ConsumerStatusEnum.FAIL.getStatusCode());
            updateMqConsumerLog.setConsumerTimes(mqConsumerLog.getConsumerTimes()+1);
            this.tradeMqConsumerLogMapper.updateByPrimaryKeySelective(updateMqConsumerLog);
            return false;
        }
    }
}
