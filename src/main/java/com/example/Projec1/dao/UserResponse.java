package com.example.Projec1.dao;


import com.example.Projec1.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Setter
@Getter
public class UserResponse {
    private HttpStatus code;
    private UserEntity data;
    private String message;

    public UserResponse(HttpStatus code, UserEntity data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }


    @Override
    public String toString() {
        return "DemoResponse{" +
                "code=" + code +
                ", data=" + data +
                ", message=" + message +
                '}';
    }
}
