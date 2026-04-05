package com.example.ChatApplication.dtos.requestBodies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomCreationRequestBody {
	private String roomName;
	

}
