package com.lloop.designpatternlearning.singleton;

/**
 * @Author lloop
 * @Create 2025/10/8 12:26
 * @Description
 * 单例模式
 * 通过synchronize确保只有一个线程进入同步块创建实例
 * 通过volatile确保对象引用的可见性以及创建过程的有序性,避免发生指令重排序
 */
public class Singleton {

    private static volatile Singleton instance;

    private Singleton getInstance() {
        if(instance == null) {
            synchronized (Singleton.class) {
                if(instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}
