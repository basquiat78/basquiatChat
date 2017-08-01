package com.basquiat.chat.notice.web;

import com.basquiat.chat.common.ExceptionController;
import com.basquiat.chat.notice.service.NoticeService;
import com.basquiat.chat.notice.vo.NoticeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 
 * Create By Basquiat
 *
 */
@Controller
public class NoticeController extends ExceptionController{
	
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(NoticeController.class);
	
	@Autowired
	NoticeService noticeService;
	
	/**
	 * 웹소켓 접속자 수
	 * @return String
	 */
	@RequestMapping(value = "/notices/users", produces="text/plain;charset=UTF-8", method = RequestMethod.GET)
	public @ResponseBody
	String getConnectUserInfo() {
		return noticeService.getConnectUserInfo().toString();
	}
	
	/**
	 * follower 초대
	 * @param noticeVo
	 * @return List<NoticeVo>
	 */
	@RequestMapping(value = "/notices/invite", method = RequestMethod.POST)
	public @ResponseBody
	List<NoticeVo> inviteFollowers(@RequestBody NoticeVo noticeVo) {
		return noticeService.inviteFollowers(noticeVo);
	}
	
	/**
	 * notice list 가져오기
	 * @param userId
	 * @return List<NoticeVo>
	 */
	@RequestMapping(value = "/notices/user/{userId}", method = RequestMethod.GET)
	public @ResponseBody
	List<NoticeVo> getNotice(@PathVariable("userId") String userId) {
		return noticeService.getNotice(userId);
	}
	
	/**
	 * read notice check
	 * @param noticeId
	 * @return NoticeVo
	 */
	@RequestMapping(value = "/notices/{noticeId}", method = RequestMethod.PUT)
	public @ResponseBody NoticeVo readNotice(@PathVariable("noticeId") long noticeId) {
		return noticeService.readNotice(noticeId);
	}
	
}