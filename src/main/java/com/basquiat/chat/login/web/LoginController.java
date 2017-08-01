package com.basquiat.chat.login.web;

import com.basquiat.chat.common.ExceptionController;
import com.basquiat.chat.member.service.MemberService;
import com.basquiat.chat.member.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * Create By Basquiat
 *
 */

@Controller
public class LoginController extends ExceptionController {

	@Autowired
	MemberService memberService;
	
	/**
	 * 로그인 초기 화면
	 * @param session
	 * @return String
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String initLogin(HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
		return "login";
	}
	
	/**
	 * 등록
	 * @param memberVo
	 * @return MemberVo
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
    public @ResponseBody MemberVo initReg(@RequestBody MemberVo memberVo) {
		return memberService.createMember(memberVo);
	}
	
	/**
	 * login 
	 * @param session
	 * @param request
	 * @param loginId
	 * @param loginPassword
	 * @return String
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String chatLogin(HttpSession session, HttpServletRequest request,
								@RequestParam(required=true) String loginId,
								@RequestParam(required=true) String loginPassword) {
		
		MemberVo member = memberService.getCheckLoginMember(loginId, loginPassword);

		if(member == null || member.getMemberId() == 0L) {
			return "redirect:"+request.getContextPath()+"/login.do";
		}
		
		session.setAttribute("memberId", member.getMemberId());
		session.setAttribute("userId", member.getUserId());
		session.setAttribute("userName", member.getUserName());
		
		return "redirect:/main/"+member.getMemberId();
	}
	
}