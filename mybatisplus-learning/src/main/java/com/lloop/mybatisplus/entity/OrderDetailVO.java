package com.lloop.mybatisplus.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单详情视图对象 - 演示自定义映射（ResultMap）
 * 包含订单信息和用户信息
 */
@Data
public class OrderDetailVO {
    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 订单金额
     */
    private BigDecimal amount;

    /**
     * 订单状态
     */
    private Integer status;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}

