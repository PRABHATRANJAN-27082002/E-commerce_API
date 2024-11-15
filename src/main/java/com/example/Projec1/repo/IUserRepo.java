package com.example.Projec1.repo;

//import com.example.Projec1.entity.NoteEntity;
import com.example.Projec1.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepo extends JpaRepository<UserEntity, Long> {

    // Custom method to find a user by email
    Optional<UserEntity> findByEmail(String email);

}