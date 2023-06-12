package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.FollowDao;
import com.ssafy.ssafit.model.dto.Follow;

@Service
public class FollowServiceImpl implements FollowService {

	@Autowired
	public FollowDao followDao;
	
	@Override
	public List<Follow> getAllFollowList(String userId) {
		return followDao.selectAllFollowing(userId);
	}

	@Override
	public int addFollower(Follow follow) {
		return followDao.insertFollow(follow);

	}

	@Override
	public int removeFollower(Follow follow) {
		return followDao.deleteFollow(follow);
	}

	@Override
	public Follow getFollowByFollowId(int followId) {
		return followDao.selectByFollowId(followId);
	}
}
