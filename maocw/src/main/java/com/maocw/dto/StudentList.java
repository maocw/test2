package com.maocw.dto;

import java.util.HashMap;
import java.util.List;


public class StudentList {
	
	private List<HashMap<String, Object>> studentDtos;
	
	public StudentList() {
		super();
	}
	
	public StudentList(List<HashMap<String, Object>> studentDtos) {
		super();
		this.studentDtos = studentDtos;
	}

	public List<HashMap<String, Object>> getStudentDtos() {
		return studentDtos;
	}

	public void setStudentDtos(List<HashMap<String, Object>> studentDtos) {
		this.studentDtos = studentDtos;
	}
}
