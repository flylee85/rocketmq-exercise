package com.zhihao.miao.common.client;

import com.zhihao.miao.common.api.IUserApi;
import com.zhihao.miao.common.constants.TradeEnums;
import com.zhihao.miao.common.protocol.user.ChangeUserMoneyReq;
import com.zhihao.miao.common.protocol.user.ChangeUserMoneyRes;
import com.zhihao.miao.common.protocol.user.QueryUserReq;
import com.zhihao.miao.common.protocol.user.QueryUserRes;
import org.springframework.web.client.RestTemplate;

public class RestClient implements IUserApi {

    private static RestTemplate restTemplate=new RestTemplate();
    private IUserApi userApi;
    public static void main(String[] args) {
        QueryUserReq queryUserReq = new QueryUserReq();
        queryUserReq.setUserId(10009);
        QueryUserRes queryUserRes = restTemplate.postForObject(TradeEnums.RestServerEnum.USER.getServerUrl()+"queryUserById", queryUserReq, QueryUserRes.class);
        System.out.println(queryUserRes);

    }

    @Override
    public ChangeUserMoneyRes changeUserMoney(ChangeUserMoneyReq changeUserMoneyReq) {
        return null;
    }


    public QueryUserRes queryUserById(QueryUserReq queryUserReq) {
        return restTemplate.postForObject(TradeEnums.RestServerEnum.USER.getServerUrl()+"queryUserById", queryUserReq, QueryUserRes.class);
    }

    public static QueryUserRes queryUserById2(QueryUserReq queryUserReq) {
        return restTemplate.postForObject(TradeEnums.RestServerEnum.USER.getServerUrl()+"queryUserById2", queryUserReq, QueryUserRes.class);
    }



}
