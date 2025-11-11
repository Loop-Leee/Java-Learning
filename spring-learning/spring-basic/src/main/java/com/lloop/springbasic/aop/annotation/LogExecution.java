package com.lloop.springbasic.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author lloop
 * @Create 2025/1/27
 * 自定义注解：用于标记需要记录日志和执行时间的方法
 * 
 * 切面核心概念：
 * - @Target(ElementType.METHOD): 表示该注解只能用于方法上
 * - @Retention(RetentionPolicy.RUNTIME): 表示该注解在运行时保留，可以通过反射获取
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecution {
    /**
     * 日志描述信息
     */
    String value() default "";
    
    /**
     * 是否记录方法执行时间
     */
    boolean logTime() default true;
    
    /**
     * 是否记录方法参数
     */
    boolean logArgs() default true;
    
    /**
     * 是否记录方法返回值
     */
    boolean logResult() default true;
}

    