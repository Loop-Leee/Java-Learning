package com.lloop.concurrentlearning.stopthread;

/**
 * 通过 interrupt() 触发 InterruptedException，然后捕获异常后结束线程。
 */
public class StopThreadByException {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + "运行中...");
                    Thread.sleep(1000); // 可能抛出 InterruptedException
                }
            } catch (InterruptedException e) {
                System.out.println("线程被中断，抛异常退出");
            }
        });

        System.out.println(Thread.currentThread().getName() + "启动线程任务");
        t.start();
        Thread.sleep(3000);
        t.interrupt(); // 发出中断信号
    }
}