package com.ssafy.ssafit.model.dto;

public class Basket {
	private String userId;
	private String videoId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	
	@Override
	public String toString() {
		return "Basket [userId=" + userId + ", videoId=" + videoId + "]";
	}
}
