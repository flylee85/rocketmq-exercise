package com.zhihao.miao.user.service;

import com.zhihao.miao.common.protocol.user.ChangeUserMoneyReq;
import com.zhihao.miao.common.protocol.user.ChangeUserMoneyRes;
import com.zhihao.miao.common.protocol.user.QueryUserReq;
import com.zhihao.miao.common.protocol.user.QueryUserRes;

public interface IUserService {
    public QueryUserRes queryUserById(QueryUserReq queryUserReq);

    public ChangeUserMoneyRes changeUserMoney(ChangeUserMoneyReq changeUserMoneyReq);
}
