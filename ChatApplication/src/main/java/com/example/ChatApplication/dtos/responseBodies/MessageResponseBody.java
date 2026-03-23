package com.example.ChatApplication.dtos.responseBodies;

import java.time.LocalDateTime;

import com.example.ChatApplication.enums.MessageType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponseBody {

    private Long id;
   
    private String content;
   
    private MessageType messageType;

    private LocalDateTime sentAt;
  
    private Long roomId;
   
    private Long senderId;
    
    private String senderUsername;
}
