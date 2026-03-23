package com.example.ChatApplication.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ChatApplication.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	
	Optional<List<Message>> findByRoomId(Long roomId);
	
	
}
