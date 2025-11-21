package com.lloop.mybatisplus.service.impl;

import com.lloop.mybatisplus.entity.User;
import com.lloop.mybatisplus.mapper.UserMapper;
import com.lloop.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务实现类
 * 演示常规使用方式：继承 ServiceImpl 获得基础服务能力
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User findByUsername(String username) {
        return baseMapper.findByUsername(username);
    }

    @Override
    public List<User> findByAgeRange(Integer minAge, Integer maxAge) {
        return baseMapper.findByAgeRange(minAge, maxAge);
    }

    @Override
    public boolean saveBatch(List<User> users) {
        // 使用 MyBatis Plus 提供的批量保存方法
        return super.saveBatch(users);
    }
}

