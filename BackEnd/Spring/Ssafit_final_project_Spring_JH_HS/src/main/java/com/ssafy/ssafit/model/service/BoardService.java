package com.ssafy.ssafit.model.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.ssafit.model.dto.Board;

public interface BoardService {
	public List<Board> getBoardList(HashMap<String, String> params);

	public int writeBoard(Board board);

	public int updateBoard(Board board);

	public int deleteBoard(int boardId);
	
	public Board readBoard(int boardId);
	
	public Board boardDetail(int boardId);
}
