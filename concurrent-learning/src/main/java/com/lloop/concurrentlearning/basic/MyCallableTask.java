package com.lloop.concurrentlearning.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

// 1. 创建实现Callable接口的任务类
public class MyCallableTask implements Callable<String> {
    private static final Logger log = LoggerFactory.getLogger(MyCallableTask.class);

    @Override
    public String call() {
        // 执行任务并返回结果
        return "Task result: " + Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        // 方式一: 使用FutureTask和Callable
        MyCallableTask callableTask = new MyCallableTask();                 // 创建Callable任务实例
        FutureTask<String> futureTask1 = new FutureTask<>(callableTask);     // 创建FutureTask包装Callable任务
        Thread thread = new Thread(futureTask1, "implementCallableThread");   // 创建线程并启动
        thread.start();

        try {
            String result = futureTask1.get();            // 获取任务执行结果
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            log.debug(e.getMessage());
        }


        // 方式二: 使用lambda表达式和Callable
        FutureTask<String> futureTask2 = new FutureTask<>(() -> {
            // 执行任务并返回结果
            return "Task result: " + Thread.currentThread().getName();
        });
        new Thread(futureTask2, "lambdaCallableThread").start();
        try {
            String result = futureTask2.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            log.debug(e.getMessage());
        }
    }
}
