package com.example.ChatApplication.dtos.requestBodies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestBody {
	private String username;
	private String email;
	private String password;
}
