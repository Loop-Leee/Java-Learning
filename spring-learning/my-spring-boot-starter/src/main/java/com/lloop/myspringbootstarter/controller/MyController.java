package com.lloop.myspringbootstarter.controller;

import com.lloop.myspringbootstarter.service.impl.MyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lloop
 * @Create 2025/11/14 12:06
 */
@RestController
public class MyController {

    private final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/name")
    public String getName() {
        return myService.getName();
    }

}
