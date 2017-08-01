package com.basquiat.chat.member.mapper;

import com.basquiat.chat.member.vo.MemberVo;
import org.springframework.stereotype.Repository;

/**
 *
 * Create By Basquiat
 *
 * @See /mapper/member/member.xml
 *
 */
@Repository
public interface MemberMapper {

	void insertMember(MemberVo memberVo);
	
	MemberVo selectMemberByUserId(String userId);
	MemberVo selectMemberByMemberId(long memberId);

}