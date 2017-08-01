package com.basquiat.chat.follower.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;

/**
 *
 * Create By Basquiat
 *
 * Mapping Table : ${defaultTablePrefix}CHAT_MEMBER
 *
 */
@JsonInclude(Include.NON_NULL)
public class MentionVo implements Serializable {

	private static final long serialVersionUID = -8070569613747220494L;
		
	/**
	 * Mention Id	
	 */
	private String mentionId;
		public String getMentionId() { return mentionId; }
		public void setMentionId(String mentionId) { this.mentionId = mentionId; }
		
	/**
	 * Mention Name	
	 */
	private String mentionName;
		public String getMentionName() { return mentionName; }
		public void setMentionName(String mentionName) { this.mentionName = mentionName; }
	
}