package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.Video;

public interface VideoService {
	// 전체 운동 영상 가져오기
	public List<Video> getVideoList();
	
	// 부위별 영상 가져오기
	public List<Video> getVideoListByPart(int exerciseCode);
	
	// 영상 상세보기
	public Video showDetail(String id);
	
	// 검색하기
	public List<Video> search(SearchCondition condition);
}
