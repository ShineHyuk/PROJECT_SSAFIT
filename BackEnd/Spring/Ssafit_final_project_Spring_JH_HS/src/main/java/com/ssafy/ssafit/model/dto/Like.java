package com.ssafy.ssafit.model.dto;

public class Like {
	private String userId;
	private int reviewId;
	private boolean liked;
	private boolean disliked;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public boolean isLiked() {
		return liked;
	}
	public void setLiked(boolean liked) {
		this.liked = liked;
	}
	public boolean isDisliked() {
		return disliked;
	}
	public void setDisliked(boolean disliked) {
		this.disliked = disliked;
	}
	@Override
	public String toString() {
		return "Like [userId=" + userId + ", reviewId=" + reviewId + ", liked=" + liked + ", disliked=" + disliked
				+ "]";
	}

}
