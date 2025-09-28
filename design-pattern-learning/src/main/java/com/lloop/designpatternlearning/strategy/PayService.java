package com.lloop.designpatternlearning.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author lloop
 * @Create 2025/9/25 13:51
 */
@Service
public class PayService {

    @Autowired
    private PaymentContext paymentContext;

    public void pay(double amount) {
        paymentContext.executePayment(amount);
    }

}
