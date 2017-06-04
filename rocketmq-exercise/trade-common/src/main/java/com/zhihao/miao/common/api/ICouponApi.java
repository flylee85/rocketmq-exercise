package com.zhihao.miao.common.api;

import com.zhihao.miao.common.protocol.coupon.ChangeCouponStatusReq;
import com.zhihao.miao.common.protocol.coupon.ChangeCouponStatusRes;
import com.zhihao.miao.common.protocol.coupon.QueryCouponReq;
import com.zhihao.miao.common.protocol.coupon.QueryCouponRes;
import com.zhihao.miao.common.protocol.user.QueryUserReq;

/**
 * 优惠券接口
 */
public interface ICouponApi {
    public QueryCouponRes queryCoupon(QueryCouponReq queryCouponReq);
    //改变优惠券状态
    public ChangeCouponStatusRes changeCouponStatus(ChangeCouponStatusReq changeCouponStatusReq);
}
