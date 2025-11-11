package com.lloop.springbasic.aop.service.impl;

import com.lloop.springbasic.aop.annotation.LogExecution;
import com.lloop.springbasic.aop.service.AspectTestService;
import org.springframework.stereotype.Service;

/**
 * @Author lloop
 * @Create 2025/11/11 14:47
 */
@Service
public class AspectTestServiceImpl implements AspectTestService {

    /**
     * AOP: 演示切面功能, 调用一个无参数方法
     */
    @Override
    @LogExecution(value = "调用无参数方法", logTime = true, logArgs = true, logResult = true)
    public void callNoArgsMethod() {
        System.out.println("调用无参数方法");
    }

    /**
     * AOP: 演示切面功能, 计算两个数的和
     */
    @Override
    @LogExecution(value = "计算两数之和", logTime = true, logArgs = true, logResult = true)
    public int add(int a, int b) {
        try {
            Thread.sleep(100); // 模拟耗时操作
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return a + b;
    }

    /**
     * AOP: 演示异常通知, 故意抛出异常
     */
    @Override
    @LogExecution(value = "演示异常处理", logTime = true, logArgs = true, logResult = true)
    public String throwException(String message) {
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("消息不能为空");
        }
        return "处理成功: " + message;
    }

}
