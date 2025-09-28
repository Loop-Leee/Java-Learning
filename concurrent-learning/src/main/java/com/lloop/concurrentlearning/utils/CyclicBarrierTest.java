package com.lloop.concurrentlearning.utils;

import java.util.concurrent.*;

/**
 * @Author lloop
 * @Create 2025/4/24 17:25
 * @Description 循环栅栏, 用于阻塞线程，直到所有线程都到达栅栏位置，然后一起执行
 */
public class CyclicBarrierTest {

    private static ThreadPoolExecutor executorService = new ThreadPoolExecutor(7, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙");
        });
        for (int i = 1; i <= 14; i++) {
            final int tempInt = i;
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "\t收集到第" + tempInt + "龙珠");
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "Thread-" + String.valueOf(i)).start();
        }

        for (int i = 1; i <= 14; i++) {
            final int tempInt = i;
            executorService.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "\t收集到第" + tempInt + "龙珠");
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }

}
