package com.henry.springboot.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Date;

@Configuration
public class ConfigDemo {
    @Bean(name = "account1")
    @Scope("prototype")
    public Account createAccount1() {
        return new Account(1, "Henry", 500.5f);
    }

    @Bean(name = "account2")
    @Scope("singleton")
    public Account createAccount2() {
        return new Account(2, "Cindy", 500.5f);
    }
}
