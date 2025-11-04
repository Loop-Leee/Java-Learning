package com.lloop.springbasic.controller;

import com.lloop.springbasic.service.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lloop
 * @Create 2025/9/25 09:30
 * 测试循环依赖注入是否成功
 */
@RestController
public class CircularDependencyTestController {

    @Autowired
    private AService aService;

    @GetMapping("/test-circular-dependency")
    public String testCircularDependency() {
        System.out.println("开始测试循环依赖...");
        aService.doSomethingWithB();
        return "循环依赖测试完成！请查看控制台输出。";
    }
}
