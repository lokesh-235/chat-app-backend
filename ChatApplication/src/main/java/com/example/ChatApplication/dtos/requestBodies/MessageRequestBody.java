package com.example.ChatApplication.dtos.requestBodies;


import com.example.ChatApplication.enums.MessageType;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequestBody {
	
    private String content;
  
    private MessageType messageType;

    private Long roomId;

    private Long senderId;
	
}
