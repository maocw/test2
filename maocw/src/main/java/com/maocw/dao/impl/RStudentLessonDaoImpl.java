package com.maocw.dao.impl;

import java.util.List;


import org.springframework.stereotype.Repository;

import com.maocw.dao.RStudentLessonDao;
import com.maocw.dto.RStudentLessonDto;
import com.maocw.dto.StudentDto;
import com.maocw.po.RStudentLesson;

@Repository
public class RStudentLessonDaoImpl extends AbstractDaoImpl<RStudentLesson> implements RStudentLessonDao{

	public RStudentLessonDaoImpl() {
		super(RStudentLesson.class);
		// TODO Auto-generated constructor stub
	}

	public RStudentLessonDto getCount(String number, String lessoName) {
		String hql = "select new com.maocw.dto.RStudentLessonDto(a.absenTimes,a.totalTimes) from RStudentLesson a"
				+ " where a.lessoName = '" + lessoName + "' and a.studentNumber = '" + number + "'";
		List<RStudentLessonDto> rStudentLessonDtos = this.hibernateTemplate.find(hql);
		if (rStudentLessonDtos==null || rStudentLessonDtos.size()==0) {
			return null;
		}
		return rStudentLessonDtos.get(0);
	}

	public void deleteByNumber(String number) {
		String hql = " delete from RStudentLesson where studentNumber = '" + number + "'";
		this.hibernateTemplate.bulkUpdate(hql);
	}

	public void updateScore(StudentDto studentDto) {
		String hql = " update RStudentLesson a set a.score = "+ studentDto.getScore() + " a.lessoName = java"+
				" where a.studentNumber = '"+ studentDto.getNumber() + "'";
		this.hibernateTemplate.bulkUpdate(hql);
	}

	public  void logOut(StudentDto studentDto) {
		String hql =" update RStudentLesson a set a.totalTimes = a.totalTimes+1";
		String where =" where a.lessoName = '"+ studentDto.getLessoName()+"' and a.studentNumber='"+studentDto.getNumber()+"'";
		if (!studentDto.getStatus()) {
			hql+= ",a.absenTimes = a.absenTimes+1";
		}
		this.hibernateTemplate.bulkUpdate(hql+where);
	}

	public void initialize(String lessoName) {
		String getNumbers = " select a.studentNumber from RStudentLesson a where a. lessoName = '" + lessoName + "'";
		List<String> list = this.hibernateTemplate.find(getNumbers);
		String number = "";
		for (String string : list) {
			number+="'"+string+"',";
		}
		number = number.substring(0,number.length()-1);
		String hql =" update Student b set b.status = false where b.number in (" + number + ")";
		this.hibernateTemplate.bulkUpdate(hql);
	}

}
