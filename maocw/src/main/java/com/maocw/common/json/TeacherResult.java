package com.maocw.common.json;

import java.util.List;

import com.maocw.dto.TeacherDto;

public class TeacherResult extends BaseResult<TeacherDto>{
		
	
	public TeacherResult(){
		super();
	}

	public TeacherResult(Boolean success, TeacherDto data) {
		super(success, data);
		// TODO Auto-generated constructor stub
	}

	public TeacherResult(Boolean success, List<TeacherDto> rows, Integer total) {
		super(success, rows, total);
		// TODO Auto-generated constructor stub
	}

	public TeacherResult(Boolean success, String message, TeacherDto data, List<TeacherDto> rows, Integer total) {
		super(success, message, data, rows, total);
		// TODO Auto-generated constructor stub
	}

	public TeacherResult(Boolean success, String message, TeacherDto data) {
		super(success, message, data);
		// TODO Auto-generated constructor stub
	}

	public TeacherResult(Boolean success, String message, List<TeacherDto> rows) {
		super(success, message, rows);
		// TODO Auto-generated constructor stub
	}

	public TeacherResult(Boolean success, String message) {
		super(success, message);
		// TODO Auto-generated constructor stub
	}

	public TeacherResult(Boolean success) {
		super(success);
		// TODO Auto-generated constructor stub
	}
}
