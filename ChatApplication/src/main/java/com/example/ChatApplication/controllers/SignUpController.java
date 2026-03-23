package com.example.ChatApplication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ChatApplication.dtos.requestBodies.UserRequestBody;
import com.example.ChatApplication.exceptions.EmailAlreadyExistsException;
import com.example.ChatApplication.exceptions.UsernameAlreadyExistsException;
import com.example.ChatApplication.services.UserService;

@RequestMapping("/apis/users")
@RestController
public class SignUpController {
	
	private UserService userService;
	
	public SignUpController(UserService userService) {
		// TODO Auto-generated constructor stub
		this.userService = userService;
	}
	
	@PostMapping("/signup")
	
	public ResponseEntity<String> sigup(@RequestBody UserRequestBody userRequestBody) {
		
		return ResponseEntity.ok(userService.registerUser(userRequestBody));
	}
	
	
	@ExceptionHandler({
	    UsernameAlreadyExistsException.class,
	    EmailAlreadyExistsException.class
	})
	public String usernameAlreadyExistsException(RuntimeException exception) {
		return exception.getMessage();
	}
	
}
