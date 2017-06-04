package com.zhihao.miao.goods.service;


import com.zhihao.miao.common.protocol.goods.*;

public interface IGoodsService {

    public QueryGoodsRes queryGoods(QueryGoodsReq queryGoodsReq);

    public ReduceGoodsNumberRes reduceGoodsNumber(ReduceGoodsNumberReq reduceGoodsNumberReq);

    public AddGoodsNumberRes addGoodsNumber(AddGoodsNumberReq addGoodsNumberReq);
}
