package com.lloop.designpatternlearning.strategy;

public class Alipay implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("使用支付宝支付了 " + amount + " 元");
    }
}