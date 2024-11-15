package com.example.Projec1.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.Projec1.entity.UserEntity;

import java.util.Date;
import java.util.UUID;

public class AuthMiddleware {

//    public static String getToken(UserEntity body){
    public static String getToken(String role){

        Algorithm algorithm = Algorithm.HMAC256("My_Secret_Key");
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("Baeldung")
                .build();
        String token = JWT.create()
                .withIssuer("Baeldung")
//                .withClaim("role", body.getRole())
                .withClaim("role", role)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30000000L))
                .withJWTId(String.valueOf(UUID.randomUUID()))
                .withNotBefore(new Date(System.currentTimeMillis() + 1000L))
                .sign(algorithm);

        return token;
    }
}

