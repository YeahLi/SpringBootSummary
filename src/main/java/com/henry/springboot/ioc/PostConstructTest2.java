package com.henry.springboot.ioc;

import org.springframework.stereotype.Component;

@Component
public class PostConstructTest2 {

    public void hello() {
        System.out.println("hello, i am PostConstructTest2");
    }
}
