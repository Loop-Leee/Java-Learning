package com.lloop.designpatternlearning.strategy;

/**
 * 支付策略接口
 */
public interface PaymentStrategy {

    /**
     * 支付接口, 各类策略都需要实现的方法
     * @param amount 金额
     */
    void pay(double amount);
}