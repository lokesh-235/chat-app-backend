package com.example.ChatApplication.controllers;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.ChatApplication.dtos.requestBodies.MessageRequestBody;
import com.example.ChatApplication.dtos.responseBodies.MessageResponseBody;
import com.example.ChatApplication.services.MessageService;

@Controller
public class SendMessageController {
	
	 private final MessageService messageService;
	 private final SimpMessagingTemplate messagingTemplate;
	
	
	public SendMessageController(MessageService messageService
			,SimpMessagingTemplate messagingTemplate) {
		// TODO Auto-generated constructor stub
		this.messageService = messageService;
		this.messagingTemplate = messagingTemplate;
	}
	
	@MessageMapping("/rooms/{roomId}/sendMessage")
    public void sendMessage(@DestinationVariable String roomId,
                           @Payload MessageRequestBody message) {

        System.out.println("Room: " + roomId);
        System.out.println("Message: " + message);

        MessageResponseBody response = messageService.addMessage(message);

        // ✅ dynamically send to correct room
        messagingTemplate.convertAndSend(
                "/topic/" + roomId + "/messages",
                response
        );
        
	}
        
	
}
