package com.ssafy.ssafit.model.dto;

public class Board {
	private int boardId;
	private String writer;
	private String title;
	private String content;
	private int viewCnt;
	private String regDate;
	
	public Board() {
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Board [boardId=" + boardId + ", writer=" + writer + ", title=" + title + ", content=" + content
				+ ", viewCnt=" + viewCnt + ", regDate=" + regDate + "]";
	}
	
	
}
