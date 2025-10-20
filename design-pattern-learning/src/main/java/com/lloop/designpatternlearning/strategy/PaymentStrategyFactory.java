package com.lloop.designpatternlearning.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PaymentStrategyFactory {

    private final Map<String, PaymentStrategy> strategyMap;

    @Autowired
    public PaymentStrategyFactory(Map<String, PaymentStrategy> strategyMap) {
        this.strategyMap = strategyMap;
        // 打印strategyMap的内容，用于调试
        strategyMap.forEach((key, value) ->
            System.out.println("正在注册 " + key + " Bean, 对应的类为: " + value.getClass().getSimpleName())
        );
    }

    /**
     * 获取支付策略
     * @param payType 支付方式
     * @return 对应的支付策略
     */
    public PaymentStrategy getPaymentStrategy(String payType) {
        return strategyMap.get(payType);
    }
    
    /**
     * 获取所有策略
     * @return 策略映射
     */
    public Map<String, PaymentStrategy> getStrategyMap() {
        return strategyMap;
    }
}