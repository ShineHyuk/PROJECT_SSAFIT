package com.ssafy.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Like;
import com.ssafy.ssafit.model.dto.Review;
import com.ssafy.ssafit.model.service.ReviewService;

@RestController
@RequestMapping("/ssafit")
public class ReviewRestController {

	@Autowired
	private ReviewService reviewService;

	@PostMapping("/review/{videoId}")
	public ResponseEntity<Review> write(@RequestBody Review review) {
		reviewService.addReview(review);
		return new ResponseEntity<Review>(review, HttpStatus.CREATED);
	}

	@GetMapping("/review/{videoId}")
	public List<Review> reviewListByVideoId(@PathVariable String videoId) {
		return reviewService.selectByVideoId(videoId);
	}
	
	@GetMapping("/review/disliked")
	public List<Review> dislikedReviewList() {
		return reviewService.selectDislikedReviews();
	}

	@GetMapping("/review/{userId}/like")
	public List<Like> LikeList(@PathVariable String userId) {
		return reviewService.getAllLike(userId);
	}

	@DeleteMapping("/review/{videoId}/{reviewId}")
	public ResponseEntity<Void> delete(@PathVariable String videoId, @PathVariable String reviewId) {
		reviewService.deleteReview(videoId, reviewId);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PutMapping("/review/{videoId}/{reviewId}")
	public ResponseEntity<Void> update(@PathVariable String videoId, @PathVariable String reviewId, Review review) {
		reviewService.modifyReview(videoId, reviewId, review.getContent());

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PutMapping("/review/{videoId}/{reviewId}/{userId}/like")
	public ResponseEntity<Void> updateLikeCount(@PathVariable String videoId, @PathVariable String reviewId,
			@PathVariable String userId) {
		reviewService.like(videoId, reviewId);
		reviewService.liked(userId, reviewId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PutMapping("/review/{videoId}/{reviewId}/{userId}/dislike")
	public ResponseEntity<Void> updateDislikeCount(@PathVariable String videoId, @PathVariable String reviewId,
			@PathVariable String userId) {
		reviewService.dislike(videoId, reviewId);
		reviewService.disliked(userId, reviewId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/review/{videoId}/{reviewId}/{userId}/{which}")
	public ResponseEntity<Void> undoLike(@PathVariable String videoId, @PathVariable String reviewId,
			@PathVariable String userId, @PathVariable String which) {
		reviewService.undo(userId, reviewId, videoId, which);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
