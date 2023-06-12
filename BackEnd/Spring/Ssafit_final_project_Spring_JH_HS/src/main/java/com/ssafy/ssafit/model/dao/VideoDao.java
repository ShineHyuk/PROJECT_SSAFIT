package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.SearchCondition;
import com.ssafy.ssafit.model.dto.Video;

public interface VideoDao {
	// 전체 운동 영상 가져오기
	public List<Video> selectAll();
	
	public List<Video> selectByPart(int exerciseCode);
	
	// 영상 상세보기
	public Video detail(String id);
	
	// 조회수 증가
	public void updateViewCnt(String id);
	
	// 조회수 감소
	public void deductViewCnt(String id);
	
	// 검색
	public List<Video> search(SearchCondition condition);
}
