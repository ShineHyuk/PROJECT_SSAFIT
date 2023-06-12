package com.ssafy.ssafit.model.dto;

public class Video {
	private String videoId;
	private String videoTitle;
	private String youtuber;
	private int exerciseCode;
	private String exerciseName;
	private int viewCnt;
	
	public Video() {
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public String getYoutuber() {
		return youtuber;
	}

	public void setYoutuber(String youtuber) {
		this.youtuber = youtuber;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public int getExerciseCode() {
		return exerciseCode;
	}

	public void setExerciseCode(int exerciseCode) {
		this.exerciseCode = exerciseCode;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	@Override
	public String toString() {
		return "Video [videoId=" + videoId + ", videoTitle=" + videoTitle + ", youtuber=" + youtuber
				+ ", exercisePartCode=" + exerciseCode + ", exerciseName=" + exerciseName + ", viewCnt="
				+ viewCnt + "]";
	}
}
