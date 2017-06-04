package com.zhihao.miao.common.rocketmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.zhihao.miao.common.exception.AceMQException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AceMQConsumer {

    public static final Logger LOGGER = LoggerFactory.getLogger(AceMQConsumer.class);
    private String groupName;
    private String topic;
    private String tag="*";//多个tag以||分割
    private String namesrvAddr;
    /**
     * Minimum consumer thread number
     */
    private int consumeThreadMin = 20;
    /**
     * Max consumer thread number
     */
    private int consumeThreadMax = 64;

    private IMessageProcessor processor;

    public void setConsumeThreadMax(int consumeThreadMax) {
        this.consumeThreadMax = consumeThreadMax;
    }

    public void setConsumeThreadMin(int consumeThreadMin) {
        this.consumeThreadMin = consumeThreadMin;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public void setProcessor(IMessageProcessor processor) {
        this.processor = processor;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void init() throws AceMQException {

        if(StringUtils.isBlank(groupName)){
            throw new AceMQException("groupName is blank!");
        }
        if(StringUtils.isBlank(topic)){
            throw new AceMQException("topic is blank!");
        }
        if(StringUtils.isBlank(namesrvAddr)){
            throw new AceMQException("namesrvAddr is blank!");
        }
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(this.groupName);
        consumer.setNamesrvAddr(this.namesrvAddr);

        try {
            consumer.subscribe(this.topic, this.tag);
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET); //从头消费还是从尾消费
            consumer.setConsumeThreadMin(this.consumeThreadMin);  //最小线程
            consumer.setConsumeThreadMax(this.consumeThreadMax); //最大线程
            AceMessageListener aceMessageListener = new AceMessageListener();
            aceMessageListener.setMessageProcessor(this.processor);
            consumer.registerMessageListener(aceMessageListener);
            consumer.start();
            LOGGER.info("consumer is start!groupName:{},topic:{},namesrvAddr:{}",groupName,topic, namesrvAddr);
        } catch (MQClientException e) {
            LOGGER.error("consumer is error!groupName:{},topic:{},namesrvAddr:{}",groupName,topic, namesrvAddr,e);
            throw new AceMQException(e);
        }



    }
}
