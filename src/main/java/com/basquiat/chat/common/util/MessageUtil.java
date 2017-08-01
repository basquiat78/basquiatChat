package com.basquiat.chat.common.util;

import com.basquiat.chat.common.code.CommonCode;
import com.basquiat.chat.follower.vo.FollowerVo;
import com.basquiat.chat.notice.vo.NoticeVo;
import org.json.JSONObject;

/**
 *
 * Create By Basquiat (uEngine Solutions)
 *
 */
public class MessageUtil {

	/**
	 * 초대 메세지 생성
	 * @param noticeVo
	 * @return String
	 */
	public static String makeInviteNoticeMessage(NoticeVo noticeVo) {
		JSONObject json = new JSONObject();
		json.put("messageType", noticeVo.getItemType());
		json.put("noticeId", noticeVo.getNoticeId());
		json.put("roomId", noticeVo.getItemId());
		json.put("message", noticeVo.getItemContent());
		return json.toString();
	}

	/**
	 * reload 메세지 생성
	 * @param FollowerVo
	 * @return String
	 */
	public static String makeReloadMessage(FollowerVo followerVo) {
		JSONObject json = new JSONObject();
		json.put("messageType", CommonCode.MESSAGE_TYPE.RELOAD.name());
		json.put("roomId", followerVo.getRoomId());
		json.put("followerId", followerVo.getFollowerId());
		json.put("follwerName", followerVo.getFollowerName());
		return json.toString();
	}

}