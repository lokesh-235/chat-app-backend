package com.example.ChatApplication.mappers;


import com.example.ChatApplication.dtos.requestBodies.UserRequestBody;
import com.example.ChatApplication.dtos.responseBodies.UserResponseBody;
import com.example.ChatApplication.entities.User;
import com.example.ChatApplication.enums.UserStatus;

import java.time.LocalDateTime;

public class UserMapper {

    public static User toEntity(UserRequestBody request) {
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword()) // later hash this!
                .status(UserStatus.OFFLINE) // default value
                .createdAt(LocalDateTime.now())
                .build();
    }
    
    public static UserResponseBody toDto(User user) {
    	return UserResponseBody.builder()
    			.id(user.getId())
    			.email(user.getEmail())
    			.username(user.getUsername())
    			.build();
    }
}
