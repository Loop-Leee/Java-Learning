package com.lloop.designpatternlearning.singleton;

/**
 * @Author lloop
 * @Create 2025/10/8 12:26
 * @Description
 * 单例模式
 * 通过synchronize确保只有一个线程进入同步块创建实例
 * 通过volatile确保对象引用的可见性以及创建过程的有序性,避免发生指令重排序, 否则其他线程有可能提前拿到未进行初始化的引用
 */
public class Singleton {

    private static volatile Singleton instance;

    private Singleton getInstance() {
        if(instance == null) {
            synchronized (Singleton.class) {
                if(instance == null) {
                    /**
                     * 创建实例的三个步骤(指令重排可能发生, 需要添加volatile修饰符进行避免)
                     * 1. 分配内存
                     * 2. 初始化(实例化)对象
                     * 3. 设置instance的引用
                     */
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}
