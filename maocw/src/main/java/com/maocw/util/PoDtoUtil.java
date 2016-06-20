package com.maocw.util;

import java.util.ArrayList;
import java.util.List;

import com.maocw.dao.RStudentLessonDao;
import com.maocw.dto.LessonDto;
import com.maocw.dto.RStudentLessonDto;
import com.maocw.dto.StudentDto;
import com.maocw.dto.TeacherDto;
import com.maocw.po.Lesson;
import com.maocw.po.RStudentLesson;
import com.maocw.po.Student;
import com.maocw.po.Teacher;

public class PoDtoUtil {


	public static LessonDto lessonPo2Dto(Lesson lesson){
		if (lesson == null) {
			return null;
		}
		LessonDto lessonDto = new LessonDto();
		lessonDto.setId(lesson.getId());
		lessonDto.setLessoName(lesson.getLessoName());
		lessonDto.setLessonClass(lesson.getLessonClass());
		lessonDto.setSpace(lesson.getSpace());
		lessonDto.setTeacherName(lesson.getTeacherName());
		lessonDto.setLessonTime(lesson.getLessonTime());
		lessonDto.setSerial(lesson.getSerial());
		return lessonDto;
	}
	
	public static List<LessonDto> lessonPos2Dtos(List<Lesson> lessons){
		List<LessonDto> lessonDtos = new ArrayList<LessonDto>();
		if (null!=lessons && lessons.size()>0) {
			for (Lesson lesson : lessons) {
				LessonDto lessonDto = lessonPo2Dto(lesson);
				if (null!=lessonDto) {
					lessonDtos.add(lessonDto);
				}
			}
		}
		return lessonDtos;
	}
	
	/******************************************Student**************************************************/
	
	
	public static Student studentDto2po(StudentDto studentDto){
		if (studentDto == null) {
			return null;
		}
		Student student = new Student();
		student.setId(studentDto.getId());
		student.setGradeClass(studentDto.getGradeClass());
		student.setName(studentDto.getName());
		student.setNumber(studentDto.getNumber());
		student.setStatus(studentDto.getStatus());
		return student;
	}
	
	public static StudentDto studentPo2Dto(Student student){
		if (student == null) {
			return null;
		}
		StudentDto studentDto = new StudentDto();
		studentDto.setId(student.getId());
		studentDto.setGradeClass(student.getGradeClass());
		studentDto.setName(student.getName());
		studentDto.setNumber(student.getNumber());
		studentDto.setStatus(student.getStatus());
		return studentDto;
	}
	
	public static List<StudentDto> studentPos2Dtos(List<Student> students){
		List<StudentDto> studentDtos = new ArrayList<StudentDto>();
		if (null!=students && students.size()>0) {
			for (Student student : students) {
				StudentDto studentDto = studentPo2Dto(student);
				if (null!=studentDto) {
					studentDtos.add(studentDto);
				}
			}
		}
		return studentDtos;
	}
	
	/******************************************Teacher**************************************************/
	
	public static TeacherDto teacherPo2Dto(Teacher teacher){
		if (teacher == null) {
			return null;
		}
		TeacherDto teacherDto = new TeacherDto();
		teacherDto.setId(teacher.getId());
		teacherDto.setTeacherName(teacher.getTeacherName());
		teacherDto.setUserName(teacher.getUserName());
		teacherDto.setPassword(teacher.getPassword());
		return teacherDto;
	}
	
	/******************************************RStudentLesson**************************************************/
	
	public static RStudentLessonDto rStudentLessonPo2Dto(RStudentLesson rStudentLesson){
		if (rStudentLesson==null) {
			return null;
		}
		RStudentLessonDto rStudentLessonDto = new RStudentLessonDto();
		rStudentLessonDto.setAbsenTimes(rStudentLesson.getAbsenTimes());
		rStudentLessonDto.setId(rStudentLesson.getId());
		rStudentLessonDto.setLessoName(rStudentLesson.getLessoName());
		rStudentLessonDto.setScore(rStudentLesson.getScore());
		rStudentLessonDto.setStudentNumber(rStudentLesson.getStudentNumber());
		rStudentLessonDto.setTeacherName(rStudentLesson.getTeacherName());
		rStudentLessonDto.setTotalTimes(rStudentLesson.getTotalTimes());
		return null;		
	}

}
