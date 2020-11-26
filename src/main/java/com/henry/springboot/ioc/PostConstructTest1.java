package com.henry.springboot.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PostConstructTest1 {

    @Autowired
    PostConstructTest2 postConstructTest2;

    public PostConstructTest1() {
//        postConstructTest2.hello();
    }

    @PostConstruct
    public void init() {
        postConstructTest2.hello();
    }
}



