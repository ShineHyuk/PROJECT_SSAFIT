package com.ssafy.ssafit.model.dto;

public class Follow {
	private int followId;
	private String userId;
	private String followingId;
	
	public Follow() {}

	public int getFollowId() {
		return followId;
	}

	public void setFollowId(int followId) {
		this.followId = followId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFollowingId() {
		return followingId;
	}

	public void setFollowingId(String followingId) {
		this.followingId = followingId;
	}

	@Override
	public String toString() {
		return "follow [followId=" + followId + ", userId=" + userId + ", followingId=" + followingId + "]";
	}
}
