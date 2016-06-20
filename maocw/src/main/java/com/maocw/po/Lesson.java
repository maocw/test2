package com.maocw.po;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Lesson")
public class Lesson implements java.io.Serializable{

	private Long id;
	private String lessoName;
	private String space;
	private String lessonClass;
	private String teacherName;
	private String lessonTime;
	private Integer serial;
	
	public Lesson(){
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

	@Column(name = "lesson_name",  nullable = false, length = 64)
	public String getLessoName() {
		return lessoName;
	}

	public void setLessoName(String lessoName) {
		this.lessoName = lessoName;
	}

	@Column(name = "space",  nullable = false, length = 64)
	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	@Column(name = "lesson_class",  nullable = false, length = 64)
	public String getLessonClass() {
		return lessonClass;
	}

	public void setLessonClass(String lessonClass) {
		this.lessonClass = lessonClass;
	}
	
	@Column(name = "teacher_name",  nullable = false, length = 64)
	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	@Column(name = "lesson_time",  nullable = false, length = 64)
	public String getLessonTime() {
		return lessonTime;
	}

	public void setLessonTime(String lessonTime) {
		this.lessonTime = lessonTime;
	}
	
	@Column(name = "serial",  nullable = false, length = 32)
	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}
}
