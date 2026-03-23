package com.example.ChatApplication.controllers;

import org.springframework.http.ResponseCookie;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ChatApplication.dtos.requestBodies.AuthRequestBody;
import com.example.ChatApplication.exceptions.InvalidDetailsException;
import com.example.ChatApplication.services.UserAuthenticationService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/apis/users")
public class UserAuthenticationController {
	
	private UserAuthenticationService authenticationService;
	
	public UserAuthenticationController(UserAuthenticationService authenticationService) {
		// TODO Auto-generated constructor stub
		this.authenticationService = authenticationService;
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<String> login(
			@RequestBody AuthRequestBody authRequestBody
			,HttpServletResponse response
			){
		
		// ✅ get JWT
        String token = authenticationService.authenticateUser(authRequestBody);

        // ✅ set cookie
        ResponseCookie cookie = ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .secure(false) // true if HTTPS
                .path("/")
                .maxAge(24 * 60 * 60)
                .sameSite("Lax")
                .build();

        response.setHeader("Set-Cookie", cookie.toString());

        // ✅ send success response
        return ResponseEntity.ok("Login Successful");
		
	}
	
	@ExceptionHandler({
	    InvalidDetailsException.class
	})
	public String invalidDetailsException(RuntimeException exception) {
		return exception.getMessage();
	}
}
