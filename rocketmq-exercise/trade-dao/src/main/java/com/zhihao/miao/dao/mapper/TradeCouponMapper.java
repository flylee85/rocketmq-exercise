package com.zhihao.miao.dao.mapper;



import com.zhihao.miao.dao.entity.TradeCoupon;
import com.zhihao.miao.dao.entity.TradeCouponExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TradeCouponMapper {
    int countByExample(TradeCouponExample example);

    int deleteByExample(TradeCouponExample example);

    int deleteByPrimaryKey(String couponId);

    int insert(TradeCoupon record);

    int insertSelective(TradeCoupon record);

    List<TradeCoupon> selectByExample(TradeCouponExample example);

    TradeCoupon selectByPrimaryKey(String couponId);

    int updateByExampleSelective(@Param("record") TradeCoupon record, @Param("example") TradeCouponExample example);

    int updateByExample(@Param("record") TradeCoupon record, @Param("example") TradeCouponExample example);

    int updateByPrimaryKeySelective(TradeCoupon record);

    int updateByPrimaryKey(TradeCoupon record);

    int useCoupon(TradeCoupon record);

    int unUseCoupon(TradeCoupon record);
}