package com.lloop.designpatternlearning.strategy;

import org.springframework.stereotype.Service;

/**
 * 支付服务, 通过 "策略模式 + 工厂模式" 避免代码耦合, 确保添加策略时也可以做到代码无侵入
 * @Author lloop
 * @Create 2025/9/25 13:51
 */
@Service
public class PayService {

    public void pay(double amount, String payType) {
        // 通过工厂方法获取支付策略
        PaymentStrategy paymentStrategy = PaymentStrategyFactory.getPaymentStrategy(payType);

        if (paymentStrategy == null) {
            throw new RuntimeException("不支持的支付方式");
        }

        paymentStrategy.pay(amount);
    }

}
