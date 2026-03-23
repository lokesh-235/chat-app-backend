package com.example.ChatApplication.dtos.responseBodies;

import com.example.ChatApplication.enums.RoomType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponseBody {
	private Long id;

    private String roomName;
    
    private RoomType roomType;
}
