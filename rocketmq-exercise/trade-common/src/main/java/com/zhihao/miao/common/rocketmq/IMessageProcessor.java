package com.zhihao.miao.common.rocketmq;

import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * 自己定义的一个接口专门处理消息，返回false业务代码稍后重试代码，返回true，表示发送成功
 */
public interface IMessageProcessor {
    /**
     * 处理消息
     * @param messageExt
     * @return
     */
    public boolean handleMessage(MessageExt messageExt);
}
