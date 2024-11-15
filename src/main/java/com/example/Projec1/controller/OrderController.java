package com.example.Projec1.controller;

import com.example.Projec1.entity.OrderEntity;
import com.example.Projec1.services.IOrderService;
import com.example.Projec1.util.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @GetMapping
    public List<OrderEntity> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> getOrderById(@RequestHeader("Authorization") String token,@PathVariable Long id) {
        String role = orderService.getRoleFromToken(token);
        if(role.equals("admin")){
            OrderEntity order = orderService.getOrderById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
            return ResponseEntity.ok(order);
        }
        return null;
    }

    @PostMapping("/")
    public OrderEntity createOrder(@RequestHeader("Authorization") String token,@Valid @RequestBody OrderEntity order) {
        String role = orderService.getRoleFromToken(token);
        if(role.equals("admin")){
            return orderService.createOrder(order);
        }
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderEntity> updateOrder(@RequestHeader("Authorization") String token,@PathVariable Long id, @Valid @RequestBody OrderEntity orderDetails) {
        String role = orderService.getRoleFromToken(token);
        OrderEntity updatedOrder = orderService.updateOrder(id, orderDetails);
        if(role.equals("admin")){
            return ResponseEntity.ok(updatedOrder);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@RequestHeader("Authorization") String token,@PathVariable Long id) {
        String role = orderService.getRoleFromToken(token);
        if(role.equals("admin")){
            orderService.deleteOrder(id);
            return ResponseEntity.noContent().build();
        }
        return null;
    }
}