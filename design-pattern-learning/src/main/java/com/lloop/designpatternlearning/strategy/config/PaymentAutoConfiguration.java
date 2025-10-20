package com.lloop.designpatternlearning.strategy.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(PaymentProperties.PREFIX)
@EnableConfigurationProperties(PaymentProperties.class)
@Configuration
public class PaymentAutoConfiguration {
}