package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Like;
import com.ssafy.ssafit.model.dto.Review;

public interface ReviewService {	
	public void addReview(Review review);
	
	public void modifyReview(String videoId, String reviewId, String reviewContent);
	
	public void deleteReview(String videoId, String reviewId);
	
	public List<Review> selectByVideoId(String videoId);
	
	public List<Review> selectDislikedReviews();
	
	public void like(String videoId, String reviewId);
	
	public void dislike(String videoId, String reviewId);
	
	public void liked(String userId, String reviewId);
	
	public void disliked(String userId, String reviewId);
	
	public List<Like> getAllLike(String userId);
	
	public void undo(String userId, String reviewId, String videoId, String which);
}
