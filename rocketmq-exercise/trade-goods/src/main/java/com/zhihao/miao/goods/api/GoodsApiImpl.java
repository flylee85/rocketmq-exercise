package com.zhihao.miao.goods.api;


import com.zhihao.miao.common.api.IGoodsApi;
import com.zhihao.miao.common.constants.TradeEnums;
import com.zhihao.miao.common.protocol.goods.QueryGoodsReq;
import com.zhihao.miao.common.protocol.goods.QueryGoodsRes;
import com.zhihao.miao.common.protocol.goods.ReduceGoodsNumberReq;
import com.zhihao.miao.common.protocol.goods.ReduceGoodsNumberRes;
import com.zhihao.miao.goods.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GoodsApiImpl implements IGoodsApi {

    @Autowired
    private IGoodsService goodsService;

    @RequestMapping(value = "/queryGoods" ,method = RequestMethod.POST)
    @ResponseBody
    public QueryGoodsRes queryGoods(@RequestBody QueryGoodsReq queryGoodsReq) {
        return this.goodsService.queryGoods(queryGoodsReq);
    }


    @RequestMapping(value = "/reduceGoodsNumber" ,method = RequestMethod.POST)
    @ResponseBody
    public ReduceGoodsNumberRes reduceGoodsNumber(@RequestBody ReduceGoodsNumberReq reduceGoodsNumberReq) {
        ReduceGoodsNumberRes reduceGoodsNumberRes = new ReduceGoodsNumberRes();
        try{
            reduceGoodsNumberRes = goodsService.reduceGoodsNumber(reduceGoodsNumberReq);
        }catch(Exception ex){
            reduceGoodsNumberRes.setRetCode(TradeEnums.RetEnum.FAIL.getCode());
            reduceGoodsNumberRes.setRetInfo(ex.getMessage());
        }
        return reduceGoodsNumberRes;
    }
}
