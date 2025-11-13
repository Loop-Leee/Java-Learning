package com.lloop.springbasic.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 日志代理调用处理器 (Log Invocation Handler)
 *
 * 任务: 基于Java动态代理机制（JDK Dynamic Proxy）实现AOP（面向切面编程）中的日志记录功能。
 * 目标: 在不修改原始类代码的前提下，为目标对象的方法调用添加统一的日志记录能力，在方法执行前、执行后以及执行过程中发生异常时的状态追踪, 提升系统的可观察性和调试效率。
 *
 * 实现流程：
 * 1. 实现InvocationHandler接口：作为动态代理的核心接口，其invoke方法是所有代理方法调用的统一入口和拦截点。
 * 2. 持有目标对象：通过构造函数注入被代理的真实对象（target），从而保证后续方法调用能正确转发至原始实现。
 */
public class LogInvocationHandler implements InvocationHandler {
    private final Object target;

    public LogInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        System.out.println("[Proxy] 代理类 " + proxy.getClass().getName() + " 即将执行方法: " + methodName + ", 参数: " + Arrays.toString(args));
        try {
            Object result = method.invoke(target, args);
            System.out.println("[Proxy] 代理类 " + proxy.getClass().getName() + " 执行方法完毕: " + methodName + ", 结果: " + result);
            return result;
        } catch (Throwable t) {
            System.out.println("[Proxy] 方法执行出错: " + methodName + ", 原因: " + t.getMessage());
            throw t;
        }
    }

    public String getTargetClassName() {
        return target.getClass().getName();
    }

}


