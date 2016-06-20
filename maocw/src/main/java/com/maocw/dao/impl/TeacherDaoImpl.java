package com.maocw.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.maocw.dao.TeacherDao;
import com.maocw.po.Teacher;

@Repository
public class TeacherDaoImpl extends AbstractDaoImpl<Teacher> implements TeacherDao{

	public TeacherDaoImpl() {
		super(Teacher.class);
	}

	public Teacher checkLogin(String userName, String password) {
		HashMap<String,Object> properties = new HashMap<String,Object>();
		properties.put("userName", userName);
		properties.put("password", password);
		return this.loadByAttributes(properties);
	}

}
