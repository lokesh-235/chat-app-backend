package com.example.ChatApplication.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ChatApplication.entities.User;
import com.example.ChatApplication.exceptions.InvalidDetailsException;
import com.example.ChatApplication.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements  UserDetailsService{
	
	private UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		// TODO Auto-generated constructor stub
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(email).orElseThrow(()->new InvalidDetailsException("Invalid User Details "));
		return new UserDetailsImpl(user);
	}

}
