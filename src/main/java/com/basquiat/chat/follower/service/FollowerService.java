package com.basquiat.chat.follower.service;

import com.basquiat.chat.follower.mapper.FollowerMapper;
import com.basquiat.chat.follower.vo.FollowerVo;
import com.basquiat.chat.follower.vo.MentionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Create By Basquiat
 *
 */
@Service("followerService")
public class FollowerService {

	@Autowired
	FollowerMapper followerMapper;

	/**
	 * 멤버 등록
	 * @param  followerVo
	 * @return MemberVo
	 */
	public FollowerVo addFollower(FollowerVo followerVo) {
		followerMapper.insertFollower(followerVo);
		return followerVo;
	}
	
	/**
	 * 멘션 정보를 가져온다
	 * @param term
	 * @return List<MentionVo>
	 */
	public List<MentionVo> getMentionFollowerList(String term) {
		return followerMapper.selectMentionFollowerList(term);
	}
	
}