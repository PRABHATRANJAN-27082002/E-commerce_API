package com.example.Projec1.controller;

import com.example.Projec1.dao.UserResponse;
import com.example.Projec1.entity.UserEntity;
import com.example.Projec1.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    IUserServices demoService;

    @PostMapping("/")
    public UserResponse register(@RequestBody UserEntity body) {
        System.out.println("In Controller");
        UserResponse res = demoService.register(body);
        return res;
    }


    @PostMapping("/login")
    public UserResponse login(@RequestBody UserEntity body) {
        return demoService.login(body);
    }



}

