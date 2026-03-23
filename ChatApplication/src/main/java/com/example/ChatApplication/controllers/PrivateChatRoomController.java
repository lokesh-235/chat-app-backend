package com.example.ChatApplication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ChatApplication.dtos.responseBodies.RoomResponseBody;

public class PrivateChatRoomController {
	@PostMapping("/private-room")
	public ResponseEntity<RoomResponseBody> createPrivateRoom(@RequestParam Long otherUserId) {

	    return null;
	}
}
