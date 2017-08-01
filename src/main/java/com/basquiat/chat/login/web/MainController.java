package com.basquiat.chat.login.web;

import com.basquiat.chat.common.ExceptionController;
import com.basquiat.chat.member.service.MemberService;
import com.basquiat.chat.member.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * Create By Basquiat
 *
 */
@Controller
public class MainController extends ExceptionController {

	@Autowired
	MemberService memberService;
	
	/**
	 * memberId Home Page
	 * @param request
	 * @param memberId
	 * @return String
	 */
	@RequestMapping(value = "/main/{memberId}", method = RequestMethod.GET)
	public String main(HttpServletRequest request, @PathVariable("memberId") long memberId) {
		
		MemberVo member = memberService.getMemberByMemberId(memberId);
		
		request.setAttribute("userId", member.getUserId());
		request.setAttribute("userName", member.getUserName());
		request.setAttribute("memberId", member.getMemberId());
		
		return "chat/main";
	}
	
}