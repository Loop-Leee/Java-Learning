package com.lloop.designpatternlearning.strategy;

import org.springframework.stereotype.Component;

/**
 * 支付宝支付, 支付策略中的一种实现
 */
@Component
public class AliPayment implements PaymentStrategy {

    public void pay(double amount) {
        System.out.println("使用支付宝支付了 " + amount + " 元");
    }
}