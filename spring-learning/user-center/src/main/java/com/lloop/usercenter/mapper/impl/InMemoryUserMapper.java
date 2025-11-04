package com.lloop.usercenter.mapper.impl;

import com.lloop.usercenter.domain.User;
import com.lloop.usercenter.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryUserMapper implements UserMapper {
    private final Map<Long, User> idToUser = new HashMap<>();

    public InMemoryUserMapper() {
        idToUser.put(1L, new User(1L, "alice"));
        idToUser.put(2L, new User(2L, "bob"));
        idToUser.put(3L, new User(3L, "charlie"));
    }

    @Override
    public User findById(Long id) {
        return idToUser.get(id);
    }
}


