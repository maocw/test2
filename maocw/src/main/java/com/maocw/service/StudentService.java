package com.maocw.service;

import java.util.HashMap;
import java.util.List;

import com.maocw.dto.StudentDto;
import com.maocw.po.Student;

public interface StudentService extends BaseService{
	
	public static HashMap<String,Long> loginTimeMap = new HashMap<String,Long>();

	StudentDto load(String number);

	void save(StudentDto studentDto);

	List<StudentDto> list(StudentDto studentDto,String lessoName);

	void deleteByIds(String studentIds);

	void studentLogin(String message, String number);

	void heartBeat(String number);

	void updateStatus(String number);
}
