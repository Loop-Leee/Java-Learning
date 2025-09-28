package com.lloop.concurrentlearning.status;

public class ThreadStateDemo {
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        // 线程 1：正常运行 + sleep
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("t1: 开始运行");
                Thread.sleep(2000); // TIMED_WAITING
                System.out.println("t1: 执行结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1");

        // 线程 2：进入 WAITING
        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("t2: 获取锁，进入 wait()");
                    lock.wait(); // WAITING, 立刻释放锁，进入等待状态
                    System.out.println("t2: 被唤醒，继续执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T2");

        // 线程 3：进入 BLOCKED
        Thread t3 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("t3: 成功获取到锁，执行完毕");
            }
        }, "T3");

        // 启动前，状态应该是 NEW
        System.out.println("初始状态：");
        System.out.println("T1 = " + t1.getState());
        System.out.println("T2 = " + t2.getState());
        System.out.println("T3 = " + t3.getState());

        Thread.sleep(2000);

        // 启动线程
        t1.start();
        t2.start();
        Thread.sleep(500); // 确保 t2 进入 WAITING
        t3.start();

        // 观察中间状态
        Thread.sleep(1000);
        System.out.println("\n中间状态：");
        System.out.println("T1 = " + t1.getState()); // RUNNABLE 或 TIMED_WAITING
        System.out.println("T2 = " + t2.getState()); // WAITING
        System.out.println("T3 = " + t3.getState()); // BLOCKED

        // 主线程获取同步锁并进行唤醒操作
        // 等主线程退出 synchronized(lock) 之后，锁释放了，T2 才有机会去抢这个锁
        synchronized (lock) {
            lock.notify(); // 唤醒 lock 的等待队列中的一个线程,让其进入所竞争队列
        }

        // 等待所有线程结束
        t1.join();
        t2.join();
        t3.join();

        System.out.println("\n最终状态：");
        System.out.println("T1 = " + t1.getState());
        System.out.println("T2 = " + t2.getState());
        System.out.println("T3 = " + t3.getState());
    }
}