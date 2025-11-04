package com.lloop.usercenter;

import com.lloop.usercenter.service.IUserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserCenterApplicationTests {

    @Resource
    private IUserService userService;

    @Test
    void getUser() {
        userService.getUserById(1L, false);
        userService.getUserById(10L, false);
        try {
            userService.getUserById(1L, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
