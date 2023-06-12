package com.ssafy.ssafit.model.dto;

public class User {
	private int rank;
	private int userSeq;
	private String id;
	private String password;
	private String name;
	private String email;
	private int age;
	private String regDate;
	private String modifyDate;
	private int exerciseCode;
	private String exerciseName;
	private String originalImageName;
	private String imageName;

	public User() {
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
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

	public String getOriginalImageName() {
		return originalImageName;
	}

	public void setOriginalImageName(String originalImageName) {
		this.originalImageName = originalImageName;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public String toString() {
		return "User [rank=" + rank + ", userSeq=" + userSeq + ", id=" + id + ", password=" + password + ", name="
				+ name + ", email=" + email + ", age=" + age + ", regDate=" + regDate + ", modifyDate=" + modifyDate
				+ ", exerciseCode=" + exerciseCode + ", exerciseName=" + exerciseName + ", originalImageName="
				+ originalImageName + ", imageName=" + imageName + "]";
	}

		

}