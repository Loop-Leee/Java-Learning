package com.lloop.designpatternlearning.strategy;

import com.lloop.designpatternlearning.strategy.config.PaymentProperties;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaymentStrategyFactory implements ApplicationContextAware {

    private static Map<String, PaymentStrategy> strategyMap;

    /**
     * 通过Spring容器初始化策略对象
     * @param applicationContext Spring容器
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        strategyMap = new HashMap<>();
        PaymentProperties paymentProperties = applicationContext.getBean(PaymentProperties.class);
        paymentProperties.getTypes().forEach((name, type) ->
                strategyMap.put(name, applicationContext.getBean(type, PaymentStrategy.class))
        );
    }

    /**
     * 获取支付策略
     * @param payType 支付方式
     * @return 对应的支付策略
     */
    public static PaymentStrategy getPaymentStrategy(String payType) {
        return strategyMap.get(payType);
    }
    
    /**
     * 获取所有策略
     * @return 策略映射
     */
    public Map<String, PaymentStrategy> getStrategyMap() {
        return strategyMap;
    }

}