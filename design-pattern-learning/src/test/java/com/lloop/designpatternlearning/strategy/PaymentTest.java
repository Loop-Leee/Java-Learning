package com.lloop.designpatternlearning.strategy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author lloop
 * @Create 2025/10/20 20:25
 */
@SpringBootTest
public class PaymentTest {

    @Autowired
    private PayService payService;

    @Test
    public void testPayment() {
        payService.pay(100, "weChatPayment");
    }
}
