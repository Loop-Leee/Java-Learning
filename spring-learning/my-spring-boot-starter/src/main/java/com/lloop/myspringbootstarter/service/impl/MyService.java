package com.lloop.myspringbootstarter.service.impl;

import com.lloop.myspringbootstarter.config.MyProperties;
import com.lloop.myspringbootstarter.service.IMyService;

/**
 * @Author lloop
 * @Create 2025/11/14 12:01
 */
public class MyService implements IMyService {

    private final MyProperties properties;

    public MyService(MyProperties properties) {
        this.properties = properties;
    }

    @Override
    public String getName() {
        return properties.getName();
    }

}
