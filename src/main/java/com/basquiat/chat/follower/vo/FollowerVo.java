package com.basquiat.chat.follower.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * Create By Basquiat
 *
 * Mapping Table : ${defaultTablePrefix}CHAT_FOLLOWER
 *
 */
@JsonInclude(Include.NON_NULL)
public class FollowerVo implements Serializable {

	private static final long serialVersionUID = 2255457584329712223L;

	/** 
	 * Chatting Room Id
	 */
	private long roomId;
		public long getRoomId() { return roomId; }
		public void setRoomId(long roomId) { this.roomId = roomId; }
		
	/**
	 * Follower Id	
	 */
	private String followerId;
		public String getFollowerId() { return followerId; }
		public void setFollowerId(String followerId) { this.followerId = followerId; }
		
	/**
	 * Follwer Name	
	 */
	private String followerName;
		public String getFollowerName() { return followerName; }
		public void setFollowerName(String followerName) { this.followerName = followerName; }
	
	/**
	 * reg date
	 */
	private Date regDttm;
		public Date getRegDttm() { return regDttm; }
		public void setRegDttm(Date regDttm) { this.regDttm = regDttm; }
	
}