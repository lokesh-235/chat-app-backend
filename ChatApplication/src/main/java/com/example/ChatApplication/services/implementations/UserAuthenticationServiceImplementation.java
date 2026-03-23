package com.example.ChatApplication.services.implementations;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.ChatApplication.dtos.requestBodies.AuthRequestBody;
import com.example.ChatApplication.security.JwtService;
import com.example.ChatApplication.services.UserAuthenticationService;

@Service
public class UserAuthenticationServiceImplementation implements UserAuthenticationService {
	
	private AuthenticationManager authenticationManager;
	private JwtService jwtService;
	
	
	public UserAuthenticationServiceImplementation(
			AuthenticationManager authenticationManager
			,JwtService jwtService
			) {
		// TODO Auto-generated constructor stub
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}

	@Override
	public String authenticateUser(AuthRequestBody authRequestBody) {
		// TODO Auto-generated method stub
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestBody.getEmail(),authRequestBody.getPassword()));
		
		if(authentication.isAuthenticated())
			return jwtService.generateToken(authRequestBody.getEmail());
		
		
		throw new RuntimeException("Invalid Details") ;
	}
	
	
	
}
