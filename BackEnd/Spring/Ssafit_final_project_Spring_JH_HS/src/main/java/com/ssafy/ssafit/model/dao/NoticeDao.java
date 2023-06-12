package com.ssafy.ssafit.model.dao;

import java.util.HashMap;
import java.util.List;

import com.ssafy.ssafit.model.dto.Notice;

public interface NoticeDao {
	// 게시글 목록
	public List<Notice> selectList(HashMap<String, String> params);

	public int insertNotice(Notice notice);

	public int updateNotice(Notice notice);

	public int deleteNotice(int noticeId);
	
	public Notice detailNotice(int noticeId);

	public void updateViewCnt(int noticeId);
}
