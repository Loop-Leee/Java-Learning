package com.lloop.designpatternlearning.strategy;

import org.springframework.stereotype.Service;

/**
 * @Author lloop
 * @Create 2025/9/25 13:51
 */
@Service
public class PayService {

    public void pay(double amount, String payType) {
        PaymentStrategy paymentStrategy = PaymentStrategyFactory.getPaymentStrategy(payType);

        if (paymentStrategy == null) {
            throw new RuntimeException("不支持的支付方式");
        }

        paymentStrategy.pay(amount);
    }

}
