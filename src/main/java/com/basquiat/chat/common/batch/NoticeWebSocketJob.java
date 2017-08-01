package com.basquiat.chat.common.batch;

import com.basquiat.chat.common.code.CommonCode;
import com.basquiat.chat.notice.service.NoticeService;
import com.basquiat.chat.websocket.service.WsSessionStore;
import com.basquiat.chat.websocket.vo.WebSocketVo;
import org.json.JSONObject;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 *
 * Create By Basquiat
 *
 * 현재 채팅에 접속한 사람들의 정보를 스케쥴로 처리한다.
 * 웹소켓에 접속할때마다 저장되는 유저의 정보를 가지고 있는
 * WsSessionStore에서 정보를 꺼내온다.
 *
 */
@DisallowConcurrentExecution
public class NoticeWebSocketJob extends QuartzJobBean {

	private static final Logger LOG = LoggerFactory.getLogger(NoticeWebSocketJob.class);
	
	NoticeService noticeService;
		public void setNoticeService(NoticeService noticeService) { this.noticeService = noticeService; }

	String wsServerUrl;
		public void setWsServerUrl(String wsServerUrl) { this.wsServerUrl = wsServerUrl; }
	
	String wsServerPath;
		public void setWsServerPath(String wsServerPath) { this.wsServerPath = wsServerPath; }

	String contextPath = null;
	String destUri = null;
	
	/**
	 * @param arg0
	 * @throws JobExecutionException
	 */
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {

		contextPath = ContextLoaderListener.getCurrentWebApplicationContext().getServletContext().getContextPath();
		destUri = wsServerUrl + contextPath + wsServerPath;
		LOG.info("Websocket destUri : " + destUri);
		this.broadCastingNotice();

	}

	/**
	 * Notice 웹소켓 전송
	 */
	private void broadCastingNotice() {

		Collection<WebSocketVo> webSocketVoList = WsSessionStore.getWebSocketVoList();
		for(WebSocketVo webSocketVo: webSocketVoList) {
			try {
				// 접속자 정보를 만들어서 보내준다.
				JSONObject json = new JSONObject();
				json.put("messageType", CommonCode.MESSAGE_TYPE.NOTICE.name());
				json.put("message", "현재 접속자 수는 " + webSocketVoList.size() + "명 입니다.");
				json.put("connectUsers", WsSessionStore.getConnectUsersInfo());
				List<WebSocketSession> webSocketSessionList = webSocketVo.getWebSocketSessionList();
				for( WebSocketSession webSocketSession :  webSocketSessionList) {
					webSocketSession.sendMessage(new TextMessage(json.toString()));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}