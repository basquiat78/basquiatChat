package com.basquiat.chat.member.vo;

import com.basquiat.chat.room.vo.RoomVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * Create By Basquiat
 *
 * Mapping Table : ${defaultTablePrefix}CHAT_MEMBER
 *
 */
@JsonInclude(Include.NON_NULL)
public class MemberVo implements Serializable {
	
	private static final long serialVersionUID = -6598872916584679155L;
	
	/**
	 * Member ID
	 */
	private long memberId;
		public long getMemberId() { return memberId; }
		public void setMemberId(long memberId) { this.memberId = memberId; }

	/**
	 * User ID 	
	 */
	private String userId;
		public String getUserId() { return userId; }
		public void setUserId(String userId) { this.userId = userId; }

	/**
	 * User Name
	 */
	private String userName;
		public String getUserName() { return userName; }
		public void setUserName(String userName) { this.userName = userName; }

	/**
	 * Password
	 */
	private String password;
		public String getPassword() { return password; }
		public void setPassword(String password) { this.password = password; }
		
	/**
	 * Encrypt Salt	
	 */
	private String encryptSalt;
		public String getEncryptSalt() { return encryptSalt; }
		public void setEncryptSalt(String encryptSalt) { this.encryptSalt = encryptSalt; }

	/**
	 * Delete Check
	 */
	private Integer isDeleted;
		public Integer getIsDeleted() { return isDeleted; }
		public void setIsDeleted(Integer isDeleted) { this.isDeleted = isDeleted; }
	
	/**
	 * Room List Established By Member	
	 */
	private List<RoomVo> roomList;
		public List<RoomVo> getRoomList() { return roomList; }
		public void setRoomList(List<RoomVo> roomList) { this.roomList = roomList; }

	/**
	 * Regist Date 
	 */
	private Date regDttm;
		public Date getRegDttm() { return regDttm; }
		public void setRegDttm(Date regDttm) { this.regDttm = regDttm; }

}