package com.example.ChatApplication.services;

import com.example.ChatApplication.dtos.requestBodies.AuthRequestBody;

public interface UserAuthenticationService {
	
	String authenticateUser(AuthRequestBody authRequestBody);
	
}
