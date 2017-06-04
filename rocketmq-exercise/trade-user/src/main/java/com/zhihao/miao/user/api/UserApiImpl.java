package com.zhihao.miao.user.api;

import com.zhihao.miao.common.api.IUserApi;
import com.zhihao.miao.common.constants.TradeEnums;
import com.zhihao.miao.common.protocol.user.ChangeUserMoneyReq;
import com.zhihao.miao.common.protocol.user.ChangeUserMoneyRes;
import com.zhihao.miao.common.protocol.user.QueryUserReq;
import com.zhihao.miao.common.protocol.user.QueryUserRes;
import com.zhihao.miao.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserApiImpl implements IUserApi {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/queryUserById" ,method = RequestMethod.POST)
    @ResponseBody
    public QueryUserRes queryUserById(@RequestBody QueryUserReq queryUserReq) {
        return this.userService.queryUserById(queryUserReq);
    }

    @RequestMapping(value = "/changeUserMoney" ,method = RequestMethod.POST)
    @ResponseBody
    public ChangeUserMoneyRes changeUserMoney(@RequestBody ChangeUserMoneyReq changeUserMoneyReq) {
        ChangeUserMoneyRes changeUserMoneyRes = new ChangeUserMoneyRes();
        try {
            changeUserMoneyRes = this.userService.changeUserMoney(changeUserMoneyReq);

        } catch (Exception ex) {
            changeUserMoneyRes.setRetCode(TradeEnums.RetEnum.FAIL.getCode());
            changeUserMoneyRes.setRetInfo(ex.getMessage());
        }
        return changeUserMoneyRes;
    }
}
