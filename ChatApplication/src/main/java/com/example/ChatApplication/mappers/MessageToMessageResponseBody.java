package com.example.ChatApplication.mappers;

import com.example.ChatApplication.dtos.responseBodies.MessageResponseBody;
import com.example.ChatApplication.entities.Message;

public class MessageToMessageResponseBody {
	
	public static MessageResponseBody toDto(Message message) {
		
		 Long senderId = message.getSender().getId(); // safe if already loaded
		    Long roomId = message.getRoom().getId();
		
		return MessageResponseBody.builder()
				.content(message.getContent())
				.messageType(message.getMessageType())
				.id(message.getId())
				.senderId(senderId)
				.roomId(roomId)
				.sentAt(message.getSentAt())
				.senderUsername(message.getSender().getUsername())
				.build();
		
	}
	
}
