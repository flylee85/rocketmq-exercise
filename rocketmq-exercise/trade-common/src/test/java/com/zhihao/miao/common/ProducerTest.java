package com.zhihao.miao.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.zhihao.miao.common.exception.AceMQException;
import com.zhihao.miao.common.rocketmq.AceMQProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rocketmq-producer.xml")
public class ProducerTest {
    @Autowired
    private AceMQProducer aceMQProducer;

    @Test
    public void testProducer() throws AceMQException {
        SendResult sendResult = this.aceMQProducer.sendMessage("TestTopic", "order", "12345678", "this is order message");
        System.out.println(sendResult);
    }


}
