package com.example.ChatApplication.controllers;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ChatApplication.dtos.requestBodies.RoomCreationRequestBody;
import com.example.ChatApplication.dtos.responseBodies.RoomResponseBody;
import com.example.ChatApplication.services.RoomService;

@RestController
@RequestMapping("/apis/rooms")
public class RoomController {
	
	private RoomService roomService;
	
	public RoomController(RoomService roomService) {
		// TODO Auto-generated constructor stub
		this.roomService = roomService;
	}
	
	@GetMapping("/all-rooms")
	public ResponseEntity<List<RoomResponseBody>> getRooms(){
		return ResponseEntity.ok(roomService.getRooms());
	}
	
	@PostMapping("/create-room")
	public ResponseEntity<RoomResponseBody> createRoom( @RequestBody RoomCreationRequestBody roomCreationRequestBody){
		System.out.println(roomCreationRequestBody.getRoomName());
		return ResponseEntity.ok(roomService.createRoom(roomCreationRequestBody));
	}
	
}
