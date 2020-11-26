package com.henry.springboot.controller;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/demo")
public class QuickStartController {
    @RequestMapping(path = "/quick", method = RequestMethod.GET)
    public String quickTest(){
        return "springboot访问成功！";
    }
}
