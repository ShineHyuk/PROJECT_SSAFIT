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

import com.ssafy.ssafit.model.dto.Comment;
import com.ssafy.ssafit.model.service.CommentService;

@RestController
@RequestMapping("/ssafit")
public class CommentRestController {
	
	@Autowired
	CommentService cs;
	
	@PostMapping("/comment")
	public ResponseEntity<?> write( Comment comment) {
		try {
			System.out.println(comment);
			cs.writeComment(comment);
			Comment c=cs.getCommentByCommentId(comment.getCommentId());
			System.out.println(c);
			return new ResponseEntity<Comment>(c, HttpStatus.CREATED);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/comment/{boardId}")
	public ResponseEntity<?> selectCommentByBoardId(@PathVariable int boardId) {
		try {
			System.out.println(boardId);
			System.out.println(cs.getCommentListByBoardId(boardId));
			List<Comment> comments= cs.getCommentListByBoardId(boardId);
			if (comments != null && comments.size() >0) {
				return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/comment")
	public ResponseEntity<?> selectAllComment() {
		try {
			List<Comment> comments = cs.getCommentList();
			System.out.println(comments);
			if (comments != null && comments.size() > 0) {
				return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PutMapping("/comment")
	public ResponseEntity<?> update(@RequestBody Comment comment) {
		try {
			int result = cs.updateComment(comment);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<?> delete(@PathVariable int commentId) {
		try {
			int result = cs.deleteComment(commentId);
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
