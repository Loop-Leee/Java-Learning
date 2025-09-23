package com.lloop.concurrentlearning.basic;

// 1. 创建实现Runnable接口的任务类
public class MyRunnableTask implements Runnable {
    @Override
    public void run() {
        // 执行任务逻辑
        System.out.println("Thread " + Thread.currentThread().getName() + " is running");
    }

    public static void main(String[] args) {
        // 方式1：直接创建线程
        MyRunnableTask task = new MyRunnableTask();
        Thread thread = new Thread(task, "implementRunnableThread");
        thread.start();

        // 方式2: 使用lambda表达式创建线程
        new Thread(() -> {
            System.out.println("Thread " + Thread.currentThread().getName() + " is running");
        }, "lambdaRunnableThread").start();

    }
}