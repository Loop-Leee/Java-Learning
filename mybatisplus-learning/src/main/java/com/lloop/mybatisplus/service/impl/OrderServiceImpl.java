package com.lloop.mybatisplus.service.impl;

import com.lloop.mybatisplus.entity.Order;
import com.lloop.mybatisplus.entity.OrderDetailVO;
import com.lloop.mybatisplus.mapper.OrderMapper;
import com.lloop.mybatisplus.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单服务实现类
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public List<Order> getOrdersByCondition(Long userId, Integer status, 
                                            BigDecimal minAmount, BigDecimal maxAmount) {
        return baseMapper.selectByCondition(userId, status, minAmount, maxAmount);
    }

    @Override
    public OrderDetailVO getOrderDetail(Long orderId) {
        return baseMapper.selectOrderDetail(orderId);
    }

    @Override
    public boolean updateBatchStatus(List<Long> orderIds, Integer status) {
        int result = baseMapper.updateBatchStatus(orderIds, status);
        return result > 0;
    }
}

