package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.BasketDao;
import com.ssafy.ssafit.model.dao.VideoDao;
import com.ssafy.ssafit.model.dto.Video;

@Service
public class BasketServiceImpl implements BasketService {

	@Autowired
	public BasketDao basketDao;
	
	@Autowired
	public VideoDao videoDao;
	
	@Override
	public List<Video> getAllBasketList(String userId) {
		return basketDao.selectAll(userId);
	}

	@Override
	public void addBasket(String userId, String videoId) {
		basketDao.insertBasket(userId, videoId);
		videoDao.deductViewCnt(videoId);
	}

	@Override
	public void removeBasket(String userId, String videoId) {
		basketDao.deleteBasket(userId, videoId);
		videoDao.deductViewCnt(videoId);
	}

	@Override
	public boolean checkBasket(String userId, String videoId) {
		if(basketDao.checkBasket(userId, videoId) == null) {
			return false;
		}
		return true;
	}
}
