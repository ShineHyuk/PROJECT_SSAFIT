package com.ssafy.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Follow;
import com.ssafy.ssafit.model.service.FollowService;

@RestController
@RequestMapping("/ssafit")
public class FollowRestController {

	@Autowired
	FollowService fs;
	
	@GetMapping("/follow/{userId}")
	public ResponseEntity<?> followList(@PathVariable String userId) {
		try {
			System.out.println("팔로우 유저아이디:"+userId);
			List<Follow> follows = fs.getAllFollowList(userId);
			System.out.println(follows);
			if (follows != null && follows.size() > 0) {
				return new ResponseEntity<List<Follow>>(follows, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/follow/detail/{followId}")
	public ResponseEntity<?> selectFollow(int followId){
		try {
			Follow follow = fs.getFollowByFollowId(followId);
			System.out.println(follow);
			if (follow != null) {
				return new ResponseEntity<Follow>(follow, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/follow")
	public ResponseEntity<?> addFollow(Follow follow){
		try {
			fs.addFollower(follow);
			Follow f=fs.getFollowByFollowId(follow.getFollowId());
			return new ResponseEntity<Follow>(f, HttpStatus.CREATED);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("/follow")
	public ResponseEntity<?> delete(Follow follow){
		try {
			System.out.println("처음들어온 삭제요청:"+follow);
			int result = fs.removeFollower(follow);
			System.out.println("삭제완료 후 db:"+fs.getAllFollowList("ssafy"));
			return new ResponseEntity<Integer>(result, HttpStatus.OK);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Sorry: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
