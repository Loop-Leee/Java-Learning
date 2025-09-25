package com.lloop.springlearning.service.impl;

import com.lloop.springlearning.service.AService;
import com.lloop.springlearning.service.BService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author lloop
 * @Create 2025/9/24 16:08
 */
@Service
public class BServiceImpl implements BService {

    // 循环依赖示例: A依赖B, B依赖A
    @Autowired
    private AService aService;

    @Override
    public void doSomethingWithA() {
        System.out.println("BService 被调用，循环依赖注入成功！");
    }
}
