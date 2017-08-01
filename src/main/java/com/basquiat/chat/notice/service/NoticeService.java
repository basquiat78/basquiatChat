package com.basquiat.chat.notice.service;

import com.basquiat.chat.common.code.CommonCode;
import com.basquiat.chat.follower.service.FollowerService;
import com.basquiat.chat.follower.vo.FollowerVo;
import com.basquiat.chat.notice.mapper.NoticeMapper;
import com.basquiat.chat.notice.vo.NoticeVo;
import com.basquiat.chat.websocket.service.WsSessionStore;
import com.basquiat.chat.websocket.vo.WebSocketVo;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * Create By Basquiat
 *
 */
@Service("noticeService")
public class NoticeService {
	
	private static final Logger LOG = LoggerFactory.getLogger(NoticeService.class);

	@Autowired
	FollowerService followerService;
	
	@Autowired
	NoticeMapper noticeMapper;
	
	/**
	 * Notice 세팅
	 * @param message
	 */
	public void setNotice(String message) {
		LOG.info("setNotice Info: " + message);
	}
	
	/**
	 * 웹소켓의 세션으로부터 접속된 인원 정보를 가져온다.
	 * @return JSONObject
	 */
	public JSONObject getConnectUserInfo() {
		
		Collection<WebSocketVo> webSocketVoList = WsSessionStore.getWebSocketVoList();
		JSONObject json = new JSONObject();
		json.put("messageType", CommonCode.MESSAGE_TYPE.NOTICE.name());
		json.put("message", "현재 접속자 수는 " + webSocketVoList.size() + "명 입니다.");
		json.put("connectUsers", WsSessionStore.getConnectUsersInfo());
		
		return json;
	}

	/**
	 * 초대한 사람의 정보를 등록한다.
	 * @param noticeVo
	 * @return List<NoticeVo>
	 */
	public List<NoticeVo> inviteFollowers(NoticeVo noticeVo) {
		List<FollowerVo> noticeFollowerList = noticeVo.getNoticeFollowerList();
		List<NoticeVo> resultNoticeVoList = new ArrayList<>();
		
		for(FollowerVo followerVo : noticeFollowerList) {
			NoticeVo vo = new NoticeVo();
			vo.setItemId(noticeVo.getItemId());
			vo.setItemType(noticeVo.getItemType());
			vo.setItemTitle(noticeVo.getItemTitle());
			vo.setFromFollowerId(noticeVo.getFromFollowerId());
			vo.setFromFollowerName(noticeVo.getFromFollowerName());
			vo.setToFollowerId(followerVo.getFollowerId());
			vo.setToFollowerName(followerVo.getFollowerName());
			
			StringBuffer itemContent = new StringBuffer();
			itemContent.append(noticeVo.getFromFollowerName())
							  .append("님이 [")
							  .append(noticeVo.getItemTitle())
							  .append("]방으로 ")
							  .append(followerVo.getFollowerName())
							  .append("님을 초대하였습니다.");
			
			vo.setItemContent(itemContent.toString());
			this.insertNotice(vo);
			followerVo.setRoomId(noticeVo.getItemId());
			followerService.addFollower(followerVo);
			
			resultNoticeVoList.add(vo);
		}
		
		return resultNoticeVoList;
	}
	
	/**
	 * Notice 등록
	 * @param noticeVo
	 */
	public void insertNotice(NoticeVo noticeVo) {
		noticeMapper.insertNotice(noticeVo);
	}
	
	/**
	 * 노티스 가져오기
	 * @param userId
	 * @return List<NoticeVo>
	 */
	public List<NoticeVo> getNotice(String userId) {
		return noticeMapper.selectNotice(userId);
	}
	
	/**
	 * 노티스 업데이트
	 * @param noticeId
	 * @return NoticeVo
	 */
	public NoticeVo readNotice(long noticeId) {
		noticeMapper.updateNotice(noticeId);
		NoticeVo returnVo = new NoticeVo();
		returnVo.setNoticeId(noticeId);
		returnVo.setIsRead(1);
		return returnVo;
	}
	
}