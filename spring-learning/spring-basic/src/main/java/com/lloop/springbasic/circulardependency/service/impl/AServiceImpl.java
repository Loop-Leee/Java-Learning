package com.lloop.springbasic.circulardependency.service.impl;

import com.lloop.springbasic.circulardependency.service.BService;
import com.lloop.springbasic.circulardependency.service.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @Author lloop
 * @Create 2025/9/24 16:06
 */
@Service
public class AServiceImpl implements AService {

    @Autowired
    @Lazy  // 使用@Lazy注解解决循环依赖
    private BService bService;

    @Override
    public void doSomethingWithB() {
        System.out.println("AService 调用 BService");
        bService.doSomethingWithA();
    }
}
