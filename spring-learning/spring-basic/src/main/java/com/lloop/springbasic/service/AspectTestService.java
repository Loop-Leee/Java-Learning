package com.lloop.springbasic.service;

import com.lloop.springbasic.annotation.LogExecution;

/**
 * @Author lloop
 * @Create 2025/11/11 14:46
 */
public interface AspectTestService {


    @LogExecution(value = "调用无参数方法", logTime = true, logArgs = true, logResult = true)
    void callNoArgsMethod();

    @LogExecution(value = "计算两数之和", logTime = true, logArgs = true, logResult = true)
    int add(int a, int b);

    @LogExecution(value = "演示异常处理", logTime = true, logArgs = true, logResult = true)
    String throwException(String message);
}
