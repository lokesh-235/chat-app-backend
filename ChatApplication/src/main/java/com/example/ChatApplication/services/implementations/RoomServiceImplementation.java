package com.example.ChatApplication.services.implementations;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.ChatApplication.dtos.requestBodies.RoomCreationRequestBody;
import com.example.ChatApplication.dtos.responseBodies.RoomResponseBody;
import com.example.ChatApplication.entities.Room;
import com.example.ChatApplication.mappers.RoomCreationRequestBodyToRoom;
import com.example.ChatApplication.mappers.RoomMapper;
import com.example.ChatApplication.repositories.RoomRepository;
import com.example.ChatApplication.services.RoomService;

@Service
public class RoomServiceImplementation implements RoomService {
	
	private RoomRepository roomRepository;
	
	
	public RoomServiceImplementation(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}

	@Override
	public List<RoomResponseBody> getRooms() {
		// TODO Auto-generated method stub
		List<Room> rooms = roomRepository.findAll(
			    Sort.by(Sort.Direction.DESC, "createdAt")
				);
		
		Stream<Room> stream = rooms.stream();
		
		List<RoomResponseBody> roomDtos = stream.map(RoomMapper::toDto).toList();
		
		
		
		return roomDtos;
	}

	@Override
	public RoomResponseBody createRoom(RoomCreationRequestBody roomCreationRequestBody) {
		// TODO Auto-generated method stub
		
		Room room = RoomCreationRequestBodyToRoom.toEnitity(roomCreationRequestBody);
		
		Room savedRoom = roomRepository.save(room);
		
		return RoomMapper.toDto(savedRoom);
		
	}
	
	
	
}
