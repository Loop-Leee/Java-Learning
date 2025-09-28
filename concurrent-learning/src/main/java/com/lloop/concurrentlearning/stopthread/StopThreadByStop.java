package com.lloop.concurrentlearning.stopthread;

/**
 * 强制终止，可能导致锁未释放、数据不一致
 */
@SuppressWarnings("deprecation") // 不推荐用
public class StopThreadByStop {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("正在运行...");
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                System.out.println("线程被中断");
            } finally {
                System.out.println("线程 finally 块仍然会被执行");
            }
        });

        t.start();
        Thread.sleep(2000);
        t.stop(); // 强制结束
        System.out.println("线程被 stop 强制终止");
    }
}