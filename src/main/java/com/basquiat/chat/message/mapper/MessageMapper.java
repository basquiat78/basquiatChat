package com.basquiat.chat.message.mapper;

import com.basquiat.chat.message.vo.MessageReadVo;
import com.basquiat.chat.message.vo.MessageVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * Create By Basquiat
 *
 * @See /mapper/${rdbms.product}/message/message.xml
 *
 */
@Repository
public interface MessageMapper {

	void insertMessage(MessageVo messageVo);
	void insertMessageRead(MessageReadVo messageReadVo);
	void updateMessageRead(MessageReadVo messageReadVo);
	void updateLastMessage(MessageVo messageVo);
	void deleteMessageRead(MessageReadVo messageReadVo);
	
	int selectUnreadMessageCount(long roomId);
	
	MessageReadVo selectMessageRead(MessageReadVo messageReadVo);
	
	List<MessageVo> selectMessageByRoomId(Map<String, String> map);
	
}