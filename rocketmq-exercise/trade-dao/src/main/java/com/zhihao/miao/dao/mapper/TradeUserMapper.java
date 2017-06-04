package com.zhihao.miao.dao.mapper;


import com.zhihao.miao.dao.entity.TradeUser;
import com.zhihao.miao.dao.entity.TradeUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TradeUserMapper {
    int countByExample(TradeUserExample example);

    int deleteByExample(TradeUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(TradeUser record);

    int insertSelective(TradeUser record);

    List<TradeUser> selectByExample(TradeUserExample example);

    TradeUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") TradeUser record, @Param("example") TradeUserExample example);

    int updateByExample(@Param("record") TradeUser record, @Param("example") TradeUserExample example);

    int updateByPrimaryKeySelective(TradeUser record);

    int updateByPrimaryKey(TradeUser record);

    int reduceUserMoney(TradeUser record);

    int addUserMoney(TradeUser record);
}