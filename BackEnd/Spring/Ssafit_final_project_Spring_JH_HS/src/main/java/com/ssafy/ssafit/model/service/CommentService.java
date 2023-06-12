package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Comment;

public interface CommentService {
	public List<Comment> getCommentList();
	
	public List<Comment> getCommentListByBoardId(int boardId);
	
	public Comment getCommentByCommentId(int commentId);

	public int writeComment(Comment comment);

	public int updateComment(Comment comment);

	public int deleteComment(int commentId);
	
}
