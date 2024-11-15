package com.example.Projec1.services;

import com.example.Projec1.dao.OrderResponse;
import com.example.Projec1.dao.UserResponse;
import com.example.Projec1.entity.OrderEntity;
import com.example.Projec1.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface IOrderService {


    List<OrderEntity> getAllOrders();

    Optional<OrderEntity> getOrderById(Long id);

    OrderEntity createOrder(OrderEntity order);

    OrderEntity updateOrder(Long id, OrderEntity orderDetails);

    void deleteOrder(Long id);

    String getRoleFromToken(String token);

}