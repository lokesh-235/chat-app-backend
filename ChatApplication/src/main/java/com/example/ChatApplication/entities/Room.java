package com.example.ChatApplication.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import com.example.ChatApplication.enums.RoomType;

@Entity
@Table(name="rooms")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomName;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy="room")
    private List<Message> messages;

    @OneToMany(mappedBy="room")
    private List<RoomMember> members;

}
