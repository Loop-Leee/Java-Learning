package com.lloop.springbasic.proxy;

import java.lang.reflect.Proxy;

public class ProxyDemo {
    public static void main(String[] args) {

        System.out.println("[main] ------- 创建代理对象 -------");

        HelloService target = new HelloServiceImpl();           // 创建委托类的实例对象

        // 创建代理对象, 用于代理委托类实例
        HelloService proxy = (HelloService) Proxy.newProxyInstance(
                HelloService.class.getClassLoader(),
                new Class[]{HelloService.class},
                new LogInvocationHandler(target));
        // 通过工厂模式创建代理对象, 用于代理委托类实例
        HelloService proxyByFactory = ProxyFactory.createProxy(target);

        System.out.println("[main] 委托类: " + target.getClass().getName());
        System.out.println("[main] 代理类: " + proxy.getClass().getName() + " 和 " + proxyByFactory.getClass().getName());

        System.out.println("\n[main] ------- 通过代理类调用方法 ------- \n");

        String result = proxy.sayHello("Lloop");
        System.out.println("[main] 代理类执行方法: " + proxy.getClass().getName() + ".sayHello(\"Lloop\")");
        System.out.println("[main] 代理类执行结果: " + result);
        System.out.println();

        result = proxyByFactory.sayHello("Lloop");
        System.out.println("[main] 代理类执行方法: " + proxyByFactory.getClass().getName() + ".sayHello(\"Lloop\")");
        System.out.println("[main] 代理类执行结果: " + result);
    }
}


