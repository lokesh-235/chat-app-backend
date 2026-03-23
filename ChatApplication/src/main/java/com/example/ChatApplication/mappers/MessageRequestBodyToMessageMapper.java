package com.example.ChatApplication.mappers;

import java.time.LocalDateTime;


import com.example.ChatApplication.dtos.requestBodies.MessageRequestBody;
import com.example.ChatApplication.entities.Message;
import com.example.ChatApplication.entities.Room;
import com.example.ChatApplication.entities.User;

public class MessageRequestBodyToMessageMapper {
	public static Message toEntity(MessageRequestBody messageRequestBody,Room room,User sender) {
		
		
		return Message.builder()
				.content(messageRequestBody.getContent())
				.messageType(messageRequestBody.getMessageType())
				.room(room)
				.sender(sender)
				.sentAt(LocalDateTime.now())
				.build();
		
	}
}
