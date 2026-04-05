package com.example.ChatApplication.mappers;

import java.time.LocalDateTime;

import com.example.ChatApplication.dtos.requestBodies.RoomCreationRequestBody;
import com.example.ChatApplication.entities.Room;
import com.example.ChatApplication.enums.RoomType;

public class RoomCreationRequestBodyToRoom {
	
	public static Room toEnitity(RoomCreationRequestBody roomCreationRequestBody) {
		
		System.out.println(roomCreationRequestBody.getRoomName());
		
		return Room.builder()
				.roomName(roomCreationRequestBody.getRoomName())
				.roomType(RoomType.GROUP)
				.createdAt(LocalDateTime.now())
				.build()
				;
		
	}
	
}
