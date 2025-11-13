package com.lloop.springbasic.proxy;

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        if (name == null || name.isBlank()) {
            name = "World";
        }
        return "Hello, " + name + "!";
    }

}


