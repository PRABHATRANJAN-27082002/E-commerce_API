package com.example.Projec1.services;


import com.example.Projec1.dao.OrderResponse;
import com.example.Projec1.dao.UserResponse;
import com.example.Projec1.entity.OrderEntity;
import com.example.Projec1.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface IUserServices {
    UserResponse register(UserEntity body);
    UserResponse login(UserEntity body);
}

