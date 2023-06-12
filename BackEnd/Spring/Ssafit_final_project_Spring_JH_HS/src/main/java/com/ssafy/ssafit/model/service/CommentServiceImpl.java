package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.CommentDao;
import com.ssafy.ssafit.model.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDao commentDao;
	
	@Override
	public List<Comment> getCommentList() {
		return commentDao.selectAll();
	}

	@Override
	public List<Comment> getCommentListByBoardId(int boardId) {
		return commentDao.selectByBoardId(boardId);
	}

	@Override
	public int writeComment(Comment comment) {
		return commentDao.insertComment(comment);
	}

	@Override
	public int updateComment(Comment comment) {
		return commentDao.updateComment(comment);
	}

	@Override
	public int deleteComment(int commentId) {
		return commentDao.deleteComment(commentId);
	}

	@Override
	public Comment getCommentByCommentId(int commentId) {
		
		return commentDao.selectByCommentId(commentId);
	}

}
