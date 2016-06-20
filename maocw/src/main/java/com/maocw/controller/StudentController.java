package com.maocw.controller;

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maocw.common.json.StudentResult;
import com.maocw.dto.StudentDto;
import com.maocw.po.Student;
import com.maocw.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	public static Integer system_heartbeat_timeout = 32*1000+100;
	
	@RequestMapping(value="/login",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public StudentResult studentAttend(@RequestParam("message") String message,@RequestParam("number") final String number){
		studentService.studentLogin(message,number);
		return null;
	}
	
	@RequestMapping(value="/heartBeat",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public StudentResult heartBeat(@RequestParam("number") String number){
		studentService.heartBeat(number);
		return null;		
	}
	
	
	@RequestMapping(value="/selfHeart",method=RequestMethod.PUT)
	@ResponseBody
	public StudentResult selfHeart(){
		if (studentService.loginTimeMap!=null && studentService.loginTimeMap.size()>=1) {
			Set<Entry<String, Long>> set = studentService.loginTimeMap.entrySet();
			for (Entry<String, Long> entry : set) {
				Long time = new Date().getTime();
				if (time-entry.getValue()>system_heartbeat_timeout) {
					studentService.updateStatus(entry.getKey());
					return new StudentResult(Boolean.TRUE,entry.getKey());		
				}
				else {
					System.out.println("at "+ time + " system heart ...");
				}
			}
		}else {
			System.out.println("No Student Login System....");
		}		
		return new StudentResult(Boolean.TRUE);		
	}
	
	
	@RequestMapping(value="/load",method=RequestMethod.GET)
	@ResponseBody
	public StudentResult load(@RequestParam("number") String number){
		StudentDto studentDto = studentService.load(number);
		if (studentDto!=null && !"".equals(studentDto)) {
			return new StudentResult(Boolean.TRUE,studentDto);
		}
		return new StudentResult(Boolean.FALSE);		
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	@ResponseBody
	public StudentResult save( StudentDto studentDto){
		try {
			studentService.save(studentDto);
		} catch (Exception e) {
			e.printStackTrace();
			return new StudentResult(Boolean.FALSE);		
		}		 
		return new StudentResult(Boolean.TRUE);
		
	}
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	@ResponseBody
	public StudentResult search(StudentDto studentDto,@RequestParam("lessoName") String lessoName){	
		List<StudentDto> studentDtos = studentService.list(studentDto,lessoName);
		if (studentDtos==null || studentDtos.size()==0) {
			return new StudentResult(Boolean.FALSE,"暂无数据");
		}
		return new StudentResult(Boolean.TRUE,studentDtos,studentDtos.size());		
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public StudentResult deleteStudent(@RequestParam("studentIds") String studentIds){	
			studentService.deleteByIds(studentIds);
			return new StudentResult(Boolean.TRUE);		
	}

}
