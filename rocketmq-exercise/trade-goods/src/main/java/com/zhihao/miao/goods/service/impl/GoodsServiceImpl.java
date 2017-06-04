package com.zhihao.miao.goods.service.impl;

import com.zhihao.miao.common.constants.TradeEnums;
import com.zhihao.miao.common.protocol.goods.*;
import com.zhihao.miao.dao.entity.TradeGoods;
import com.zhihao.miao.dao.entity.TradeGoodsNumberLog;
import com.zhihao.miao.dao.entity.TradeGoodsNumberLogKey;
import com.zhihao.miao.dao.mapper.TradeGoodsMapper;
import com.zhihao.miao.dao.mapper.TradeGoodsNumberLogMapper;
import com.zhihao.miao.goods.service.IGoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private TradeGoodsMapper tradeGoodsMapper;
    @Autowired
    private TradeGoodsNumberLogMapper tradeGoodsNumberLogMapper;


    //根据商品id查询商品信息
    public QueryGoodsRes queryGoods(QueryGoodsReq queryGoodsReq) {
        QueryGoodsRes queryGoodsRes = new QueryGoodsRes();
        queryGoodsRes.setRetCode(TradeEnums.RetEnum.SUCCESS.getCode());
        queryGoodsRes.setRetInfo(TradeEnums.RetEnum.SUCCESS.getDesc());
        try {
            if (queryGoodsReq == null || queryGoodsReq.getGoodsId() == null) {
                throw new Exception("查询商品信息ID不正确");
            }
            //查询商品信息
            TradeGoods tradeGoods = this.tradeGoodsMapper.selectByPrimaryKey(queryGoodsReq.getGoodsId());

            if (tradeGoods != null) {
                //使用spring的BeanUtils包进行映射
                BeanUtils.copyProperties(tradeGoods, queryGoodsRes);
            } else {
                throw new Exception("未查询到商品");
            }

        } catch (Exception ex) {
            queryGoodsRes.setRetCode(TradeEnums.RetEnum.FAIL.getCode());
            queryGoodsRes.setRetInfo(ex.getMessage());
        }
        return queryGoodsRes;
    }

    //减库存
    @Transactional
    public ReduceGoodsNumberRes reduceGoodsNumber(ReduceGoodsNumberReq reduceGoodsNumberReq) {
        ReduceGoodsNumberRes reduceGoodsNumberRes = new ReduceGoodsNumberRes();
        reduceGoodsNumberRes.setRetCode(TradeEnums.RetEnum.SUCCESS.getCode());
        reduceGoodsNumberRes.setRetInfo(TradeEnums.RetEnum.SUCCESS.getDesc());
        if (reduceGoodsNumberReq == null || reduceGoodsNumberReq.getGoodsId() == null
                || reduceGoodsNumberReq.getGoodsNumber() == null || reduceGoodsNumberReq.getGoodsNumber() <= 0
                ) {
            throw new RuntimeException("扣减库存请求参数不正确");
        }
        TradeGoods tradeGoods = new TradeGoods();
        tradeGoods.setGoodsId(reduceGoodsNumberReq.getGoodsId());
        tradeGoods.setGoodsNumber(reduceGoodsNumberReq.getGoodsNumber());
        //减库存db操作
        int i = this.tradeGoodsMapper.reduceGoodsNumber(tradeGoods);
        if (i <= 0) {
            throw new RuntimeException("扣减库存失败");
        }

        //记录减库存日志
        TradeGoodsNumberLog tradeGoodsNumberLog = new TradeGoodsNumberLog();
        tradeGoodsNumberLog.setGoodsId(reduceGoodsNumberReq.getGoodsId());
        tradeGoodsNumberLog.setGoodsNumber(reduceGoodsNumberReq.getGoodsNumber());
        tradeGoodsNumberLog.setOrderId(reduceGoodsNumberReq.getOrderId());
        tradeGoodsNumberLog.setLogTime(new Date());
        this.tradeGoodsNumberLogMapper.insert(tradeGoodsNumberLog);
        return reduceGoodsNumberRes;
    }

    //加库存
    public AddGoodsNumberRes addGoodsNumber(AddGoodsNumberReq addGoodsNumberReq) {
        AddGoodsNumberRes addGoodsNumberRes = new AddGoodsNumberRes();
        addGoodsNumberRes.setRetCode(TradeEnums.RetEnum.SUCCESS.getCode());
        addGoodsNumberRes.setRetInfo(TradeEnums.RetEnum.SUCCESS.getDesc());
        try{
            if (addGoodsNumberReq == null || addGoodsNumberReq.getGoodsId() == null
                    || addGoodsNumberReq.getGoodsNumber() == null || addGoodsNumberReq.getGoodsNumber() <= 0
                    ) {
                throw new RuntimeException("增加库存请求参数不正确");
            }
            //先检查有没有扣减库存的记录，这边是订单id和商品id联合主键
            if (addGoodsNumberReq.getOrderId() != null) {
                TradeGoodsNumberLogKey key = new TradeGoodsNumberLogKey();
                key.setGoodsId(addGoodsNumberReq.getGoodsId());
                key.setOrderId(addGoodsNumberReq.getOrderId());
                TradeGoodsNumberLog tradeGoodsNumberLog = this.tradeGoodsNumberLogMapper.selectByPrimaryKey(key);
                if (tradeGoodsNumberLog == null) {
                    throw new Exception("未找到扣库存记录");
                }
            }
            TradeGoods tradeGoods = new TradeGoods();
            tradeGoods.setGoodsId(addGoodsNumberReq.getGoodsId());
            tradeGoods.setGoodsNumber(addGoodsNumberReq.getGoodsNumber());
            //增加库存操作
            int i = this.tradeGoodsMapper.addGoodsNumber(tradeGoods);
            if (i <= 0) {
                throw new Exception("增加库存失败");
            }

        }catch(Exception ex){
            addGoodsNumberRes.setRetCode(TradeEnums.RetEnum.FAIL.getCode());
            addGoodsNumberRes.setRetInfo(ex.getMessage());
        }

        return addGoodsNumberRes;
    }
}

