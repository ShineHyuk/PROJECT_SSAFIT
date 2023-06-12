package com.ssafy.ssafit.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.ssafit.model.dto.Video;

public interface BasketDao {
	List<Video> selectAll(String userId);
	
	void insertBasket(@Param("userId") String userId, @Param("videoId") String videoId);
	
	void deleteBasket(@Param("userId") String userId, @Param("videoId") String videoId);
	
	Video checkBasket(@Param("userId") String userId, @Param("videoId") String videoId);
}
