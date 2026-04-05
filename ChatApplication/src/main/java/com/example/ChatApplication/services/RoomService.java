package com.example.ChatApplication.services;

import java.util.List;

import com.example.ChatApplication.dtos.requestBodies.RoomCreationRequestBody;
import com.example.ChatApplication.dtos.responseBodies.RoomResponseBody;

public interface RoomService {
	List<RoomResponseBody> getRooms();
	
	RoomResponseBody createRoom(RoomCreationRequestBody roomCreationRequestBody);
}
