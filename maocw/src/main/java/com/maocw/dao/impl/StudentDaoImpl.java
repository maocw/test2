package com.maocw.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.maocw.dao.StudentDao;
import com.maocw.dto.PageInfo;
import com.maocw.dto.StudentDto;
import com.maocw.po.Student;
import com.maocw.util.PoDtoUtil;


@Repository
public class StudentDaoImpl extends AbstractDaoImpl<Student> implements StudentDao{


	public StudentDaoImpl() {
		super(Student.class);
	}
	
	public DetachedCriteria getSearchCondition( Student student,PageInfo pageInfo){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Student.class);
		
		if(null!=student)
		{
			if(null!=student.getId() && !"".equals(student.getId()))
			{
				detachedCriteria.add(Property.forName("id").eq(student.getId()));
			}
			
			if(null!=student.getName() && !"".equals(student.getName()))
			{
				detachedCriteria.add(Property.forName("name").eq(student.getName()));
			}
			
			if(null!=student.getGradeClass() && !"".equals(student.getGradeClass()))
			{
				detachedCriteria.add(Property.forName("gradeClass").eq(student.getGradeClass()));
			}
			
			if(null!=student.getNumber()&& !"".equals(student.getNumber()))
			{
				detachedCriteria.add(Property.forName("number").eq(student.getNumber()));
			}
									
		}			
		if (null != pageInfo.getSortColumn() && !"".equals(pageInfo.getSortColumn())) {
			String sortColumn = pageInfo.getSortColumn();
			if("number".equals(sortColumn)){
				sortColumn = "number";
			}else if("gradeClass".equals(sortColumn)){
				sortColumn = "gradeClass";
			}else if("name".equals(sortColumn)){
				sortColumn = "name";
			}
			if("DESC".equals(pageInfo.getSortType().toUpperCase()))
				detachedCriteria.addOrder(Order.desc(sortColumn));
			else
				detachedCriteria.addOrder(Order.asc(sortColumn));
		}
		return detachedCriteria;
	}

	public List<StudentDto> list(StudentDto studentDto,PageInfo pageInfo) {
		
		DetachedCriteria detachedCriteria = getSearchCondition(PoDtoUtil.studentDto2po(studentDto),pageInfo);
		List<Student>  list = super.hibernateTemplate.findByCriteria(detachedCriteria);
		return PoDtoUtil.studentPos2Dtos(list);
	}

	public void login(String number) {
		String hql = " update Student a set a.status = true"+
				" where a.number = '"+ number + "'";
		this.hibernateTemplate.bulkUpdate(hql);
	}


}
