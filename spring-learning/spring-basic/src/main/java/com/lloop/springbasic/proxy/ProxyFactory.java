package com.lloop.springbasic.proxy;

import java.lang.reflect.Proxy;

public final class ProxyFactory {
    /**
     * 私有构造函数，防止实例化
     */
    private ProxyFactory() {
    }

    /**
     * 创建代理对象
     * 创建过程需要的参数: 目标类加载器, 目标类接口, 代理调用处理器
     *
     * @param target 委托类
     * @param <T>    代理对象类型
     * @return 代理对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(T target) {
        if (target == null) {
            throw new IllegalArgumentException("委托类(Target Class)不能为空");
        }
        // 获取委托类的类加载器、接口、代理处理程序
        ClassLoader classLoader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        LogInvocationHandler handler = new LogInvocationHandler(target);
        // 通过 JDK 创建并返回代理对象
        return (T) Proxy.newProxyInstance(classLoader, interfaces, handler);
    }
}


