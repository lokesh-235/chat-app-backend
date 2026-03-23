package com.example.ChatApplication.controllers;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ChatApplication.dtos.responseBodies.MessageResponseBody;
import com.example.ChatApplication.services.MessageService;

@RestController
@RequestMapping("/apis/rooms")
public class MessageController {
	
	private MessageService messageService;
	
	public MessageController(MessageService messageService) {
		// TODO Auto-generated constructor stub
		this.messageService = messageService;
				
	}
	
	@GetMapping("/{roomId}/messages")
	public ResponseEntity<List<MessageResponseBody>> getMessages(@PathVariable Long roomId){
		System.out.println("Request Hit");
		return ResponseEntity.ok(messageService.getMessages(roomId));
	}
	
	
	
}
