package com.ssafy.ssafit.model.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.ssafit.model.dto.Notice;

public interface NoticeService {
	public List<Notice> getNoticeList(HashMap<String, String> params);

	public int writeNotice(Notice notice);

	public int updateNotice(Notice notice);

	public int deleteNotice(int noticeId);
	
	public Notice readNotice(int noticeId);
	
	public Notice noticeDetail(int noticeId);
}
