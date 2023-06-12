package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.VideoDao;
import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.Video;

@Service
public class VideoServiceImpl implements VideoService {
	
	private VideoDao videoDao;
	
	@Autowired
	public VideoServiceImpl(VideoDao videoDao) {
		this.videoDao = videoDao;
	}

	@Override
	public List<Video> getVideoList() {
		return videoDao.selectAll();
	}


	@Override
	public Video showDetail(String id) {
		videoDao.updateViewCnt(id);
		return videoDao.detail(id);
	}

	@Override
	public List<Video> search(SearchCondition condition) {
		return videoDao.search(condition);
	}

	@Override
	public List<Video> getVideoListByPart(int exerciseCode) {
		return videoDao.selectByPart(exerciseCode);
	}
}
