package com.ssafy.ssafit.model.dao;

import java.util.List;

import com.ssafy.ssafit.model.dto.Comment;

public interface CommentDao {
	public List<Comment> selectAll();
	
	public List<Comment> selectByBoardId(int boardId);
	
	public Comment selectByCommentId(int commentId);

	public int insertComment(Comment comment);

	public int updateComment(Comment comment);

	public int deleteComment(int commentId);
	
}
