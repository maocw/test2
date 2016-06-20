package com.maocw.service.impl;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maocw.common.json.StudentResult;
import com.maocw.dao.impl.RStudentLessonDaoImpl;
import com.maocw.dao.impl.StudentDaoImpl;
import com.maocw.dto.PageInfo;
import com.maocw.dto.RStudentLessonDto;
import com.maocw.dto.StudentDto;
import com.maocw.po.RStudentLesson;
import com.maocw.po.Student;
import com.maocw.service.StudentService;
import com.maocw.servlet.CodeServlet;
import com.maocw.util.PoDtoUtil;

@Service
public class StudentServiceImpl extends AbstractServiceImpl implements StudentService{

	public static Integer student_heartbeat_timeout = 30*1000+100;
	
	
	@Autowired
	private StudentDaoImpl studentDaoImpl;
	
	@Autowired 
	private RStudentLessonDaoImpl rStudentLessonDaoImpl;
	
	@Override
	public StudentDto load(String number) {
		HashMap<String,Object> property = new HashMap<String,Object>();
		property.put("number", number);
		Student student =  studentDaoImpl.loadByAttributes(property);
		return PoDtoUtil.studentPo2Dto(student);
	}

	@Override
	public void save(StudentDto studentDto) {	
		Student student = PoDtoUtil.studentDto2po(studentDto);
		studentDaoImpl.saveOrUpdate(student);
	}

	@Override
	public List<StudentDto> list(StudentDto studentDto1,String lessoName) {
		PageInfo pageInfo = new  PageInfo();
		if (studentDto1.getSort()!=null) {
			pageInfo.setSortColumn(studentDto1.getSort());
		}
		if (studentDto1.getOrder()!=null) {
			pageInfo.setSortType(studentDto1.getOrder());
		}
		List<StudentDto> studentDtos = studentDaoImpl.list(studentDto1,pageInfo);
		List<StudentDto> studentDtosReturn = new ArrayList<StudentDto>();
		ArrayList<StudentDto> lists = new ArrayList<StudentDto>();
		for (StudentDto studentDto : studentDtos) {
			RStudentLessonDto rStudentLessonDto = rStudentLessonDaoImpl.getCount(studentDto.getNumber(),lessoName);
			if (rStudentLessonDto==null) {
				continue;
			}
			studentDto.setAbsenTimes(rStudentLessonDto.getAbsenTimes());
			studentDto.setTotalTimes(rStudentLessonDto.getTotalTimes());
			NumberFormat nt = NumberFormat.getPercentInstance();//设置百分数精确度2即保留两位小数
			nt.setMinimumFractionDigits(2);
			float s = (float) studentDto.getAbsenTimes()/studentDto.getTotalTimes();
			studentDto.setRate(nt.format(s));
			studentDtosReturn.add(studentDto);
			if (studentDto1.getAbsenTimes()!=null && studentDto.getAbsenTimes()>=studentDto1.getAbsenTimes()) {
				lists.add(studentDto);
			}
		}
		if (studentDto1.getAbsenTimes()!=null) {
			return lists;
		}
		return studentDtosReturn;
	}

	@Override
	public void deleteByIds(String studentIds) {
		String ids[] = studentIds.split(",");
		studentDaoImpl.deleteByIds(ids);
		for (String  id : ids) {
			HashMap<String,Object> properties = new HashMap<String,Object>();
			properties.put("number", id);
			Student student = studentDaoImpl.loadByAttributes(properties);
			studentDaoImpl.delete(student);
			rStudentLessonDaoImpl.deleteByNumber(id);
		}
	}

	@Override
	public void studentLogin(String message, String number) {
		int i = 0;
		for (; i < message.length(); i++) {
			if (message.charAt(i)==' ') {
				break;
			}
		}
		String lessoName = message.substring(i, message.length());
		studentDaoImpl.login(number);
		loginTimeMap.put(number, new Date().getTime());
		System.out.println( number +" login at time "+ new Date().getTime());
	}

	@Override
	public void heartBeat(String number) {
		Long time = StudentService.loginTimeMap.get(number);
		Long newTime  = new Date().getTime();
		if (newTime-time>student_heartbeat_timeout) {
			updateStatus(number);
		}else {
			System.out.println("at "+ newTime + " student "+ number+" heartBeat...");
		}
		StudentService.loginTimeMap.put(number, new Date().getTime());
	}
	
	public void updateStatus(String number){
		HashMap<String,Object> property = new HashMap<String,Object>();
		property.put("number", number);
		Student student =  studentDaoImpl.loadByAttributes(property);
		if (student.getStatus()) {
			student.setStatus(false);
			studentDaoImpl.update(student);
		}
	}

}
