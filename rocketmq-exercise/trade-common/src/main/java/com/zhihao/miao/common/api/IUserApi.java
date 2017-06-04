package com.zhihao.miao.common.api;


import com.zhihao.miao.common.protocol.user.ChangeUserMoneyReq;
import com.zhihao.miao.common.protocol.user.ChangeUserMoneyRes;
import com.zhihao.miao.common.protocol.user.QueryUserReq;
import com.zhihao.miao.common.protocol.user.QueryUserRes;

public interface IUserApi {
    public QueryUserRes queryUserById(QueryUserReq queryUserReq);

    //改变余额的接口
    public ChangeUserMoneyRes changeUserMoney(ChangeUserMoneyReq changeUserMoneyReq);
}
