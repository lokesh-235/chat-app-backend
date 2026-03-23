package com.example.ChatApplication.services;

import java.util.List;

import com.example.ChatApplication.dtos.requestBodies.MessageRequestBody;
import com.example.ChatApplication.dtos.responseBodies.MessageResponseBody;

public interface MessageService {
	


	List<MessageResponseBody> getMessages(Long roomId);
	
	MessageResponseBody addMessage(MessageRequestBody messageRequestBody);
	
}
