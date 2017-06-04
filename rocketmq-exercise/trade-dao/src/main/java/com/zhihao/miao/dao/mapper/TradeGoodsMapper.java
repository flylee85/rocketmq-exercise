package com.zhihao.miao.dao.mapper;


import com.zhihao.miao.dao.entity.TradeGoods;
import com.zhihao.miao.dao.entity.TradeGoodsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TradeGoodsMapper {
    int countByExample(TradeGoodsExample example);

    int deleteByExample(TradeGoodsExample example);

    int deleteByPrimaryKey(Integer goodsId);

    int insert(TradeGoods record);

    int insertSelective(TradeGoods record);

    List<TradeGoods> selectByExample(TradeGoodsExample example);

    TradeGoods selectByPrimaryKey(Integer goodsId);

    int updateByExampleSelective(@Param("record") TradeGoods record, @Param("example") TradeGoodsExample example);

    int updateByExample(@Param("record") TradeGoods record, @Param("example") TradeGoodsExample example);

    int updateByPrimaryKeySelective(TradeGoods record);

    int updateByPrimaryKey(TradeGoods record);

    int reduceGoodsNumber(TradeGoods record);
    int addGoodsNumber(TradeGoods record);

}