package com.lloop.concurrentlearning.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 整体思路: 使用 ReentrantLock 和 Condition 实现生产者-消费者模型
 * 缓冲区为空时，消费者等待数据，缓冲区已满时，生产者等待
 * 缓冲区非空时，消费者消费数据，缓冲区为空时，消费者等待
 */
public class ProducerConsumerExample {
    // 共享缓冲区
    static class Buffer {
        private final Queue<Integer> queue = new LinkedList<>(); // 缓冲区队列
        private final int maxSize;                               // 缓冲区大小
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition notFull = lock.newCondition();  // 缓冲区未满条件
        private final Condition notEmpty = lock.newCondition(); // 缓冲区非空条件

        public Buffer(int maxSize) {
            this.maxSize = maxSize;
        }

        // 生产者放入数据
        public void put(int value) throws InterruptedException {
            lock.lock();
            try {
                // 当缓冲区满时，生产者等待
                // 注意: 一定要将等待条件放在 while 循环, 否则可能导致线程假死
                while (queue.size() == maxSize) {
                    System.out.println("缓冲区已满，生产者等待...");
                    notFull.await();   // 并发编程原则: 等待条件必须放在 while 循环中
                }
                queue.offer(value);
                System.out.println("生产: " + value + " | 缓冲区大小: " + queue.size());
                // 唤醒等待的消费者
                // 注意: 一定要唤醒所有的, 否则有可能导致部分线程一直被阻塞
                notEmpty.signalAll();
            } finally {
                lock.unlock();
            }
        }

        // 消费者获取数据
        public int take() throws InterruptedException {
            lock.lock();
            try {
                // 当缓冲区空时，消费者等待
                while (queue.isEmpty()) {
                    System.out.println("缓冲区为空，消费者等待...");
                    notEmpty.await();
                }
                int value = queue.poll();
                System.out.println("消费: " + value + " | 缓冲区大小: " + queue.size());
                // 唤醒等待的生产者
                notFull.signalAll();
                return value;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Buffer buffer = new Buffer(5); // 缓冲区容量为5
        
        // 生产者线程
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    buffer.put(i);
                    Thread.sleep(100); // 模拟生产耗时
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // 消费者线程
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    buffer.take();
                    Thread.sleep(150); // 模拟消费耗时
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}