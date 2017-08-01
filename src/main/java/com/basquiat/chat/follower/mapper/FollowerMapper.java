package com.basquiat.chat.follower.mapper;

import com.basquiat.chat.follower.vo.FollowerVo;
import com.basquiat.chat.follower.vo.MentionVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * Create By Basquiat
 *
 * @See /mapper/${rdbms.product}/follower/follower.xml
 *
 */
@Repository
public interface FollowerMapper {

	void insertFollower(FollowerVo followerVo);
	
	List<MentionVo> selectMentionFollowerList(String term);
	
}