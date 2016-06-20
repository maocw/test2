package com.maocw.common.json;

import java.util.List;

import com.maocw.dto.LessonDto;

public class LessonResult extends BaseResult<LessonDto>{

	public LessonResult(){
		super();
	}

	public LessonResult(Boolean success, LessonDto data) {
		super(success, data);
		// TODO Auto-generated constructor stub
	}

	public LessonResult(Boolean success, List<LessonDto> rows, Integer total) {
		super(success, rows, total);
		// TODO Auto-generated constructor stub
	}

	public LessonResult(Boolean success, String message, LessonDto data, List<LessonDto> rows, Integer total) {
		super(success, message, data, rows, total);
		// TODO Auto-generated constructor stub
	}

	public LessonResult(Boolean success, String message, LessonDto data) {
		super(success, message, data);
		// TODO Auto-generated constructor stub
	}

	public LessonResult(Boolean success, String message, List<LessonDto> rows) {
		super(success, message, rows);
		// TODO Auto-generated constructor stub
	}

	public LessonResult(Boolean success, String message) {
		super(success, message);
		// TODO Auto-generated constructor stub
	}

	public LessonResult(Boolean success) {
		super(success);
		// TODO Auto-generated constructor stub
	}
}
