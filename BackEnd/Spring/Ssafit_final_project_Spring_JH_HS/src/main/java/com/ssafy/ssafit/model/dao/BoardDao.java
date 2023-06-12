package com.ssafy.ssafit.model.dao;

import java.util.HashMap;
import java.util.List;

import com.ssafy.ssafit.model.dto.Board;

public interface BoardDao {
	// 게시글 목록
	public List<Board> selectList(HashMap<String, String> params);

	public int insertBoard(Board board);

	public int updateBoard(Board board);

	public int deleteBoard(int boardId);
	
	public Board detailBoard(int boardId);

	public void updateViewCnt(int boardId);
}
