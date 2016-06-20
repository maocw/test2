package com.maocw.po;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RStudentLessongrade")
public class RStudentLesson implements java.io.Serializable{

	private Long id;
	private String studentNumber;
	private String score;
	private String teacherName;
	private String lessoName;
	private Integer absenTimes;
	private Integer totalTimes;
	
	public RStudentLesson(){
		super();
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "student_number",  nullable = false, length = 64)
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	@Column(name = "teacher_name",  nullable = false, length = 64)
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	@Column(name = "score",  nullable = false, length = 64)
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
	@Column(name = "lesson_name",  nullable = false, length = 64)
	public String getLessoName() {
		return lessoName;
	}
	public void setLessoName(String lessoName) {
		this.lessoName = lessoName;
	}

	@Column(name = "absent_times",  nullable = false, length = 32)
	public int getAbsenTimes() {
		return absenTimes;
	}

	public void setAbsenTimes(int absenTimes) {
		this.absenTimes = absenTimes;
	}

	@Column(name = "total_times",  nullable = false, length = 32)
	public int getTotalTimes() {
		return totalTimes;
	}

	public void setTotalTimes(int totalTimes) {
		this.totalTimes = totalTimes;
	}
	
	
}
