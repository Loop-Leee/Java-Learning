package com.lloop.designpatternlearning.strategy;

import org.springframework.stereotype.Component;

/**
 * 实际负责执行交易的类
 */
@Component
public class PaymentContext {

    private PaymentStrategy strategy;

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void executePayment(double amount) {
        strategy.pay(amount);
    }
}