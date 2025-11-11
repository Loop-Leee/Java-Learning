package com.lloop.springbasic.controller;

import com.lloop.springbasic.service.AspectTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author lloop
 * @Create 2025/1/27
 * AOP 切面功能测试控制器
 * 
 * 测试端点：
 * 1. /aspect-test/normal - 测试正常方法执行（所有通知类型）
 * 2. /aspect-test/add - 测试带参数和返回值的方法
 * 3. /aspect-test/exception - 测试异常通知
 */
@RestController
@RequestMapping("/aspect-test")
public class AspectTestController {

    @Autowired
    private AspectTestService aspectTestService;

    /**
     * 测试正常方法执行
     * 会触发所有类型的通知：Before、Around、After、AfterReturning
     * 测试路径: curl GET http://localhost:8080/aspect-test/normal
     */
    @GetMapping("/normal")
    public Map<String, Object> testNormal() {
        Map<String, Object> result = new HashMap<>();
        result.put("message", "开始测试正常方法执行");
        result.put("instruction", "请查看控制台输出，观察各种通知的执行顺序");
        
        // 调用带有 @LogExecution 注解的方法
        aspectTestService.callNoArgsMethod();
        
        result.put("status", "完成");
        return result;
    }

    /**
     * 测试带参数和返回值的方法
     * 演示切面如何记录方法参数和返回值
     * 测试路径: curl GET "http://localhost:8080/aspect-test/add?a=100&b=200"
     */
    @GetMapping("/add")
    public Map<String, Object> testAdd(
            @RequestParam(defaultValue = "10") int a,
            @RequestParam(defaultValue = "20") int b) {
        Map<String, Object> result = new HashMap<>();
        result.put("message", "测试带参数和返回值的方法");
        result.put("parameters", Map.of("a", a, "b", b));
        
        // 调用带有 @LogExecution 注解的方法
        int sum = aspectTestService.add(a, b);
        
        result.put("result", sum);
        result.put("instruction", "请查看控制台输出，观察参数和返回值的记录");
        return result;
    }

    /**
     * 测试异常通知
     * 演示切面如何处理方法抛出的异常
     * 测试路径: curl GET "http://localhost:8080/aspect-test/exception?message=testInput"
     */
    @GetMapping("/exception")
    public Map<String, Object> testException(
            @RequestParam(required = false) String message) {
        Map<String, Object> result = new HashMap<>();
        result.put("message", "测试异常通知功能");
        
        try {
            // 如果 message 为空，会抛出异常
            String response = aspectTestService.throwException(message);
            result.put("status", "success");
            result.put("response", response);
        } catch (IllegalArgumentException e) {
            result.put("status", "exception_caught");
            result.put("exception", e.getMessage());
            result.put("instruction", "请查看控制台输出，观察异常通知的执行");
        }
        
        return result;
    }

    /**
     * 获取切面功能说明
     */
    @GetMapping("/info")
    public Map<String, Object> getAspectInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("title", "AOP 切面功能说明");
        info.put("description", "本示例演示了 Spring AOP 的核心概念和功能");
        
        Map<String, String> concepts = new HashMap<>();
        concepts.put("Aspect（切面）", "横切关注点的模块化，LoggingAspect 类就是一个切面");
        concepts.put("Join Point（连接点）", "程序执行过程中的某个特定点，如方法调用");
        concepts.put("Pointcut（切点）", "匹配连接点的表达式，定义哪些方法会被拦截");
        concepts.put("Advice（通知）", "在切点上执行的动作");
        concepts.put("Weaving（织入）", "将切面应用到目标对象的过程");
        info.put("核心概念", concepts);
        
        Map<String, String> advices = new HashMap<>();
        advices.put("@Before", "前置通知：在方法执行前执行");
        advices.put("@After", "后置通知：在方法执行后执行（无论成功或失败）");
        advices.put("@AfterReturning", "返回通知：在方法正常返回后执行");
        advices.put("@AfterThrowing", "异常通知：在方法抛出异常后执行");
        advices.put("@Around", "环绕通知：可以完全控制方法的执行");
        info.put("通知类型", advices);
        
        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("/aspect-test/normal", "测试正常方法执行（所有通知类型）");
        endpoints.put("/aspect-test/add?a=10&b=20", "测试带参数和返回值的方法");
        endpoints.put("/aspect-test/exception?message=test", "测试异常通知（正常情况）");
        endpoints.put("/aspect-test/exception", "测试异常通知（抛出异常）");
        endpoints.put("/aspect-test/info", "获取切面功能说明");
        info.put("测试端点", endpoints);
        
        return info;
    }
}

