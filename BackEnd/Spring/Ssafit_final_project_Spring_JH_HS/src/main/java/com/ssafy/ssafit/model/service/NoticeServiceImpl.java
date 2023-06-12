package com.ssafy.ssafit.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.NoticeDao;
import com.ssafy.ssafit.model.dto.Notice;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	public NoticeDao noticeDao;

	@Override
	public List<Notice> getNoticeList(HashMap<String, String> params) {
		return noticeDao.selectList(params);
	}

	@Override
	public int writeNotice(Notice notice) {
		return noticeDao.insertNotice(notice);
	}

	@Override
	public int updateNotice(Notice notice) {
		return noticeDao.updateNotice(notice);
	}

	@Override
	public int deleteNotice(int noticeId) {
		return noticeDao.deleteNotice(noticeId);
	}

	@Override
	public Notice noticeDetail(int noticeId) {
		return noticeDao.detailNotice(noticeId);
	}

	@Override
	public Notice readNotice(int boardId) {
		noticeDao.updateViewCnt(boardId);
		return noticeDao.detailNotice(boardId);
	}

}
