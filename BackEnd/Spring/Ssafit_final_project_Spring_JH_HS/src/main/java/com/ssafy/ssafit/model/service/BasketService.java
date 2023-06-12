package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Video;

public interface BasketService {
	List<Video> getAllBasketList(String userId);
	
	void addBasket(String userId, String videoId);
	
	void removeBasket(String userId, String videoId);
	
	boolean checkBasket(String userId, String videoId);
}
