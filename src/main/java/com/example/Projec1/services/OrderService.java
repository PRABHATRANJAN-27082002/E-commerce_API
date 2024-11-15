package com.example.Projec1.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.Projec1.entity.OrderEntity;
import com.example.Projec1.repo.IOrderRepo;
import com.example.Projec1.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService{
    @Autowired
    IOrderRepo orderRepository;

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<OrderEntity> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public OrderEntity createOrder(OrderEntity order) {
        return orderRepository.save(order);
    }

    public OrderEntity updateOrder(Long id, OrderEntity orderDetails) {
        OrderEntity order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        order.setProduct(orderDetails.getProduct());
        order.setQuantity(orderDetails.getQuantity());
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        OrderEntity order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        orderRepository.delete(order);
    }

    @Override
    public String getRoleFromToken(String token) {
        // Use the same secret key that was used to sign the JWT
        String secretKeyString = "My_Secret_Key"; // Replace with your actual secret key
        Algorithm algorithm = Algorithm.HMAC256(secretKeyString);

        // Create JWTVerifier instance using the same algorithm and issuer as in token generation
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("Baeldung")
                .build();

        // Decode and verify the JWT token
        DecodedJWT decodedJWT = verifier.verify(token.replace("Bearer ", ""));

        // Extract the "role" claim from the decoded token
        String role = decodedJWT.getClaim("role").asString();

        return role; // Return the role from the token
    }
}

