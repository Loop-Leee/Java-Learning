package com.lloop.springbasic.aop.aspect;

import com.lloop.springbasic.aop.annotation.LogExecution;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author lloop
 * @Create 2025/1/27
 * 切面类：实现日志记录和性能监控功能
 * 
 * 切面核心概念：
 * 1. Aspect（切面）：横切关注点的模块化，本类就是一个切面
 * 2. Join Point（连接点）：程序执行过程中的某个特定点，如方法调用、异常抛出等
 * 3. Pointcut（切点）：匹配连接点的表达式，定义哪些方法会被拦截
 * 4. Advice（通知）：在切点上执行的动作，包括：
 *    - @Before: 前置通知，在方法执行前执行
 *    - @After: 后置通知，在方法执行后执行（无论成功或失败）
 *    - @AfterReturning: 返回通知，在方法正常返回后执行
 *    - @AfterThrowing: 异常通知，在方法抛出异常后执行
 *    - @Around: 环绕通知，可以完全控制方法的执行
 * 5. Weaving（织入）：将切面应用到目标对象的过程
 */
@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * 切点定义：匹配所有使用 @LogExecution 注解的方法
     * 
     * 切点表达式说明：
     * - @annotation(com.lloop.springbasic.annotation.LogExecution): 
     *   匹配所有标注了 @LogExecution 注解的方法
     */
    @Pointcut("@annotation(com.lloop.springbasic.aop.annotation.LogExecution)")
    public void logExecutionPointcut() {
        // 切点方法体为空，仅作为切点定义的标识
    }

    /**
     * 前置通知（Before Advice）
     * 在目标方法执行之前执行
     * 
     * 应用场景：记录方法开始执行、参数验证、权限检查等
     */
    @Before("logExecutionPointcut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogExecution logExecution = method.getAnnotation(LogExecution.class);
        
        String methodName = signature.toShortString();
        String description = logExecution.value().isEmpty() ? methodName : logExecution.value();
        
        logger.info("========== [前置通知] 方法开始执行 ==========");
        logger.info("方法: {}", methodName);
        logger.info("描述: {}", description);
        
        if (logExecution.logArgs()) {
            Object[] args = joinPoint.getArgs();
            logger.info("参数: {}", Arrays.toString(args));
        }
    }

    /**
     * 后置通知（After Advice）
     * 在目标方法执行之后执行（无论方法是否成功返回或抛出异常）
     * 
     * 应用场景：资源清理、状态重置等
     */
    @After("logExecutionPointcut()")
    public void afterAdvice(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        logger.info("========== [后置通知] 方法执行完成 ==========");
        logger.info("方法: {}", methodName);
    }

    /**
     * 返回通知（AfterReturning Advice）
     * 在目标方法正常返回后执行
     * 
     * 应用场景：记录返回值、处理返回结果等
     */
    @AfterReturning(pointcut = "logExecutionPointcut()", returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogExecution logExecution = method.getAnnotation(LogExecution.class);
        
        String methodName = signature.toShortString();
        logger.info("========== [返回通知] 方法正常返回 ==========");
        logger.info("方法: {}", methodName);
        
        if (logExecution.logResult() && result != null) {
            logger.info("返回值: {}", result);
        }
    }

    /**
     * 异常通知（AfterThrowing Advice）
     * 在目标方法抛出异常后执行
     * 
     * 应用场景：异常日志记录、异常处理、告警等
     */
    @AfterThrowing(pointcut = "logExecutionPointcut()", throwing = "exception")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().toShortString();
        logger.error("========== [异常通知] 方法执行异常 ==========");
        logger.error("方法: {}", methodName);
        logger.error("异常类型: {}", exception.getClass().getName());
        logger.error("异常信息: {}", exception.getMessage());
        logger.error("异常堆栈: ", exception);
    }

    /**
     * 环绕通知（Around Advice）
     * 可以完全控制方法的执行，包括决定是否执行目标方法、修改参数、修改返回值等
     * 
     * 应用场景：性能监控、事务管理、缓存处理、权限控制等
     * 
     * 这是最强大的通知类型，可以替代其他所有通知类型
     */
    @Around("logExecutionPointcut()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogExecution logExecution = method.getAnnotation(LogExecution.class);
        
        String methodName = signature.toShortString();
        String description = logExecution.value().isEmpty() ? methodName : logExecution.value();
        
        long startTime = System.currentTimeMillis();
        Object result = null;
        Throwable exception = null;
        
        try {
            logger.info("========== [环绕通知-开始] ==========");
            logger.info("方法: {}", methodName);
            logger.info("描述: {}", description);
            
            // 执行目标方法
            result = joinPoint.proceed();
            
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            
            if (logExecution.logTime()) {
                logger.info("执行时间: {} ms", executionTime);
            }
            
            logger.info("========== [环绕通知-结束] ==========");
            
            return result;
            
        } catch (Throwable e) {
            exception = e;
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            
            logger.error("========== [环绕通知-异常] ==========");
            logger.error("方法: {}", methodName);
            logger.error("执行时间: {} ms (异常中断)", executionTime);
            logger.error("异常: {}", e.getMessage());
            
            // 重新抛出异常，让调用者知道发生了异常
            throw e;
        }
    }
}

