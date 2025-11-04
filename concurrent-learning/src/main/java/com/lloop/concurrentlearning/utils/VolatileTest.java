package com.lloop.concurrentlearning.utils;

/**
 * @Author lloop
 * @Create 2025/10/17 09:44
 */
public class VolatileTest {

    // 如果不使用 volatile，线程2可能永远无法结束
    private static /*volatile*/ boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            stop = true;
            System.out.println("Thread-1 modify stop to true");
        });

        Thread t2 = new Thread(() -> {
            while (!stop) {
                // 空循环等待 stop 变为 true

                // 这里的 println 也不可以使用, 因为println方法内部使用了 synchronized 同步锁会使得线程从主存读取 stop 的值
                // System.out.println("Thread-2 is waiting for stop to be true");

            }
            System.out.println("Thread-2 read stop = "  + stop);
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("All threads finished");
    }
}