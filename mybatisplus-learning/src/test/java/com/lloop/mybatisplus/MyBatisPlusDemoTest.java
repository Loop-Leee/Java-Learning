package com.lloop.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lloop.mybatisplus.entity.Order;
import com.lloop.mybatisplus.entity.OrderDetailVO;
import com.lloop.mybatisplus.entity.User;
import com.lloop.mybatisplus.service.OrderService;
import com.lloop.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * MyBatis Plus 使用示例测试类
 */
@SpringBootTest
public class MyBatisPlusDemoTest {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    // ==================== 常规使用方式测试 ====================

    /**
     * 测试常规使用方式：基础 CRUD 操作
     */
    @Test
    public void testBasicCRUD() {
        System.out.println("========== 常规使用方式：基础 CRUD ==========");

        Random random = new Random();
        // 1. 保存用户
        User user = new User();
        user.setUsername("test_user_" + random.nextInt(10000));
        user.setEmail("test@example.com");
        user.setAge(25);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setDeleted(0);
        boolean saved = userService.save(user);
        System.out.println("保存用户结果: " + saved);
        System.out.println("保存后的用户ID: " + user.getId());

        // 2. 根据ID查询
        User foundUser = userService.getById(user.getId());
        System.out.println("查询到的用户: " + foundUser);

        // 3. 更新用户
        foundUser.setAge(26);
        foundUser.setUpdateTime(LocalDateTime.now());
        boolean updated = userService.updateById(foundUser);
        System.out.println("更新用户结果: " + updated);

        // 4. 查询所有用户
        List<User> allUsers = userService.list();
        System.out.println("所有用户数量: " + allUsers.size());

        // 5. 逻辑删除（如果配置了逻辑删除）
        // userService.removeById(user.getId());
    }

    /**
     * 测试批量操作
     */
    @Test
    public void testBatchOperations() {
        System.out.println("========== 常规使用方式：批量操作 ==========");

        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            User user = new User();
            user.setUsername("batch_user_" + i);
            user.setEmail("batch" + i + "@example.com");
            user.setAge(20 + i);
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            user.setDeleted(0);
            users.add(user);
        }

