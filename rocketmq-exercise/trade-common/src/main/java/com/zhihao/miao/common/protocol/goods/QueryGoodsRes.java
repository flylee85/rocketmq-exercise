package com.zhihao.miao.common.protocol.goods;

import com.zhihao.miao.common.protocol.BaseRes;

import java.math.BigDecimal;
import java.util.Date;

public class QueryGoodsRes extends BaseRes {
    private Integer goodsId; //商品id

    private String goodsName; //商品名称

    private Integer goodsNumber;  //商品数目

    private BigDecimal goodsPrice; //商品价格

    private String goodsDesc;  //商品描述

    private Date addTime; //添加时间

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
}
