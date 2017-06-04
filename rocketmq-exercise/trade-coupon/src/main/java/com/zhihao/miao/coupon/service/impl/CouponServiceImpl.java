package com.zhihao.miao.coupon.service.impl;


import com.zhihao.miao.common.constants.TradeEnums;
import com.zhihao.miao.common.protocol.coupon.ChangeCouponStatusReq;
import com.zhihao.miao.common.protocol.coupon.ChangeCouponStatusRes;
import com.zhihao.miao.common.protocol.coupon.QueryCouponReq;
import com.zhihao.miao.common.protocol.coupon.QueryCouponRes;
import com.zhihao.miao.coupon.service.ICouponService;
import com.zhihao.miao.dao.entity.TradeCoupon;
import com.zhihao.miao.dao.mapper.TradeCouponMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponServiceImpl implements ICouponService {
    @Autowired
    private TradeCouponMapper tradeCouponMapper;

    public QueryCouponRes queryCoupon(QueryCouponReq queryCouponReq) {
        QueryCouponRes queryCouponRes = new QueryCouponRes();
        queryCouponRes.setRetCode(TradeEnums.RetEnum.SUCCESS.getCode());
        queryCouponRes.setRetInfo(TradeEnums.RetEnum.SUCCESS.getDesc());

        try{
            if(queryCouponReq==null || StringUtils.isBlank(queryCouponReq.getCouponId())){
                throw new Exception("请求参数不正确，优惠券编号为空");
            }

            //查询优惠券信息
            TradeCoupon tradeCoupon = this.tradeCouponMapper.selectByPrimaryKey(queryCouponReq.getCouponId());
            if (tradeCoupon != null) {
                BeanUtils.copyProperties(tradeCoupon,queryCouponRes);
            }else{
                throw new Exception("未查询到该优惠券");
            }
        }catch(Exception ex){
            queryCouponRes.setRetCode(TradeEnums.RetEnum.FAIL.getCode());
            queryCouponRes.setRetInfo(ex.getMessage());
        }

        return queryCouponRes;
    }

    //改变优惠券状态
    public ChangeCouponStatusRes changeCouponStatus(ChangeCouponStatusReq changeCouponStatusReq) {
        ChangeCouponStatusRes changeCouponStatusRes = new ChangeCouponStatusRes();
        changeCouponStatusRes.setRetCode(TradeEnums.RetEnum.SUCCESS.getCode());
        changeCouponStatusRes.setRetInfo(TradeEnums.RetEnum.SUCCESS.getDesc());
        try{
            if (changeCouponStatusReq == null || StringUtils.isBlank(changeCouponStatusReq.getCouponId())) {
                throw new Exception("请求参数不正确，优惠券编号为空");
            }
            //使用优惠券
            TradeCoupon record = new TradeCoupon();
            record.setCouponId(changeCouponStatusReq.getCouponId());
            record.setOrderId(changeCouponStatusReq.getOrderId());
            if (changeCouponStatusReq.getIsUsed().equals(TradeEnums.YesNoEnum.YES.getCode())) {
                int i = this.tradeCouponMapper.useCoupon(record); //使用这一步的sql防止并发使用这张优惠券
                if (i <= 0) {
                    throw new Exception("使用该优惠券失败");
                }
            } else if (changeCouponStatusReq.getIsUsed().equals(TradeEnums.YesNoEnum.NO.getCode())) {
                this.tradeCouponMapper.unUseCoupon(record);
            }

        }catch(Exception ex){
            changeCouponStatusRes.setRetCode(TradeEnums.RetEnum.FAIL.getCode());
            changeCouponStatusRes.setRetInfo(ex.getMessage());
        }
        return changeCouponStatusRes;
    }
}
