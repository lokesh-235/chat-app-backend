package com.example.ChatApplication.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ChatApplication.dtos.responseBodies.UserResponseBody;
import com.example.ChatApplication.services.UserService;
@RestController
@RequestMapping("/apis/users")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/me")
	public UserResponseBody getCurrentUser(Authentication authentication) {
		return userService.getUserByEmail(authentication.getName());
	}

}
