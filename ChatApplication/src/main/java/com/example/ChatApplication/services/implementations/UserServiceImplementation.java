package com.example.ChatApplication.services.implementations;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ChatApplication.dtos.requestBodies.UserRequestBody;
import com.example.ChatApplication.dtos.responseBodies.UserResponseBody;
import com.example.ChatApplication.entities.User;
import com.example.ChatApplication.exceptions.EmailAlreadyExistsException;
import com.example.ChatApplication.exceptions.InvalidDetailsException;
import com.example.ChatApplication.exceptions.UsernameAlreadyExistsException;
import com.example.ChatApplication.mappers.UserMapper;
import com.example.ChatApplication.repositories.UserRepository;
import com.example.ChatApplication.services.UserService;


@Service
public class UserServiceImplementation implements UserService {
	
	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public UserServiceImplementation(UserRepository userRepository , BCryptPasswordEncoder bCryptPasswordEncoder) {
		// TODO Auto-generated constructor stub
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public String registerUser(UserRequestBody userRequestBody)  {
		// TODO Auto-generated method stub
		
	    userRepository.findByUsername(userRequestBody.getUsername())
	            .ifPresent(user -> {
	                throw new UsernameAlreadyExistsException("Username already exists");
	            });

	    
	    userRepository.findByEmail(userRequestBody.getEmail())
	            .ifPresent(user -> {
	                throw new EmailAlreadyExistsException("Email already exists");
	            });

		
		User user = UserMapper.toEntity(userRequestBody);
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		userRepository.save(user);
		
		return "User registered Successfully";
	}

	@Override
	public UserResponseBody getUserByEmail(String email) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(email).orElseThrow(()->new InvalidDetailsException("Email does not exists"));
		
		return UserMapper.toDto(user);
	}

}
