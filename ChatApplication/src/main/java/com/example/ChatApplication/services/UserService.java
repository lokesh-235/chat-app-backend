package com.example.ChatApplication.services;

import com.example.ChatApplication.dtos.requestBodies.UserRequestBody;
import com.example.ChatApplication.dtos.responseBodies.UserResponseBody;

public interface UserService {
	String registerUser(UserRequestBody userRequestBody) ;
	
	UserResponseBody getUserByEmail(String email);
}
