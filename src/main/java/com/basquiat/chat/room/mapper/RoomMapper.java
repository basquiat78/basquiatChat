package com.basquiat.chat.room.mapper;

import com.basquiat.chat.room.vo.RoomVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * Create By Basquiat
 *
 * @See /mapper/${rdbms.product}/room/room.xml
 *
 */
@Repository
public interface RoomMapper {

	void insertRoom(RoomVo roomVo);

	RoomVo selectRoomById(Map<String, String> map);
	
	List<RoomVo> selectAllRoomById(String userId);
	List<RoomVo> selectRoomByEstablisherId(String establisherId);
	
}