package com.maocw.dto;


public class LessonDto extends AbstractDto{
	
	private String lessoName;
	private String space;
	private String lessonClass;
	private String teacherName;
	private String lessonTime;
	private Integer serial;

	public String getLessoName() {
		return lessoName;
	}

	
	public LessonDto(){
		
	}
	
	public LessonDto(Long id, String lessoName, String space, String lessonClass, String teacherName, String lessonTime,
			Integer serial) {
		super(id);
		this.lessoName = lessoName;
		this.space = space;
		this.lessonClass = lessonClass;
		this.teacherName = teacherName;
		this.lessonTime = lessonTime;
		this.serial = serial;
	}

	public void setLessoName(String lessoName) {
		this.lessoName = lessoName;
	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public String getLessonClass() {
		return lessonClass;
	}

	public void setLessonClass(String lessonClass) {
		this.lessonClass = lessonClass;
	}
	
	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}


	public String getLessonTime() {
		return lessonTime;
	}


	public void setLessonTime(String lessonTime) {
		this.lessonTime = lessonTime;
	}


	public Integer getSerial() {
		return serial;
	}


	public void setSerial(Integer serial) {
		this.serial = serial;
	}

}
