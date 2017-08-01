package com.basquiat.chat.notice.mapper;

import com.basquiat.chat.notice.vo.NoticeVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * Create By Basquiat
 *
 * @See /mapper/${rdbms.product}/notice/notice.xml
 *
 */
@Repository
public interface NoticeMapper {

	void insertNotice(NoticeVo noticeVo);
	void updateNotice(long noticeId);

	List<NoticeVo> selectNotice(String userId);

}