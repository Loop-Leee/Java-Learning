package com.lloop.usercenter.controller;

import com.lloop.usercenter.domain.User;
import com.lloop.usercenter.service.IUserService;
import com.lloop.usercenter.service.impl.SimpleUserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(SimpleUserService.class);

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 查询用户
     * curl "http://localhost:8080/api/v1/users/1"
     * curl "http://localhost:8080/api/v1/users/1"
     * curl "http://localhost:8080/api/v1/users/1?simulateError=true"
     * @param id
     * @param simulateError
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id,
                                     @RequestParam(value = "simulateError", required = false, defaultValue = "false") boolean simulateError) {
        log.info("[UserController] 收到查询请求, id={}, simulateError={}", id, simulateError);
        try {
            User user = userService.getUserById(id, simulateError);
            if (user == null) {
                log.warn("[UserController] 用户不存在, id={}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
            }
            log.info("[UserController] 请求成功, id={}", id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            log.error("[UserController] 请求失败, id={}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("internal error");
        }
    }
}


