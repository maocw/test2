package com.maocw.service;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.maocw.dto.LessonDto;
import com.maocw.dto.StudentDto;
import com.maocw.dto.TeacherDto;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public interface TeacherService extends BaseService{

	TeacherDto checkLogin(String userName, String password);

	List<LessonDto> getLesson(String teacherName);

	void exportExcel(List<HashMap<String, Object>> studentList) throws IOException, RowsExceededException, WriteException;

	void logOut(List<StudentDto>  studentDtos);

	void initialize(String lessoName);

}
