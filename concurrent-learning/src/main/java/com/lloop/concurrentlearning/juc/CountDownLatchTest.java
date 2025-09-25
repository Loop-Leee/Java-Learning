package com.lloop.concurrentlearning.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @Author lloop
 * @Create 2025/4/24 17:29
 * @Description CountDownLatch, 用于阻塞线程，等待所有线程完成工作
 * CountDownLatch 是一次性的，只能使用一次
 */
public class CountDownLatchTest {

    /**
     * 模拟6个同学离开教室，都离开后，班长锁门
     */
    public static void main(String[] args) {
        int students = 6;
        System.out.println("---班上一共有" + students + "个学生在上晚自习---");
        CountDownLatch countDownLatch = new CountDownLatch(students);
        for (int i = 0; i <= 5; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep((long) (Math.random() * 3000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t 上完自习，离开教室");
                countDownLatch.countDown();
            }, (char)(i+'A') + "").start();
        }
        try {
            System.out.println("班长开始清点人数准备关门...");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t 班长最后关门走人");

    }
}
