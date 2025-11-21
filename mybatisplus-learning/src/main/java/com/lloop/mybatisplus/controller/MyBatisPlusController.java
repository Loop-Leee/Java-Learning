package com.lloop.mybatisplus.controller;

import com.lloop.mybatisplus.entity.Order;
import com.lloop.mybatisplus.entity.OrderDetailVO;
import com.lloop.mybatisplus.entity.User;
import com.lloop.mybatisplus.service.OrderService;
import com.lloop.mybatisplus.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * MyBatis Plus 使用示例控制器
 * 演示各种使用方式
 */
@RestController
@RequestMapping("/api/mybatisplus")
public class MyBatisPlusController {

    private final UserService userService;

    private final OrderService orderService;

    public MyBatisPlusController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    // ==================== 常规使用方式示例 ====================

    /**
     * 演示常规使用方式：使用 BaseMapper 和 IService 提供的基础方法
     */
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        // 使用 IService 提供的 getById 方法
        return userService.getById(id);
    }

    /**
     * 演示常规使用方式：保存用户
     */
    @PostMapping("/user")
    public boolean saveUser(@RequestBody User user) {
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setDeleted(0);
        // 使用 IService 提供的 save 方法
        return userService.save(user);
    }

    /**
     * 演示常规使用方式：批量保存
     */
    @PostMapping("/user/batch")
    public boolean saveBatchUsers(@RequestBody List<User> users) {
        users.forEach(user -> {
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            user.setDeleted(0);
        });
        // 使用 IService 提供的 saveBatch 方法
        return userService.saveBatch(users);
    }

    /**
     * 演示常规使用方式：更新用户
     */
    @PutMapping("/user")
    public boolean updateUser(@RequestBody User user) {
        user.setUpdateTime(LocalDateTime.now());
        // 使用 IService 提供的 updateById 方法
        return userService.updateById(user);
    }

    /**
     * 演示常规使用方式：删除用户（逻辑删除）
     */
    @DeleteMapping("/user/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        // 使用 IService 提供的 removeById 方法（逻辑删除）
        return userService.removeById(id);
    }

    /**
     * 演示常规使用方式：查询所有用户
     */
    @GetMapping("/user/list")
    public List<User> listUsers() {
        // 使用 IService 提供的 list 方法
        return userService.list();
    }

    // ==================== 自动映射示例 ====================

    /**
     * 演示自动映射：根据用户名查询（字段名自动映射到属性名）
     */
    @GetMapping("/user/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        // MyBatis Plus 会自动将数据库字段（如 create_time）映射到 Java 属性（createTime）
        return userService.findByUsername(username);
    }

    // ==================== XML 实现的 SQL 示例 ====================

    /**
     * 演示 XML 实现的 SQL：根据用户ID查询订单
     */
    @GetMapping("/order/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    // ==================== 动态 SQL 示例 ====================

    /**
     * 演示动态 SQL：根据条件动态查询订单
     */
    @GetMapping("/order/condition")
    public List<Order> getOrdersByCondition(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) BigDecimal minAmount,
            @RequestParam(required = false) BigDecimal maxAmount) {
        return orderService.getOrdersByCondition(userId, status, minAmount, maxAmount);
    }

    /**
     * 演示动态 SQL：根据年龄范围查询用户（注解方式）
     */
    @GetMapping("/user/age")
    public List<User> getUsersByAgeRange(
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge) {
        return userService.findByAgeRange(minAge, maxAge);
    }

    // ==================== 自定义映射示例 ====================

    /**
     * 演示自定义映射（ResultMap）：查询订单详情（包含用户信息）
     */
    @GetMapping("/order/detail/{orderId}")
    public OrderDetailVO getOrderDetail(@PathVariable Long orderId) {
        // 使用自定义的 ResultMap 将订单和用户信息映射到 OrderDetailVO
        return orderService.getOrderDetail(orderId);
    }

    /**
     * 演示批量更新：批量更新订单状态
     */
    @PutMapping("/order/batch-status")
    public boolean updateBatchStatus(@RequestParam List<Long> orderIds, 
                                     @RequestParam Integer status) {
        return orderService.updateBatchStatus(orderIds, status);
    }

    /**
     * 演示保存订单
     */
    @PostMapping("/order")
    public boolean saveOrder(@RequestBody Order order) {
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        return orderService.save(order);
    }
}

