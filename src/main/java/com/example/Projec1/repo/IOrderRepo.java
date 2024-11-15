package com.example.Projec1.repo;

import com.example.Projec1.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IOrderRepo extends JpaRepository<OrderEntity, Long> {

}
