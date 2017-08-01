package com.basquiat.chat.notice.vo;

import com.basquiat.chat.follower.vo.FollowerVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * Create By Basquiat
 *
 * Mapping Table : ${defaultTablePrefix}CHAT_NOTICE
 *
 */
@JsonInclude(Include.NON_NULL)
public class NoticeVo implements Serializable {

	private static final long serialVersionUID = -5683212657583733918L;
	
	/**
	 * 알림 아이디
	 */
	private long noticeId;
		public long getNoticeId() { return noticeId; }
		public void setNoticeId(long noticeId) { this.noticeId = noticeId; }
	
	/**
	 * 아이템 타입
	 * @See CommonCode : MESSAGE_TYPE
	 */
	private String itemType;
		public String getItemType() { return itemType; }
		public void setItemType(String itemType) { this.itemType = itemType; }

	/**
	 * 아이템 아이디
	 * <pre>
	 * 		messageType is MESSAGE	: itemId means messageId
	 * 		messageType is INVITE 		: itemId means roomId
	 * </pre>
	 */
	private long itemId;
		public long getItemId() { return itemId; }
		public void setItemId(long itemId) { this.itemId = itemId; }
		
	/**
	 * 아이템 제목
	 * 
	 */
	private String itemTitle;
		public String getItemTitle() {return itemTitle;}
		public void setItemTitle(String itemTitle) {this.itemTitle = itemTitle;}
		
	/**
	 * 알림 내용
	 */
	private String itemContent;
		public String getItemContent() { return itemContent; }
		public void setItemContent(String itemContent) { this.itemContent = itemContent; }
		
	/**
	 * 알림 수신 사용자
	 */
	private String toFollowerId;
		public String getToFollowerId() { return toFollowerId; }
		public void setToFollowerId(String toFollowerId) { this.toFollowerId = toFollowerId; }
	
	/**
	 * 수신자명
	 */
	private String toFollowerName;
		public String getToFollowerName() {return toFollowerName;}
		public void setToFollowerName(String toFollowerName) {this.toFollowerName = toFollowerName;}

	/**
	 * 알림 발생 사용자
	 */
	private String fromFollowerId;
		public String getFromFollowerId() { return fromFollowerId; }
		public void setFromFollowerId(String fromFollowerId) { this.fromFollowerId = fromFollowerId; }
		
	/**
	 * 알림 발생 사용자명
	 */
	private String fromFollowerName;
		public String getFromFollowerName() {return fromFollowerName;}
		public void setFromFollowerName(String fromFollowerName) {this.fromFollowerName = fromFollowerName;}

	/**
	 * 읽음여부
	 */
	private Integer isRead;
		public Integer getIsRead() { return isRead; }
		public void setIsRead(Integer isRead) { this.isRead = isRead; }
		
	/**
	 * 등록일시
	 */
	private Date regDttm;
		public Date getRegDttm() { return regDttm; }
		public void setRegDttm(Date regDttm) { this.regDttm = regDttm; }

	/**
	 * 읽지 않은 알림 카운트
	 */
	private int noticeCount;
		public int getNoticeCount() {return noticeCount;}
		public void setNoticeCount(int noticeCount) {this.noticeCount = noticeCount;}

	/**
	 * 알림대상 리스트
	 */
	private List<FollowerVo> noticeFollowerList;
		public List<FollowerVo> getNoticeFollowerList() { return noticeFollowerList; }
		public void setNoticeFollowerList(List<FollowerVo> noticeFollowerList) { this.noticeFollowerList = noticeFollowerList; }

	/**
	 * 룸 팔로워 리스트
	 */
	private List<FollowerVo> roomFollowerList;
		public List<FollowerVo> getRoomFollowerList() { return roomFollowerList; }
		public void setRoomFollowerList(List<FollowerVo> roomFollowerList) { this.roomFollowerList = roomFollowerList; }

	/**
	 * 전체 알림 카운트
	 */
	private int totalNoticeCnt;
		public int getTotalNoticeCnt() { return totalNoticeCnt; }
		public void setTotalNoticeCnt(int totalNoticeCnt) { this.totalNoticeCnt = totalNoticeCnt; }
	
	/**
	 * noti date string
	 */
	private String noticeDateStr;
		public String getNoticeDateStr() { return noticeDateStr; }
		public void setNoticeDateStr(String noticeDateStr) { this.noticeDateStr = noticeDateStr; }
		
}