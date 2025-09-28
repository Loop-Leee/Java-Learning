package com.lloop.concurrentlearning.stopthread;


/**
 * 通过 interrupt() 触发 InterruptedException，然后捕获异常后结束线程,即使在sleep()也可以立即响应。
 */
public class StopThreadBySleep {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("工作中...");
                    Thread.sleep(100000); // 被中断时直接抛异常
                }
            } catch (InterruptedException e) {
                System.out.println("sleep 被打断，退出线程");
            }
        });

        t.start();
        Thread.sleep(3000);
        t.interrupt();
    }
}