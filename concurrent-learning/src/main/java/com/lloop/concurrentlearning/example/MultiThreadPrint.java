package com.lloop.concurrentlearning.example;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 题目要求: 5 个线程，分别打印 A B C D E，要求每个线程打印 5 次，打印顺序为 ABCDEABCDEABCDEABCDEABCDE
 */
public class MultiThreadPrint extends Thread {
    static int index = 5;            // 线程编号
    static int maxCount = 25;         // 最大打印次数
    static AtomicInteger count = new AtomicInteger(0);  // 计数器, 记录当前已经打印的次数
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    private int assign;       // 线程编号
    private final Runnable action;  // 线程要执行的任务

    public MultiThreadPrint(int assign, Runnable action) {
        this.assign = assign;
        if (assign == index) {
            this.assign = 0;
        }
        this.action = action;
    }

    @Override
    public void run() {
        // 只要count 小于 maxCount，就还需要打印
        while (count.get() < maxCount) {
            try {
                lock.lock();
                while (count.get() < maxCount && count.get() % index != assign) {
                    condition.await();
                }
                if (count.get() < maxCount) {
                    // 由于有顺序打印的要求,所以需要判断是否是当前线程需要打印
                    this.action.run();
                    count.incrementAndGet();
                }
                condition.signalAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MultiThreadPrint[] threads = new MultiThreadPrint[5];
        for (int i = 0; i < 5; i++) {
            final int assign = i;
            threads[i] = new MultiThreadPrint(assign, () -> {
                System.out.println(count.get() + " " + Thread.currentThread().getName() + " " + (char) ('A' + assign));
            });
            threads[i].start();
        }

        for (MultiThreadPrint thread : threads) {
            thread.join();
        }

        System.out.println("打印完成！");
    }
}