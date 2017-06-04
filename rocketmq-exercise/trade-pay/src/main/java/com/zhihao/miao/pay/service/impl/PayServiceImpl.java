package com.zhihao.miao.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.zhihao.miao.common.constants.MQEnums;
import com.zhihao.miao.common.constants.TradeEnums;
import com.zhihao.miao.common.exception.AceMQException;
import com.zhihao.miao.common.protocol.mq.PaidMQ;
import com.zhihao.miao.common.protocol.pay.CallbackPaymentReq;
import com.zhihao.miao.common.protocol.pay.CallbackPaymentRes;
import com.zhihao.miao.common.protocol.pay.CreatePaymentReq;
import com.zhihao.miao.common.protocol.pay.CreatePaymentRes;
import com.zhihao.miao.common.rocketmq.AceMQProducer;
import com.zhihao.miao.common.util.IDGenerator;
import com.zhihao.miao.dao.entity.TradeMqProducerTemp;
import com.zhihao.miao.dao.entity.TradePay;
import com.zhihao.miao.dao.entity.TradePayExample;
import com.zhihao.miao.dao.mapper.TradeMqProducerTempMapper;
import com.zhihao.miao.dao.mapper.TradePayMapper;
import com.zhihao.miao.pay.service.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PayServiceImpl implements IPayService {
    @Autowired
    private TradePayMapper tradePayMapper;
    @Autowired
    private TradeMqProducerTempMapper tradeMqProducerTempMapper;
    @Autowired
    private AceMQProducer aceMQProducer;
    private ExecutorService executorService= Executors.newFixedThreadPool(10);
    @Override
    public CreatePaymentRes createPayment(CreatePaymentReq createPaymentReq) {
        CreatePaymentRes createPaymentRes = new CreatePaymentRes();
        createPaymentRes.setRetCode(TradeEnums.RetEnum.SUCCESS.getCode());
        createPaymentRes.setRetInfo(TradeEnums.RetEnum.SUCCESS.getDesc());
        try {
            TradePayExample payExample = new TradePayExample();
            payExample.createCriteria()
                    .andOrderIdEqualTo(createPaymentReq.getOrderId())
                    .andIsPaidEqualTo(TradeEnums.YesNoEnum.YES.getCode());
            int count = this.tradePayMapper.countByExample(payExample);
            if (count > 0) {
                throw new Exception("该订单已支付");
            }
            String payId = IDGenerator.generateUUID();
            TradePay tradePay = new TradePay();
            tradePay.setPayId(payId);
            tradePay.setOrderId(createPaymentReq.getOrderId());
            tradePay.setIsPaid(TradeEnums.YesNoEnum.NO.getCode());
            tradePay.setPayAmount(createPaymentReq.getPayAmount());
            tradePayMapper.insert(tradePay);
            System.out.println("创建支付单成功:"+payId);
        } catch (Exception ex) {
            createPaymentRes.setRetCode(TradeEnums.RetEnum.FAIL.getCode());
            createPaymentRes.setRetInfo("创建支付单失败:"+ex.getMessage());
        }
        return createPaymentRes;
    }

    @Override
    @Transactional
    public CallbackPaymentRes callbackPayment(CallbackPaymentReq callbackPaymentReq) {
        CallbackPaymentRes callbackPaymentRes = new CallbackPaymentRes();
        callbackPaymentRes.setRetCode(TradeEnums.RetEnum.SUCCESS.getCode());
        callbackPaymentRes.setRetInfo(TradeEnums.RetEnum.SUCCESS.getDesc());
        if (callbackPaymentReq.getIsPaid().equals(TradeEnums.YesNoEnum.YES.getCode())) {

            //更新支付状态
            TradePay tradePay = tradePayMapper.selectByPrimaryKey(callbackPaymentReq.getPayId());
            if (tradePay == null) {
                throw new RuntimeException("未找到支付单");
            }

            if (tradePay.getIsPaid().equals(TradeEnums.YesNoEnum.YES.getCode())) {
                throw new RuntimeException("该支付单已支付");
            }

            tradePay.setIsPaid(TradeEnums.YesNoEnum.YES.getCode());
            int i = tradePayMapper.updateByPrimaryKeySelective(tradePay);
            //发送可靠消息
            if (i == 1) {
                final PaidMQ paidMQ = new PaidMQ();
                paidMQ.setPayAmount(tradePay.getPayAmount());
                paidMQ.setOrderId(tradePay.getOrderId());
                paidMQ.setPayId(tradePay.getPayId());
                final TradeMqProducerTemp mqProducerTemp = new TradeMqProducerTemp();
                mqProducerTemp.setId(IDGenerator.generateUUID());
                mqProducerTemp.setGroupName("payProducerGroup");
                mqProducerTemp.setMsgKeys(tradePay.getPayId());
                mqProducerTemp.setMsgTopic(MQEnums.TopicEnum.PAY_PAID.getTopic());
                mqProducerTemp.setMsgTag(MQEnums.TopicEnum.PAY_PAID.getTag());
                mqProducerTemp.setMsgBody(JSON.toJSONString(paidMQ));
                mqProducerTemp.setCreateTime(new Date());
                tradeMqProducerTempMapper.insert(mqProducerTemp);
                //异步发送mq，发送成功清空发送表
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            SendResult sendResult = aceMQProducer.sendMessage(MQEnums.TopicEnum.PAY_PAID, paidMQ.getPayId(), JSON.toJSONString(paidMQ));
                            System.out.println(sendResult);
                            if (sendResult.getSendStatus().equals(SendStatus.SEND_OK)) {
                                tradeMqProducerTempMapper.deleteByPrimaryKey(mqProducerTemp.getId());
                                System.out.println("删除消息表成功");
                            }
                        } catch (AceMQException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }else{
                throw new RuntimeException("该支付单已支付");
            }
        }
        return callbackPaymentRes;
    }


}

