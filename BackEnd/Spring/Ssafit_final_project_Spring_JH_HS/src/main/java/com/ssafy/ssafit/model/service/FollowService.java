package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Follow;

public interface FollowService {
	List<Follow> getAllFollowList(String userId);
	
	Follow getFollowByFollowId(int followId);
	
	int addFollower(Follow follow);
	
	int removeFollower(Follow follow);
}
