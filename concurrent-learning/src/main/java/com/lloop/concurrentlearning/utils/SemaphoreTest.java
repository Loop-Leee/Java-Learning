package com.lloop.concurrentlearning.utils;

import java.util.concurrent.Semaphore;

/**
 * @Author lloop
 * @Create 2025/4/24 17:39
 * @Description 信号量, 用于控制访问的线程数量
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    Thread.sleep((long) (Math.random() * 1000));
                    System.out.println(Thread.currentThread().getName() + " 抢到了车位");
                    Thread.sleep((long) (Math.random() * 1000));
                    System.out.println(Thread.currentThread().getName() + " 离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
