package com.lloop.concurrentlearning.example;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 题目要求: 5 个线程，分别打印 A B C D E，要求每个线程打印 5 次，打印顺序为 ABCDEABCDEABCDEABCDEABCDE
 */
public class MultiThreadPrint extends Thread {
    static int index;
    static int maxCount;
    static AtomicInteger count;
    static ReentrantLock lock = new ReentrantLock(true);
    private int assign;
    private Runnable action;

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
            lock.lock();
            if (count.get() < maxCount && count.get() % index == assign) {
                this.action.run();
                count.incrementAndGet();
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MultiThreadPrint.index = 5;    // 5 个线程交替打印
        MultiThreadPrint.maxCount = 25;  //  总共 25 次打印
        MultiThreadPrint.count = new AtomicInteger(1);

        for (int i = 1; i <= 5; i++) {
            final int assign = i;
            MultiThreadPrint t = new MultiThreadPrint(assign, () -> {
                System.out.println((char)('A' + assign-1));
            });
            t.start();
        }
    }
}