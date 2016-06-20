package com.maocw.dto;

public class RStudentLessonDto {

	private Long id;
	private String studentNumber;
	private String score;
	private String teacherName;
	private String lessoName;
	private Integer absenTimes;
	private Integer totalTimes;
	
	private String studentName;
	
	private String studentClass;
	
	public RStudentLessonDto(){
		
	}
	
	public RStudentLessonDto(Long id, String studentNumber, String score, String teacherName, String lessoName,
			Integer absenTimes, Integer totalTimes) {
		super();
		this.id = id;
		this.studentNumber = studentNumber;
		this.score = score;
		this.teacherName = teacherName;
		this.lessoName = lessoName;
		this.absenTimes = absenTimes;
		this.totalTimes = totalTimes;
	}
	
	
	public RStudentLessonDto(Integer absenTimes, Integer totalTimes) {
		super();
		this.absenTimes = absenTimes;
		this.totalTimes = totalTimes;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getLessoName() {
		return lessoName;
	}
	public void setLessoName(String lessoName) {
		this.lessoName = lessoName;
	}
	public Integer getAbsenTimes() {
		return absenTimes;
	}
	public void setAbsenTimes(Integer absenTimes) {
		this.absenTimes = absenTimes;
	}
	public Integer getTotalTimes() {
		return totalTimes;
	}
	public void setTotalTimes(Integer totalTimes) {
		this.totalTimes = totalTimes;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}
}
