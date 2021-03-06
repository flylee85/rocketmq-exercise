package com.zhihao.miao.dao;

import com.zhihao.miao.dao.entity.TradeUser;
import com.zhihao.miao.dao.mapper.TradeUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-dao.xml")
public class TestDao {

    @Autowired
    private TradeUserMapper tradeUserMapper;

    @Test
    public void test() {
        TradeUser record = new TradeUser();
        record.setUserName("zhangsan");
        record.setUserPassword("123456");
        int i = tradeUserMapper.insert(record);
        System.out.println("i="+i);
        System.out.println("id="+record.getUserId());
    }
}
