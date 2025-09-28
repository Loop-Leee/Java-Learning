package com.lloop.designpatternlearning.strategy;

public class WeChatPay implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("使用微信支付了 " + amount + " 元");
    }
}
