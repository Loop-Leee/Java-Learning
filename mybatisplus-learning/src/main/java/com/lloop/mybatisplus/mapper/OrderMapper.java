package com.lloop.mybatisplus.mapper;

import com.lloop.mybatisplus.entity.Order;
import com.lloop.mybatisplus.entity.OrderDetailVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单 Mapper 接口
 * 演示 XML 实现的 SQL、动态 SQL 和自定义映射
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 根据用户ID查询订单列表（XML 方式）
     * 对应 OrderMapper.xml 中的 selectByUserId 方法
     */
    List<Order> selectByUserId(@Param("userId") Long userId);

    /**
     * 动态查询订单（XML 方式，演示动态 SQL）
     * 对应 OrderMapper.xml 中的 selectByCondition 方法
     */
    List<Order> selectByCondition(@Param("userId") Long userId,
                                  @Param("status") Integer status,
                                  @Param("minAmount") BigDecimal minAmount,
                                  @Param("maxAmount") BigDecimal maxAmount);

    /**
     * 查询订单详情（包含用户信息，演示自定义映射 ResultMap）
     * 对应 OrderMapper.xml 中的 selectOrderDetail 方法
     */
    OrderDetailVO selectOrderDetail(@Param("orderId") Long orderId);

    /**
     * 批量更新订单状态（XML 方式，演示动态 SQL）
     * 对应 OrderMapper.xml 中的 updateBatchStatus 方法
     */
    int updateBatchStatus(@Param("orderIds") List<Long> orderIds, @Param("status") Integer status);
}

