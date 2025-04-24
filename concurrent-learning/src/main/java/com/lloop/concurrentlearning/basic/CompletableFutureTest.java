package com.lloop.concurrentlearning.basic;

import java.util.concurrent.*;

public class CompletableFutureTest {

    private static final ExecutorService threadPool = new ThreadPoolExecutor(
            2,
            5,
            1,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(3),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.DiscardOldestPolicy()
    );


    public static void main(String[] args) {

        int input1 = 10;  // 外部参数1
        int input2 = 20;  // 外部参数2

        // 使用一个对象包装多个参数
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 访问外部参数 input1 和 input2
            return input1 + input2;
        }, threadPool).thenApply(result -> {
            return result * 2;  // 对结果进行处理
        }).thenAccept(finalResult -> {
            System.out.println("最终结果: " + finalResult);  // 输出最终结果
        });

        System.out.println("异步非阻塞,不用等待 CompletableFuture 的结果");

        /*
        // 不能这样,因为 input1 和 input2 是在异步线程中访问的，它们在主线程中修改会影响异步线程中的值
        input1 = 30;
        input2 = 40;
        System.out.println("input1:" + input1 + "input2:" + input2);
        */

    }


}
