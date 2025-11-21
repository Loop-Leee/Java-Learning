package com.lloop.mybatisplus.service;

import com.lloop.mybatisplus.entity.Order;
import com.lloop.mybatisplus.entity.OrderDetailVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单服务接口
 * 演示 XML SQL、动态 SQL 和自定义映射的使用
 */
public interface OrderService extends IService<Order> {

    /**
     * 根据用户ID查询订单列表
     */
    List<Order> getOrdersByUserId(Long userId);

    /**
     * 动态查询订单
     */
    List<Order> getOrdersByCondition(Long userId, Integer status, 
                                     BigDecimal minAmount, BigDecimal maxAmount);

    /**
     * 查询订单详情（包含用户信息）
     */
    OrderDetailVO getOrderDetail(Long orderId);

    /**
     * 批量更新订单状态
     */
    boolean updateBatchStatus(List<Long> orderIds, Integer status);
}

