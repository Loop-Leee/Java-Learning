package com.lloop.mybatisplus.service;

import com.lloop.mybatisplus.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户服务接口
 * 演示常规使用方式：继承 IService 获得增强的 CRUD 能力
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名查询用户
     */
    User findByUsername(String username);

    /**
     * 根据年龄范围查询用户
     */
    List<User> findByAgeRange(Integer minAge, Integer maxAge);

    /**
     * 批量保存用户
     */
    boolean saveBatch(List<User> users);
}

