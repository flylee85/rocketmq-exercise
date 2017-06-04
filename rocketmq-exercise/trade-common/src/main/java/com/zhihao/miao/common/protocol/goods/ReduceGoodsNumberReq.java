package com.zhihao.miao.common.protocol.goods;

//减库存的请求
public class ReduceGoodsNumberReq {
    private Integer goodsId;  //商品id
    private Integer goodsNumber;  //商品库存
    private String orderId; //订单id

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
