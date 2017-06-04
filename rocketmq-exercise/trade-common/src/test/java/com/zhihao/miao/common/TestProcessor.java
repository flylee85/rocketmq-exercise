package com.zhihao.miao.common;


import com.alibaba.rocketmq.common.message.MessageExt;
import com.zhihao.miao.common.rocketmq.IMessageProcessor;

public class TestProcessor  implements IMessageProcessor {

    @Override
    public boolean handleMessage(MessageExt messageExt) {
        System.out.println("收到消息："+messageExt.toString());
        return true;
    }
}
