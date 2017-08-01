package com.basquiat.chat.follower.web;

import com.basquiat.chat.common.ExceptionController;
import com.basquiat.chat.follower.service.FollowerService;
import com.basquiat.chat.follower.vo.MentionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 * Create By Basquiat
 *
 */

@Controller
public class FollowerController extends ExceptionController{
	
	@Autowired
	FollowerService followerService;
	
	/**
	 * 멘션 정보
	 * @param term
	 * @return ArrayList<MentionVo>
	 */
	@RequestMapping(value = "/followers/mentions/{term}", method = RequestMethod.GET)
	public @ResponseBody
	List<MentionVo> autoCompleteFollowerList(@PathVariable("term") String term) {
		return followerService.getMentionFollowerList(term);
	}
	
}