package com.zhihao.miao.common;

import com.zhihao.miao.common.exception.AceMQException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rocketmq-consumer.xml")
public class ConsumerTest {

    @Test
    public void testConsumer() throws AceMQException, InterruptedException {
        Thread.sleep(1000000L);  //消费者因为是监听器不同的监听，spring容器一直启动即可
    }
}
