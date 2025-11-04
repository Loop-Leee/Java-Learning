package com.lloop.usercenter.service.impl;

import com.lloop.usercenter.domain.User;
import com.lloop.usercenter.mapper.UserMapper;
import com.lloop.usercenter.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SimpleUserService implements IUserService {
    private static final Logger log = LoggerFactory.getLogger(SimpleUserService.class);

    private final UserMapper userMapper;

    public SimpleUserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getUserById(Long id, boolean simulateError) {
        log.info("[UserService] 开始查询用户, id={}, simulateError={}", id, simulateError);

        if (simulateError) {
            IllegalStateException ex = new IllegalStateException("模拟的业务异常: 参数 simulateError=true");
            log.error("[UserService] 业务处理失败, id={}", id, ex);
            throw ex;
        }

        User user = userMapper.findById(id);
        if (user == null) {
            log.warn("[UserService] 未找到用户, id={}", id);
            return null;
        }

        log.info("[UserService] 查询成功, id={}, username={}", user.getId(), user.getUsername());
        return user;
    }
}


