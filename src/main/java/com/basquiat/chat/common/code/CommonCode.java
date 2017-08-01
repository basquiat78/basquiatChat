package com.basquiat.chat.common.code;

/**
 *
 * Create By Basquiat
 *
 * 메세지 타입 정의
 * 필요하다면 확장을 한다.
 *
 */
public class CommonCode {

	/**
	 * 메세지 관련 코드
	 * <pre>
	 * 		MESSAGE			: 내용 메세지
	 * 		WHISPER			: 귓속말 메세지
	 * 		INVITE			: 초대 메세지
	 * 		PARTICIPATION	: 참여 메세지
	 * 		LEAVE			: 탈방 메세지
	 * 		NOTICE			: 알람 메세지
	 * </pre>
	 */
	public static enum MESSAGE_TYPE {MESSAGE, WHISPER, INVITE, PARTICIPATION, LEAVE, NOTICE, RELOAD};
	
}