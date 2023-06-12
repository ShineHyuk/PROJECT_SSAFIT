package com.ssafy.ssafit.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.BoardDao;
import com.ssafy.ssafit.model.dto.Board;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	public BoardDao boardDao;

	@Override
	public List<Board> getBoardList(HashMap<String, String> params) {
		return boardDao.selectList(params);
	}

	@Override
	public int writeBoard(Board board) {
		return boardDao.insertBoard(board);
	}

	@Override
	public int updateBoard(Board board) {
		return boardDao.updateBoard(board);
	}

	@Override
	public int deleteBoard(int boardId) {
		return boardDao.deleteBoard(boardId);
	}

	@Override
	public Board boardDetail(int boardId) {
		return boardDao.detailBoard(boardId);
	}

	@Override
	public Board readBoard(int boardId) {
		boardDao.updateViewCnt(boardId);
		return boardDao.detailBoard(boardId);
	}

}
