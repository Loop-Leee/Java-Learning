package com.lloop.designpatternlearning.strategy;

import org.springframework.stereotype.Component;

@Component
public class WeChatPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("使用微信支付了 " + amount + " 元");
    }
}