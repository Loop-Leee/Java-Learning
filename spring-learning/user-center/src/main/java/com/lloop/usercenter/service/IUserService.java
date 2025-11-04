package com.lloop.usercenter.service;

import com.lloop.usercenter.domain.User;

public interface IUserService {
    User getUserById(Long id, boolean simulateError);
}


