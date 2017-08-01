package com.basquiat.chat.member.service;

import com.basquiat.chat.common.exception.ChatRunTimeException;
import com.basquiat.chat.common.exception.ChatServiceException;
import com.basquiat.chat.member.mapper.MemberMapper;
import com.basquiat.chat.member.vo.MemberVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 *
 * Create By Basquiat
 *
 */
@Service("memberService")
public class MemberService {
	
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);

	@Resource(name="passwordEncoder")
	private ShaPasswordEncoder passwordEncoder;

	@Autowired
	MemberMapper memberMapper;
	
	/**
	 * 사용자 생성
	 * @param memberVo
	 * @return MemberVo
	 */
	public MemberVo createMember(MemberVo memberVo) {
		
		String encryptSalt = UUID.randomUUID().toString();
		String encodedPassword = passwordEncoder.encodePassword(memberVo.getPassword(), encryptSalt);
		
		memberVo.setPassword(encodedPassword);
		memberVo.setEncryptSalt(encryptSalt);
		
		memberMapper.insertMember(memberVo);

		memberVo.setEncryptSalt("");
		memberVo.setPassword("");
		
		return memberVo;
	}
	
	/**
	 * 사용자 아이디, 비번으로 정보 체크
	 * @param userId
	 * @param password
	 * @return MemberVo
	 */
	public MemberVo getCheckLoginMember(String userId, String password) {
		
		MemberVo member = getMemberByUserId(userId);
		
		if(member == null || member.getMemberId() < 1) {
			throw new ChatRunTimeException("로그인 정보가 존재하지 않습니다.");
		}
		
		String encryptPassword = member.getPassword();
		if(!encryptPassword.equals(passwordEncoder.encodePassword(password, member.getEncryptSalt()))) {
			throw new ChatServiceException("잘못된 로그인 정보입니다.");
		}
		return member;
	}

	/**
	 * 사용자 아이디로 멤버 조회
	 * @param userId
	 * @return MemberVo
	 */
	public MemberVo getMemberByUserId(String userId) {
		MemberVo memberVo =  memberMapper.selectMemberByUserId(userId);
		return memberVo;
	}
	
	/**
	 * 멤버 아이디로 멤버 조회
	 * @param memberId
	 * @return MemberVo
	 */
	public MemberVo getMemberByMemberId(long memberId) {
		return memberMapper.selectMemberByMemberId(memberId);
	}
	
}