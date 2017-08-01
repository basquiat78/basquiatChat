package com.basquiat.chat.room.web;

import com.basquiat.chat.common.ExceptionController;
import com.basquiat.chat.room.service.RoomService;
import com.basquiat.chat.room.vo.RoomVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 
 * Create By Basquiat
 *
 */
@Controller
public class RoomController extends ExceptionController {
	
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(RoomController.class);
	
	@Autowired
	RoomService roomService;
	
	/**
	 * 채팅방 생성
	 * @param roomVo
	 * @return RoomVo
	 */
	@RequestMapping(value = "/rooms", method = RequestMethod.POST)
	public @ResponseBody
	RoomVo createRoom(@RequestBody RoomVo roomVo) {
		return roomService.createRoom(roomVo);
	}
	
	/**
	 * 사용자가 포함된 모든 채팅방
	 * @param userId
	 * @return ArrayList<RoomVo>
	 */
	@RequestMapping(value = "/rooms/user/{userId}", method = RequestMethod.GET)
	public @ResponseBody
	List<RoomVo> getAllRoomById(@PathVariable("userId") String userId) {
		return roomService.getAllRoomById(userId);
	}
	
	/**
	 * 사용자에 의해 개설된 방
	 * @param userId
	 * @return ArrayList<RoomVo>
	 */
	@RequestMapping(value = "/rooms/establisher/{establisherId}", method = RequestMethod.GET)
	public @ResponseBody
	List<RoomVo> getRoomByEstablisherId(@PathVariable("establisherId") String establisherId) {
		return roomService.getRoomByEstablisherId(establisherId);
	}
	
	/**
	 * RoomId에 해당하는 Room Data
	 * @param roomId
	 * @return RoomVo
	 */
	@RequestMapping(value = "/rooms/{roomId}/user/{userId}", method = RequestMethod.GET)
	public @ResponseBody RoomVo getRoomById(@PathVariable("roomId") String roomId, @PathVariable("userId") String userId) {
		return roomService.getRoomById(roomId, userId);
	}
	
}