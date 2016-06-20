package com.maocw.common.json;

import java.util.List;

import com.maocw.dto.LessonDto;
import com.maocw.dto.StudentDto;

public class StudentResult extends BaseResult<StudentDto>{

	public StudentResult(){
		super();
	}

	public StudentResult(Boolean success, StudentDto data) {
		super(success, data);
		// TODO Auto-generated constructor stub
	}

	public StudentResult(Boolean success, List<StudentDto> rows, Integer total) {
		super(success, rows, total);
		// TODO Auto-generated constructor stub
	}

	public StudentResult(Boolean success, String message, StudentDto data, List<StudentDto> rows, Integer total) {
		super(success, message, data, rows, total);
		// TODO Auto-generated constructor stub
	}

	public StudentResult(Boolean success, String message, StudentDto data) {
		super(success, message, data);
		// TODO Auto-generated constructor stub
	}

	public StudentResult(Boolean success, String message, List<StudentDto> rows) {
		super(success, message, rows);
		// TODO Auto-generated constructor stub
	}

	public StudentResult(Boolean success, String message) {
		super(success, message);
		// TODO Auto-generated constructor stub
	}

	public StudentResult(Boolean success) {
		super(success);
		// TODO Auto-generated constructor stub
	}
}
