package com.example.ChatApplication.mappers;

import com.example.ChatApplication.dtos.responseBodies.RoomResponseBody;
import com.example.ChatApplication.entities.Room;

public class RoomMapper {
	public static RoomResponseBody toDto(Room room) {
		return RoomResponseBody.builder()
				.id(room.getId())
				.roomName(room.getRoomName())
				.roomType(room.getRoomType())
				.build();
	}
}
