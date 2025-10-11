package com.lloop.concurrentlearning.redis;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class CacheService {
    private final ReentrantLock lock = new ReentrantLock(true);
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private volatile Object cacheData;
    private volatile long expireTime = 0;

    public Object getData() {
        long now = System.currentTimeMillis();
        // 缓存未过期: 直接返回缓存数据
        if (cacheData != null && now < expireTime) {
            return cacheData;
        }

        // 缓存已过期: 异步执行刷新, 并且防止并发刷新
        if (lock.tryLock()) {
            try {
                executor.submit(this::refreshCache);
            } finally {
                lock.unlock();
            }
        }

        // 缓存已过期并且已经提交了刷新请求: 直接返回旧缓存（即使过期）
        return cacheData;
    }

    private void refreshCache() {
        lock.lock(); // 确保只一个刷新任务在执行
        try {
            Object newData = queryDB();
            cacheData = newData;
            expireTime = System.currentTimeMillis() + 10_000;
        } finally {
            lock.unlock();
        }
    }

    private Object queryDB() {
        try { Thread.sleep(200); } catch (InterruptedException ignored) {}
        return "新数据@" + System.currentTimeMillis();
    }


    public static void main(String[] args) throws InterruptedException {
        CacheService cacheService = new CacheService();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(cacheService.getData());
            }).start();
            Thread.sleep(10);
        }
    }
}