        // 批量保存
        boolean saved = userService.saveBatch(users);
        System.out.println("批量保存结果: " + saved);
        System.out.println("保存的用户数量: " + users.size());
    }

    // ==================== 自动映射测试 ====================

    /**
     * 测试自动映射：字段名自动映射到属性名
     */
    @Test
    public void testAutoMapping() {
        System.out.println("========== 自动映射示例 ==========");

        // 创建用户
        User user = new User();
        user.setUsername("auto_mapping_user");
        user.setEmail("auto@example.com");
        user.setAge(30);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setDeleted(0);
        userService.save(user);

        // 根据用户名查询（自动映射 create_time -> createTime）
        User foundUser = userService.findByUsername("auto_mapping_user");
        System.out.println("查询到的用户: " + foundUser);
        System.out.println("创建时间（自动映射）: " + foundUser.getCreateTime());
    }

    // ==================== XML 实现的 SQL 测试 ====================

    /**
     * 测试 XML 实现的 SQL
     */
    @Test
    public void testXmlSQL() {
        System.out.println("========== XML 实现的 SQL 示例 ==========");

        // 先创建用户
        User user = new User();
        user.setUsername("xml_user");
        user.setEmail("xml@example.com");
        user.setAge(28);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setDeleted(0);
        userService.save(user);

        // 创建订单
        Order order1 = new Order();
        order1.setUserId(user.getId());
        order1.setOrderNo("ORD001");
        order1.setAmount(new BigDecimal("100.00"));
        order1.setStatus(0);
        order1.setCreateTime(LocalDateTime.now());
        order1.setUpdateTime(LocalDateTime.now());
        orderService.save(order1);

        Order order2 = new Order();
        order2.setUserId(user.getId());
        order2.setOrderNo("ORD002");
        order2.setAmount(new BigDecimal("200.00"));
        order2.setStatus(1);
        order2.setCreateTime(LocalDateTime.now());
        order2.setUpdateTime(LocalDateTime.now());
        orderService.save(order2);

        // 使用 XML 实现的 SQL 查询订单
        List<Order> orders = orderService.getOrdersByUserId(user.getId());
        System.out.println("用户 " + user.getId() + " 的订单数量: " + orders.size());
        orders.forEach(order -> {
            System.out.println("订单: " + order.getOrderNo() + ", 金额: " + order.getAmount());
        });
    }

    // ==================== 动态 SQL 测试 ====================

    /**
     * 测试动态 SQL
     */
    @Test
    public void testDynamicSQL() {
        System.out.println("========== 动态 SQL 示例 ==========");

        // 创建测试数据
        User user = new User();
        user.setUsername("dynamic_user");
        user.setEmail("dynamic@example.com");
        user.setAge(25);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setDeleted(0);
        userService.save(user);

        // 创建不同状态的订单
        for (int i = 0; i < 3; i++) {
            Order order = new Order();
            order.setUserId(user.getId());
            order.setOrderNo("DYN" + i);
            order.setAmount(new BigDecimal((i + 1) * 100));
            order.setStatus(i);
            order.setCreateTime(LocalDateTime.now());
            order.setUpdateTime(LocalDateTime.now());
            orderService.save(order);
        }

        // 测试1：只根据用户ID查询
        System.out.println("\n--- 测试1：只根据用户ID查询 ---");
        List<Order> orders1 = orderService.getOrdersByCondition(user.getId(), null, null, null);
        System.out.println("查询结果数量: " + orders1.size());

        // 测试2：根据用户ID和状态查询
        System.out.println("\n--- 测试2：根据用户ID和状态查询 ---");
        List<Order> orders2 = orderService.getOrdersByCondition(user.getId(), 1, null, null);
        System.out.println("查询结果数量: " + orders2.size());

        // 测试3：根据金额范围查询
        System.out.println("\n--- 测试3：根据金额范围查询 ---");
        List<Order> orders3 = orderService.getOrdersByCondition(
                user.getId(), null, new BigDecimal("150"), new BigDecimal("250"));
        System.out.println("查询结果数量: " + orders3.size());

        // 测试4：根据年龄范围查询用户（注解方式的动态SQL）
        System.out.println("\n--- 测试4：根据年龄范围查询用户 ---");
        List<User> users = userService.findByAgeRange(20, 30);
        System.out.println("年龄在 20-30 之间的用户数量: " + users.size());
    }

    // ==================== 自定义映射测试 ====================

    /**
     * 测试自定义映射（ResultMap）
     */
    @Test
    public void testCustomMapping() {
        System.out.println("========== 自定义映射（ResultMap）示例 ==========");

        // 创建用户
        User user = new User();
        user.setUsername("custom_mapping_user");
        user.setEmail("custom@example.com");
        user.setAge(30);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setDeleted(0);
        userService.save(user);

        // 创建订单
        Order order = new Order();
        order.setUserId(user.getId());
        order.setOrderNo("CUSTOM001");
        order.setAmount(new BigDecimal("500.00"));
        order.setStatus(1);
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        orderService.save(order);

        // 使用自定义映射查询订单详情（包含用户信息）
        OrderDetailVO orderDetail = orderService.getOrderDetail(order.getId());
        System.out.println("订单详情:");
        System.out.println("  订单ID: " + orderDetail.getOrderId());
        System.out.println("  订单号: " + orderDetail.getOrderNo());
        System.out.println("  订单金额: " + orderDetail.getAmount());
        System.out.println("  用户ID: " + orderDetail.getUserId());
        System.out.println("  用户名: " + orderDetail.getUsername());
        System.out.println("  用户邮箱: " + orderDetail.getEmail());
    }

    /**
     * 测试批量更新
     */
    @Test
    public void testBatchUpdate() {
        System.out.println("========== 批量更新示例 ==========");

        // 创建用户和订单
        User user = new User();
        user.setUsername("batch_update_user");
        user.setEmail("batch@example.com");
        user.setAge(25);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setDeleted(0);
        userService.save(user);

        List<Long> orderIds = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Order order = new Order();
            order.setUserId(user.getId());
            order.setOrderNo("BATCH" + i);
            order.setAmount(new BigDecimal((i + 1) * 50));
            order.setStatus(0);
            order.setCreateTime(LocalDateTime.now());
            order.setUpdateTime(LocalDateTime.now());
            orderService.save(order);
            orderIds.add(order.getId());
        }

        // 批量更新订单状态
        boolean updated = orderService.updateBatchStatus(orderIds, 1);
        System.out.println("批量更新结果: " + updated);
        System.out.println("更新的订单数量: " + orderIds.size());
    }
}

