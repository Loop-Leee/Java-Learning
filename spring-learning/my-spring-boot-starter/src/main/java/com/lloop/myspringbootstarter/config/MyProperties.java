package com.lloop.myspringbootstarter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author lloop
 * @Create 2025/11/14 11:53
 */
@ConfigurationProperties(prefix = "my")
public class MyProperties {

    private String name;

    public String getName() {
        return name;
    }

}
