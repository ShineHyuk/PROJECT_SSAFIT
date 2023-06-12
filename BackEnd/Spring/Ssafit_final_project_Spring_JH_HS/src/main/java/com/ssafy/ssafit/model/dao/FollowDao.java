package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.Follow;

public interface FollowDao {
	List<Follow> selectAllFollowing(String userId);
	
	Follow selectByFollowId(int followId);
	
	int insertFollow(Follow follow);
	
	int deleteFollow(Follow follow);
}
