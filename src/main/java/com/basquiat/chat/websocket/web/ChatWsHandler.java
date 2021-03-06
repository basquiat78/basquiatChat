package com.basquiat.chat.websocket.web;

import com.basquiat.chat.common.code.CommonCode;
import com.basquiat.chat.common.util.MessageUtil;
import com.basquiat.chat.follower.vo.FollowerVo;
import com.basquiat.chat.member.service.MemberService;
import com.basquiat.chat.message.service.MessageService;
import com.basquiat.chat.message.vo.MessageReadVo;
import com.basquiat.chat.notice.service.NoticeService;
import com.basquiat.chat.notice.vo.NoticeVo;
import com.basquiat.chat.websocket.service.WsSessionStore;
import com.basquiat.chat.websocket.vo.WebSocketVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;

/**
 * 
 * Create By Basquiat
 *
 */
@Component
public class ChatWsHandler extends TextWebSocketHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ChatWsHandler.class);

	@Autowired
	NoticeService noticeService;
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	MemberService memberService;
	
	private static final String MESSAGE_TYPE = "messageType";
	private static final String ITEM_TYPE = "itemType";
	private static final String FOLLOWER_LIST = "followerList";
	private static final String FOLLOWER_ID = "followerId";
	private static final String WHISPER_ID = "whisperId";
	private static final String ROOM_ID = "roomId";
	
	/**
	 * Event Operation
	 * @param session
	 * @param message
	 * @throws Exception
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		JSONObject jsonObject = new JSONObject(message.getPayload());
		
		String messageType = "";
		if(jsonObject.has(MESSAGE_TYPE)) {
			messageType = jsonObject.getString(MESSAGE_TYPE);
		} else {
			messageType = jsonObject.getString(ITEM_TYPE);
		}
		
		this.broadcast(jsonObject, messageType);
		
	}

	/**
	 * 웹소켓 메세지 전달 Broadcast
	 * @param jsonObject
	 * @param messageType
	 * @throws IOException
	 */
	private void broadcast(JSONObject jsonObject, String messageType) throws IOException {
		/**
		 * 메세지에서 타입을 추출한다.
		 * 메세지 타입
		 */
		if(messageType.equals(CommonCode.MESSAGE_TYPE.MESSAGE.name())) { // 채팅 메세지 전송
			
			JSONArray list = (JSONArray) jsonObject.get(FOLLOWER_LIST);
			for(int i=0; i < list.length(); i++) {
				
				JSONObject json = (JSONObject)list.get(i);
				String messageToUserId = json.getString(FOLLOWER_ID);

				// 해당 아이디로 웹소켓 세션을 찾고 null이 아니면 메세지를 전송한다.
				// 만일 없다면 알람을 위한 노티스 세팅
				List<WebSocketSession> storeSessionList = WsSessionStore.getSessionByUserId(messageToUserId);
				if(storeSessionList != null) {
					for(WebSocketSession webSocketSession : storeSessionList) {
						webSocketSession.sendMessage(new TextMessage(jsonObject.toString()));
					}
				} else {
					// session이 null이면 ung_chat_read테이블에 인서트한다.
					MessageReadVo vo = new MessageReadVo();
					vo.setRoomId(Long.parseLong(jsonObject.get(ROOM_ID).toString()));
					vo.setUserId(messageToUserId);
					
					MessageReadVo resultVo = messageService.selectMessageRead(vo);
					if(resultVo == null) {
						messageService.insertMessageRead(vo);
					} else {
						messageService.updateMessageRead(vo);
					}
				}
			}
			
		} else if(messageType.equals(CommonCode.MESSAGE_TYPE.INVITE.name())) { // 채팅방으로 초대한 경우
			// notice 테이블에 관련 정보를 인서트하고
			ObjectMapper mapper = new ObjectMapper();
			NoticeVo noticeVo = mapper.readValue(jsonObject.toString(), NoticeVo.class);
			List<NoticeVo> resultNoticeVoList = noticeService.inviteFollowers(noticeVo);
			// 웹소켓으로 메세지를 전송한다.
			for(NoticeVo resultNoticeVo : resultNoticeVoList) {
				List<WebSocketSession> storeSessionList = WsSessionStore.getSessionByUserId(resultNoticeVo.getToFollowerId());
				if(storeSessionList != null) {
					String noticeContent = MessageUtil.makeInviteNoticeMessage(resultNoticeVo);
					for(WebSocketSession webSocketSession : storeSessionList) {
						webSocketSession.sendMessage(new TextMessage(noticeContent));
					}
				}
			}
			
			// 초대한 경우 현재 방의 정보를 업데이트해야하기 때문에 방에 존재하는 사용자에게 알람을 보내야한다.
			List<FollowerVo> roomFollowerList = noticeVo.getRoomFollowerList();
			// 웹소켓으로 메세지를 전송한다.
			for(FollowerVo followerVo : roomFollowerList) {
				List<WebSocketSession> storeSessionList = WsSessionStore.getSessionByUserId(followerVo.getFollowerId());
				if(storeSessionList != null) {
					String reloadContent = MessageUtil.makeReloadMessage(followerVo);
					for(WebSocketSession webSocketSession : storeSessionList) {
						webSocketSession.sendMessage(new TextMessage(reloadContent));
					}
				}
			}

		} else if(messageType.equals(CommonCode.MESSAGE_TYPE.PARTICIPATION.name())) { // 채팅방에 참여한 경우
			
		} else if(messageType.equals(CommonCode.MESSAGE_TYPE.LEAVE.name())) { // 채팅방에서 나간 경우
			
		} else if(messageType.equals(CommonCode.MESSAGE_TYPE.WHISPER.name())) { // 귓속말

			String whisperId = jsonObject.getString(WHISPER_ID);
			List<WebSocketSession> storeSessionList = WsSessionStore.getSessionByUserId(whisperId);
			if(storeSessionList != null) {
				for(WebSocketSession webSocketSession : storeSessionList) {
					webSocketSession.sendMessage(new TextMessage(jsonObject.toString()));
				}
			} else {
				// session이 null이면 ung_chat_read테이블에 인서트한다.
				MessageReadVo vo = new MessageReadVo();
				vo.setRoomId(Long.parseLong(jsonObject.get(ROOM_ID).toString()));
				vo.setUserId(whisperId);

				MessageReadVo resultVo = messageService.selectMessageRead(vo);
				if(resultVo == null) {
					messageService.insertMessageRead(vo);
				} else {
					messageService.updateMessageRead(vo);
				}

			}

		}
	}
	
	/**
	 * Connection Event Method
	 * @param session
	 * @throws Exception
	 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

		String userId = session.getAttributes().get("userId").toString();
		WebSocketVo webSocketVo = WsSessionStore.getWebSocketVoById(userId);
		// null이면 최초 connect
		if(webSocketVo == null) {
			WebSocketVo newWebSocketVo = new WebSocketVo(session);
			newWebSocketVo.fillWebSocketVo(memberService);
			WsSessionStore.addSession(newWebSocketVo);

		} else {
			int size = webSocketVo.addSession(session);
			LOG.info("Vo session Size : " + size);
		}
		LOG.info("current sessions size : " + WsSessionStore.getWebSocketVoList().size() );

    }
    
    /**
     * Close Connection from Client
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		WsSessionStore.removeSession(session);
		LOG.info("current sessions size : " + WsSessionStore.getWebSocketVoList().size() );
    }

}