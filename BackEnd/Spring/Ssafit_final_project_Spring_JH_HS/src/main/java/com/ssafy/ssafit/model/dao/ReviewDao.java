package com.ssafy.ssafit.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.ssafit.model.dto.Like;
import com.ssafy.ssafit.model.dto.Review;

public interface ReviewDao {

	public List<Review> selectByVideoId(String videoId);

	public void insertReview(Review review);
	
	public List<Review> selectDislikedReviews();

	public void updateReview(@Param("videoId") String videoId, @Param("reviewId") String reviewId, @Param("reviewContent") String reviewContent);

	public void deleteReview(@Param("videoId") String videoId, @Param("reviewId") String reviewId);
	
	public void like(@Param("videoId") String videoId, @Param("reviewId") String reviewId);
	
	public void dislike(@Param("videoId") String videoId, @Param("reviewId") String reviewId);
	
	public void undoLike(@Param("videoId") String videoId, @Param("reviewId") String reviewId);
	
	public void undoDislike(@Param("videoId") String videoId, @Param("reviewId") String reviewId);
	
	public void liked(@Param("userId") String userId, @Param("reviewId") String reviewId);

	public void disliked(@Param("userId") String userId, @Param("reviewId") String reviewId);
	
	public List<Like> selectAllLike(String userId);
	
	public void undo(@Param("userId") String userId, @Param("reviewId") String reviewId);
}
