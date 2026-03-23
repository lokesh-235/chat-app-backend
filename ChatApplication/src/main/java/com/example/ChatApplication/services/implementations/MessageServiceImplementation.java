package com.example.ChatApplication.services.implementations;

import java.util.List;



import org.springframework.stereotype.Service;

import com.example.ChatApplication.dtos.requestBodies.MessageRequestBody;
import com.example.ChatApplication.dtos.responseBodies.MessageResponseBody;
import com.example.ChatApplication.entities.Message;
import com.example.ChatApplication.entities.Room;
import com.example.ChatApplication.entities.User;
import com.example.ChatApplication.mappers.MessageRequestBodyToMessageMapper;
import com.example.ChatApplication.mappers.MessageToMessageResponseBody;
import com.example.ChatApplication.repositories.MessageRepository;
import com.example.ChatApplication.repositories.RoomRepository;
import com.example.ChatApplication.repositories.UserRepository;
import com.example.ChatApplication.services.MessageService;

@Service
public class MessageServiceImplementation implements MessageService {
	
	private MessageRepository messageRepository;
	private RoomRepository roomRepository;
	private UserRepository userRepository;
	
	public MessageServiceImplementation(
			MessageRepository messageRepository
			,RoomRepository roomRepository
			,UserRepository userRepository
			) {
		// TODO Auto-generated constructor stub
		this.messageRepository = messageRepository;
		this.roomRepository = roomRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<MessageResponseBody> getMessages(Long roomId) {
		// TODO Auto-generated method stub
		List<Message> messages = messageRepository.findByRoomId(roomId).orElseThrow(()->new RuntimeException("Empty Messages"));
		
		List<MessageResponseBody> messageDtos = messages.stream()
				.map(MessageToMessageResponseBody::toDto)
				.toList();
		
		return messageDtos;
	}

	@Override
	public MessageResponseBody addMessage(MessageRequestBody messageRequestBody) {
		// TODO Auto-generated method stub
		Room room = roomRepository.findById(messageRequestBody.getRoomId())
				.orElseThrow(()->new RuntimeException("Room does not exist"));
		
		User sender = userRepository.findById(messageRequestBody.getSenderId())
				.orElseThrow(()->new RuntimeException("User does not exist"));
		
		Message message = MessageRequestBodyToMessageMapper.toEntity(messageRequestBody, room, sender);
		
		Message savedMessage = messageRepository.save(message);
		
		return MessageToMessageResponseBody.toDto(savedMessage);
	}

}
