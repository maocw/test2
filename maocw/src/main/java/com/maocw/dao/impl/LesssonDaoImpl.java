package com.maocw.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.maocw.dao.LessonDao;
import com.maocw.po.Lesson;

@Repository
public class LesssonDaoImpl extends AbstractDaoImpl<Lesson> implements LessonDao{

	public LesssonDaoImpl() {
		super(Lesson.class);
	}

	public List<Lesson> getLessonTable(String teacherName) {
		String hql = "from Lesson a where a.teacherName = '" + teacherName + "'";
		List<Lesson> lessons = this.hibernateTemplate.find(hql);
		return lessons;
	}
}
