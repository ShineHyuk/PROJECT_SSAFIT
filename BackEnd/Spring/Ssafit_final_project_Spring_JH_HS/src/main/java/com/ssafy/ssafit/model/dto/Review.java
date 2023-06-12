package com.ssafy.ssafit.model.dto;

import java.util.Date;

public class Review {
	private int reviewId;
	private String videoId;
	private String writer;
	private String content;
	private int reviewLike;
	private int reviewDislike;
	private Date regDate;
	
	public Review() {
		
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getReviewLike() {
		return reviewLike;
	}

	public void setReviewLike(int reviewLike) {
		this.reviewLike = reviewLike;
	}
	

	public int getReviewDislike() {
		return reviewDislike;
	}

	public void setReviewDislike(int reviewDislike) {
		this.reviewDislike = reviewDislike;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", videoId=" + videoId + ", writer=" + writer + ", content=" + content
				+ ", reviewLike=" + reviewLike + ", reviewDislike=" + reviewDislike + ", regDate=" + regDate + "]";
	}

}
