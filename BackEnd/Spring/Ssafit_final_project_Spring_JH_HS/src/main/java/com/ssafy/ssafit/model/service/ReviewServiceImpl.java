package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.ReviewDao;
import com.ssafy.ssafit.model.dao.VideoDao;
import com.ssafy.ssafit.model.dto.Like;
import com.ssafy.ssafit.model.dto.Review;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	public ReviewDao reviewDao;
	
	@Autowired
	public VideoDao videoDao;
	
	@Override
	public void addReview(Review review) {
		reviewDao.insertReview(review);
		videoDao.deductViewCnt(review.getVideoId());
	}

	@Override
	public void modifyReview(String videoId, String reviewId, String reviewContent) {
		reviewDao.updateReview(videoId, reviewId, reviewContent);
		videoDao.deductViewCnt(videoId);
	}

	@Override
	public void deleteReview(String videoId, String reviewId) {
		reviewDao.deleteReview(videoId,reviewId);
		videoDao.deductViewCnt(videoId);
	}

	@Override
	public List<Review> selectByVideoId(String videoId) {
		return reviewDao.selectByVideoId(videoId);
	}

	@Override
	public void like(String videoId, String reviewId) {
		reviewDao.like(videoId, reviewId);
		videoDao.deductViewCnt(videoId);
	}

	@Override
	public void dislike(String videoId, String reviewId) {
		reviewDao.dislike(videoId, reviewId);
		videoDao.deductViewCnt(videoId);
	}

	@Override
	public void liked(String userId, String reviewId) {
		reviewDao.liked(userId, reviewId);
	}

	@Override
	public List<Like> getAllLike(String userId) {
		return reviewDao.selectAllLike(userId);
	}

	@Override
	public void disliked(String userId, String reviewId) {
		reviewDao.disliked(userId, reviewId);
	}

	@Override
	public void undo(String userId, String reviewId, String videoId, String which) {
		reviewDao.undo(userId, reviewId);
		videoDao.deductViewCnt(videoId);
		
		if(which.equals("like")) {
			reviewDao.undoLike(videoId, reviewId);
		} else {
			reviewDao.undoDislike(videoId, reviewId);
		}
	}

	@Override
	public List<Review> selectDislikedReviews() {
		return reviewDao.selectDislikedReviews();
	}

}
