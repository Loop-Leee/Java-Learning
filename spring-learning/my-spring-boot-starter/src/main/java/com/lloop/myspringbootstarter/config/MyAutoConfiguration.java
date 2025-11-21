package com.lloop.myspringbootstarter.config;

import com.lloop.myspringbootstarter.service.impl.MyService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author lloop
 * @Create 2025/11/14 11:52
 */
@Configuration
@EnableConfigurationProperties(MyProperties.class)
public class MyAutoConfiguration {

    private final MyProperties myProperties;

    public MyAutoConfiguration(MyProperties myProperties) {
        this.myProperties = myProperties;
    }

    @Bean
    public MyService myService() {
        return new MyService(myProperties);
    }

}