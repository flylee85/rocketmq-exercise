package com.zhihao.miao.common.api;

import com.zhihao.miao.common.protocol.goods.QueryGoodsReq;
import com.zhihao.miao.common.protocol.goods.QueryGoodsRes;
import com.zhihao.miao.common.protocol.goods.ReduceGoodsNumberReq;
import com.zhihao.miao.common.protocol.goods.ReduceGoodsNumberRes;

//库存服务
public interface IGoodsApi {
    public QueryGoodsRes queryGoods(QueryGoodsReq queryGoodsReq); //查询库存

    public ReduceGoodsNumberRes reduceGoodsNumber(ReduceGoodsNumberReq reduceGoodsNumberReq); //减库存
}
