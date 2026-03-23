package com.example.ChatApplication.entities;


import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


import com.example.ChatApplication.enums.MessageType;

@Entity
@Table(name="messages")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String content;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    private LocalDateTime sentAt;

    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name="sender_id")
    private User sender;
    
    

}
