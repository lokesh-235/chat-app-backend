package com.example.ChatApplication.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ChatApplication.entities.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	
}
