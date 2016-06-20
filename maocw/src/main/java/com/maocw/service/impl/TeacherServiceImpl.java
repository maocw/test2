package com.maocw.service.impl;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maocw.dao.impl.LesssonDaoImpl;
import com.maocw.dao.impl.RStudentLessonDaoImpl;
import com.maocw.dao.impl.TeacherDaoImpl;
import com.maocw.dto.LessonDto;
import com.maocw.dto.StudentDto;
import com.maocw.dto.StudentList;
import com.maocw.dto.TeacherDto;
import com.maocw.po.Lesson;
import com.maocw.po.Teacher;
import com.maocw.service.TeacherService;
import com.maocw.util.PoDtoUtil;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

@Service
public class TeacherServiceImpl extends AbstractServiceImpl implements TeacherService{

	@Autowired
	private TeacherDaoImpl teacherDaoImpl;
	
	@Autowired
	private LesssonDaoImpl lesssonDaoImpl;
	
	@Autowired
	private RStudentLessonDaoImpl rStudentLessonDaoImpl;
	
	@Override
	public TeacherDto checkLogin(String userName, String password) {
		Teacher teacher = teacherDaoImpl.checkLogin(userName,password);
		if (teacher!=null && !"".equals(teacher)) {
			return PoDtoUtil.teacherPo2Dto(teacher);
		}
		return null;
	}

	@Override
	public List<LessonDto> getLesson(String teacherName) {
		List<Lesson> lessons = lesssonDaoImpl.getLessonTable(teacherName);
		return PoDtoUtil.lessonPos2Dtos(lessons);
	}

	@Override
	public void exportExcel(List<HashMap<String, Object>> studentDtos) throws IOException, RowsExceededException, WriteException {
		String path = "C:/Users/mao/Desktop/test.xls";
		WritableWorkbook wwb = Workbook.createWorkbook(new File(path));
		if (wwb!=null) {
			WritableSheet ws = wwb.createSheet("工作表名称", 0);               
            Label label0 = new Label(0,0,"**|**");
            ws.addCell(label0);
            Label label1 = new Label(1,0,"姓名");
            ws.addCell(label1);
            Label label2 = new Label(2,0,"学号");
            ws.addCell(label2);
            Label label3 = new Label(3,0,"班级");
            ws.addCell(label3);
            Label label4 = new Label(4,0,"缺课次数");
            ws.addCell(label4);
            Label label5 = new Label(5,0,"缺课率");
            ws.addCell(label5);
            for(int i=1;i<=studentDtos.size();i++){
            	Label labelC = new Label(0, i, i+"");
            	 ws.addCell(labelC); 
            }
            for(int j=1;j<=studentDtos.size();j++){
        		Label label = new Label(1, j , (String) studentDtos.get(j-1).get("name"));
        		ws.addCell(label);
        	}
            for(int j=1;j<=studentDtos.size();j++){
        		Label label = new Label(2, j , (String) studentDtos.get(j-1).get("number"));
        		ws.addCell(label);
        	}
            for(int j=1;j<=studentDtos.size();j++){
        		Label label = new Label(3, j , (String) studentDtos.get(j-1).get("gradeClass"));
        		ws.addCell(label);
        	}
            for(int j=1;j<=studentDtos.size();j++){
        		Label label = new Label(4, j , studentDtos.get(j-1).get("absenTimes").toString());
        		ws.addCell(label);
        	}
            for(int j=1;j<=studentDtos.size();j++){
        		Label label = new Label(5, j , (String) studentDtos.get(j-1).get("rate"));
        		ws.addCell(label);
        	}
            //从内存中写入文件中    
            wwb.write();    
            //关闭资源，释放内存    
            wwb.close(); 
		}		
	}

	@Override
	public void logOut(List<StudentDto> studentDtos) {
		for (StudentDto studentDto : studentDtos) {
			rStudentLessonDaoImpl.logOut(studentDto);
		}
		
	}

	@Override
	public void initialize(String lessoName) {
		rStudentLessonDaoImpl.initialize(lessoName);
	}

}
