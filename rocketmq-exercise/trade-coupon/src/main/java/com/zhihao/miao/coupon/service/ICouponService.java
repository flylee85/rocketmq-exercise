package com.zhihao.miao.coupon.service;

import com.zhihao.miao.common.protocol.coupon.ChangeCouponStatusReq;
import com.zhihao.miao.common.protocol.coupon.ChangeCouponStatusRes;
import com.zhihao.miao.common.protocol.coupon.QueryCouponReq;
import com.zhihao.miao.common.protocol.coupon.QueryCouponRes;

public interface ICouponService {
    public QueryCouponRes queryCoupon(QueryCouponReq queryCouponReq);
    public ChangeCouponStatusRes changeCouponStatus(ChangeCouponStatusReq changeCouponStatusReq);
}
