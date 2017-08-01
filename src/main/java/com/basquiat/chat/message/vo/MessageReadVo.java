package com.basquiat.chat.message.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * Create By Basquiat
 *
 * Mapping Table : ${defaultTablePrefix}CHAT_READ
 *
 */
@JsonInclude(Include.NON_NULL)
public class MessageReadVo implements Serializable{

	private static final long serialVersionUID = 8178556441751091228L;
	
	/** 
	 * Chatting Room Id
	 */
	private long roomId;
		public long getRoomId() { return roomId; }
		public void setRoomId(long roomId) { this.roomId = roomId; }

	/**
	 * user Id	
	 */
	private String userId;
		public String getUserId() { return userId; }
		public void setUserId(String userId) { this.userId = userId; }
	
	/**
	 * count
	 */
	private long messageCount;
		public long getMessageCount() { return messageCount; }
		public void setMessageCount(long messageCount) { this.messageCount = messageCount; }
		
	/**
	 * reg date
	 */
	private Date regDttm;
		public Date getRegDttm() { return regDttm; }
		public void setRegDttm(Date regDttm) { this.regDttm = regDttm; }
		
	/**
	 * mod date
	 */
	private Date modDttm;
		public Date getModDttm() { return modDttm; }
		public void setModDttm(Date modDttm) { this.modDttm = modDttm; }
		
}