//package com.example.ChatApplication.mappers;
//
//import com.example.ChatApplication.dtos.responseBodies.MessageResponseBody;
//import com.example.ChatApplication.entities.Message;
//
//public class MessageMapper {
//	
//	public static MessageResponseBody toDto(Message message) {
//		return MessageResponseBody.builder()
//				.id(message.getId())
//				.content(message.getContent())
//				.senderId(message.getSender().getId())
//				.roomId(message.getRoom().getId())
//				.build();
//	}
//	
//}
