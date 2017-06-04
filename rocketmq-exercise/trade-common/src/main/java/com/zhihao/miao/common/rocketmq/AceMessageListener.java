package com.zhihao.miao.common.rocketmq;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

public class AceMessageListener  implements MessageListenerConcurrently {

    private IMessageProcessor messageProcessor;

    public void setMessageProcessor(IMessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor;
    }

    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        for (MessageExt msg : msgs) {
            boolean result = messageProcessor.handleMessage(msg);  //定义自己的对消息处理的实现类
            if(!result){
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;  //返回抱错，重试
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;  //返回成功
    }
}
