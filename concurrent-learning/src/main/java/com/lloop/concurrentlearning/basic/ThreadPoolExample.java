package com.lloop.concurrentlearning.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolExample {
    public static void main(String[] args) {
        // 创建线程池
        // ExecutorService executorService = Executors.newFixedThreadPool(3);
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(2, 4, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        // 提交多个任务并获取Future对象
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int finalI = i;   // runnable 只能接受 final 变量或者事实上的 final 变量
            Future<String> future = executorService.submit(() -> {
                Thread.sleep(1000);
                return "Task " + finalI + " 正在由 " + Thread.currentThread().getName() + " 执行";
            });
            futures.add(future);
        }

        // 获取所有任务的结果
        try {
            for (int i = 0; i < futures.size(); i++) {
                String result = futures.get(i).get();
                System.out.println("Task " + i + " result: " + result);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            executorService.shutdown();
        }
    }
}