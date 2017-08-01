package com.basquiat.chat.room.service;

import com.basquiat.chat.common.code.CommonCode;
import com.basquiat.chat.common.util.MessageUtil;
import com.basquiat.chat.follower.service.FollowerService;
import com.basquiat.chat.follower.vo.FollowerVo;
import com.basquiat.chat.notice.service.NoticeService;
import com.basquiat.chat.notice.vo.NoticeVo;
import com.basquiat.chat.room.mapper.RoomMapper;
import com.basquiat.chat.room.vo.RoomVo;
import com.basquiat.chat.websocket.service.WsSessionStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Create By Basquiat
 *
 */
@Service("roomService")
public class RoomService {
	
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(RoomService.class);

	@Autowired
	RoomMapper roomMapper;
	
	@Autowired
	FollowerService followerService;
	
	@Autowired
	NoticeService noticeService;
	
	/**
	 * 채팅방 생성
	 * @param roomVo
	 * @return RoomVo
	 * @throws IOException
	 */
	public RoomVo createRoom(RoomVo roomVo) {
		
		Date now = new Date();
		roomVo.setRegDttm(now);
		
		roomMapper.insertRoom(roomVo);
		
		List<FollowerVo> followerList = roomVo.getFollowerList();
		for(FollowerVo follower : followerList) {
			follower.setRegDttm(now);
			follower.setRoomId(roomVo.getRoomId());
			followerService.addFollower(follower);
			// 방을 생성한 사람을 제외한 나머지 팔로워에게 노티 알람
			if(	!roomVo.getEstablisherId().equals(follower.getFollowerId()) ) {
				this.broadcastToFollower(follower, roomVo);
			}
		}
		
		return roomVo;
	}
	
	/**
	 * 사용자가 포함된 모든 채팅방 조회
	 * @param userId
	 * @return RoomVo
	 */
	public List<RoomVo> getAllRoomById(String userId) {
		return roomMapper.selectAllRoomById(userId);
	}
	
	/**
	 * 사용자에 의해 개설된 방
	 * @param userId
	 * @return RoomVo
	 */
	public List<RoomVo> getRoomByEstablisherId(String establisherId) {
		return roomMapper.selectRoomByEstablisherId(establisherId);
	}
	
	/**
	 * roomId에 해당하는 Room Data
	 * @param roomId
	 * @return RoomVo
	 */
	public RoomVo getRoomById(String roomId, String userId) {
		Map<String, String> map = new HashMap<>();
		map.put("roomId", roomId);
		map.put("userId", userId);
		return roomMapper.selectRoomById(map);
	}
	
	/**
	 * 방 생성시 참여한 사람의 정보가 넘어온다면 해당 유저에게 노티 알람을 줘야한다.
	 * @param followerVo
	 * @param roomVo
	 * @throws IOException
	 */
	private void broadcastToFollower(FollowerVo followerVo, RoomVo roomVo) {
		
		/**
		 * 노티 정보 저장
		 */
		NoticeVo vo = new NoticeVo();
		vo.setItemId(roomVo.getRoomId());
		vo.setItemType(CommonCode.MESSAGE_TYPE.INVITE.name());
		vo.setItemTitle(roomVo.getRoomTitle());
		vo.setFromFollowerId(roomVo.getEstablisherId());
		vo.setFromFollowerName(roomVo.getEstablisherName());
		vo.setToFollowerId(followerVo.getFollowerId());
		vo.setToFollowerName(followerVo.getFollowerName());
		
		StringBuffer itemContent = new StringBuffer();;
		itemContent.append(roomVo.getEstablisherName())
						  .append("님이 [")
						  .append(roomVo.getRoomTitle())
						  .append("]방으로 ")
						  .append(followerVo.getFollowerName())
						  .append("님을 초대하였습니다.");
		
		vo.setItemContent(itemContent.toString());
		noticeService.insertNotice(vo);
		
		/**
		 * 웹소켓으로 캐스팅
		 */
		List<WebSocketSession> storeSessionList = WsSessionStore.getSessionByUserId(followerVo.getFollowerId());
		if(storeSessionList != null) {
			String noticeContent = MessageUtil.makeInviteNoticeMessage(vo);
			try {
				for(WebSocketSession webSocketSession :  storeSessionList) {
					webSocketSession.sendMessage(new TextMessage(noticeContent));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}