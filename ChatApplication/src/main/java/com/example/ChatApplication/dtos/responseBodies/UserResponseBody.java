package com.example.ChatApplication.dtos.responseBodies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseBody {
	private Long id;
	private String username;
	private String email;
}
