package com.lloop.concurrentlearning.basic;

public class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        // 线程执行逻辑
        System.out.println("Thread " + Thread.currentThread().getName() + " is running");
    }

    public static void main(String[] args) {
        // 创建方式一: 继承并实现 Thread 类
        MyThread thread = new MyThread("extendThread");
        thread.start();  // 启动线程

        // 创建方式二: 直接使用 lambda 表达式
        new Thread(() -> {
            System.out.println("Thread " + Thread.currentThread().getName() + " is running");
        }, "lambdaThread").start();
    }
}

