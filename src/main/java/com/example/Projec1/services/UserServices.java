package com.example.Projec1.services;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.Projec1.dao.OrderResponse;
import com.example.Projec1.dao.UserResponse;
import com.example.Projec1.entity.OrderEntity;
import com.example.Projec1.entity.UserEntity;
import com.example.Projec1.repo.IOrderRepo;
import com.example.Projec1.repo.IUserRepo;
import com.example.Projec1.util.AuthMiddleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices implements IUserServices {
        String token = null;

    @Autowired
    IUserRepo demoRepo;



    @Override
    public UserResponse register(UserEntity body) {

        Optional<UserEntity> isRegistered = demoRepo.findByEmail(body.getEmail());

        UserEntity user = null;
        if(isRegistered.isEmpty()){
            String hashpw = BCrypt.hashpw(body.getPassword(), BCrypt.gensalt());
            body.setPassword(hashpw);
//            token = AuthMiddleware.getToken();
//            body.setAuth(token);
            user = demoRepo.save(body);
        }else{
            return new UserResponse(HttpStatus.BAD_REQUEST, null, body.getEmail()+" is already registered");
        }

        return new UserResponse(HttpStatus.CREATED, user, "User Registered Successfully");
    }

    @Override
    public UserResponse login(UserEntity body){

        Optional<UserEntity> isEmailPresent = demoRepo.findByEmail(body.getEmail());
        System.out.println(isEmailPresent);


        if(isEmailPresent.isEmpty()){
            return new UserResponse(HttpStatus.BAD_REQUEST, null, body.getEmail()+" is not registered.");
        }

        String passwordFromDb = isEmailPresent.get().getPassword();
        boolean checkpw = BCrypt.checkpw(body.getPassword(), passwordFromDb);
        if(checkpw) {
            String role = isEmailPresent.map(UserEntity::getRole).orElse("Role not found");;
//            token = AuthMiddleware.getToken(body);
            token = AuthMiddleware.getToken(role);
            return new UserResponse(HttpStatus.OK, body, "User Logged in successful : "+token);
        }
        return new UserResponse(HttpStatus.BAD_REQUEST, null, "Please enter valid password");
    }

}


