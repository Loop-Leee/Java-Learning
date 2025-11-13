package com.lloop.springbasic.proxy;

public interface HelloService {

    String sayHello(String name);

    default String getClassName() {
        return this.getClass().getName();
    }

}


