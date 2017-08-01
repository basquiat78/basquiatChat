package com.basquiat.chat.message.web;

import com.basquiat.chat.common.ExceptionController;
import com.basquiat.chat.message.service.MessageService;
import com.basquiat.chat.message.vo.MessageReadVo;
import com.basquiat.chat.message.vo.MessageVo;
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
public class MessageController extends ExceptionController {
	
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	MessageService messageService;
	
	/**
	 * 메세지 등록
	 * @param messageVo
	 * @return MessageVo
	 */
	@RequestMapping(value = "/messages", method = RequestMethod.POST)
	public @ResponseBody
	MessageVo sendMessage(@RequestBody MessageVo messageVo) {
		return messageService.sendMessage(messageVo);
	}
	
	/**
	 * 채팅방 아이디와 사용자 아이디로 메세지 가져오기
	 * @param roomId
	 * @param userId
	 * @return ArrayList<MessageVo>
	 */
	@RequestMapping(value = "/messages/room/{roomId}/user/{userId}", method = RequestMethod.GET)
	public @ResponseBody
	List<MessageVo> getMessageByRoomId(@PathVariable("roomId") String roomId, @PathVariable("userId") String userId) {
		return messageService.getMessageByRoomId(roomId, userId);
	}
	
	/**
	 * 읽지 않은 메세지에 대해 사용자와 방아이디에 대한 정보를 인서트한다.
	 * @param messageReadVo
	 * @return
	 */
	@RequestMapping(value = "/messages/read", method = RequestMethod.POST)
	public @ResponseBody
	MessageReadVo createMessageRead(@RequestBody MessageReadVo messageReadVo) {
		return messageService.insertMessageRead(messageReadVo);
	}
	
	/**
	 * 읽지 않은 메세지에 대해 사용자와 방아이디에 대한 정보를 가져온다. (단건)
	 * @param messageReadVo
	 * @return MessageReadVo
	 */
	@RequestMapping(value = "/messages/read", method = RequestMethod.GET)
	public @ResponseBody MessageReadVo getMessageRead(@RequestBody MessageReadVo messageReadVo) {
		return messageService.selectMessageRead(messageReadVo);
	}
	
	/**
	 * 읽지 않은 메세지에 대해 사용자와 방아이디에 대한 정보를 갱신한다.
	 * @param messageReadVo
	 * @return
	 */
	@RequestMapping(value = "/messages/read", method = RequestMethod.PUT)
	public @ResponseBody MessageReadVo updateMessageRead(@RequestBody MessageReadVo messageReadVo) {
		return messageService.updateMessageRead(messageReadVo);
	}
	
	/**
	 * 읽지 않은 메세지에 대해 사용자와 방아이디에 대한 정보를 삭제한다.
	 * @param messageReadVo
	 * @return
	 */
	@RequestMapping(value = "/messages/read", method = RequestMethod.DELETE)
	public @ResponseBody MessageReadVo deleteMessageRead(@RequestBody MessageReadVo messageReadVo) {
		return messageService.deleteMessageRead(messageReadVo);
	}

	/**
	 * 읽지 않는 사용자 수
	 * @param roomId
	 * @return
	 */
	@RequestMapping(value = "/messages/room/{roomId}/unread", method = RequestMethod.GET)
	public @ResponseBody int getUnreadMessageCount(@PathVariable("roomId") long roomId) {
		return messageService.getUnreadMessageCount(roomId);
	}

}