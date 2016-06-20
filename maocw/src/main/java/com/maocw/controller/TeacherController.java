package com.maocw.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.maocw.QRcode.BuildQRcode;
import com.maocw.QRcode.LatAndLngUtil;
import com.maocw.common.json.LessonResult;
import com.maocw.common.json.TeacherResult;
import com.maocw.dto.LessonDto;
import com.maocw.dto.StudentDto;
import com.maocw.dto.TeacherDto;
import com.maocw.dto.StudentList;
import com.maocw.service.TeacherService;
import com.maocw.util.StreamDrainer;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public TeacherResult checkLogin(@RequestBody TeacherDto teacher){
		TeacherDto teacherDto = teacherService.checkLogin(teacher.getUserName(),teacher.getPassword());
		if (teacherDto!=null && !"".equals(teacherDto)) {
			return new TeacherResult(Boolean.TRUE, teacherDto);	
		}
		return new TeacherResult(Boolean.FALSE,"不存在该教师用户");
	}
	
	@RequestMapping(value="/getLesson",method=RequestMethod.POST)
	@ResponseBody
	public LessonResult getLesson(@RequestParam("teacherName") String teacherName){
		List<LessonDto> lessonDtos = teacherService.getLesson(teacherName);
		if (lessonDtos!=null && lessonDtos.size()!=0) {
			return new LessonResult(Boolean.TRUE, lessonDtos, lessonDtos.size());
		}
		return new LessonResult(Boolean.FALSE,"Not Found");
	}
	
	
	@RequestMapping(value="/buildQR",method=RequestMethod.GET)
	@ResponseBody
	public TeacherResult buildQRcode(@RequestParam("message") String message,HttpServletResponse response){
		return null;
//		try {
//		     String path = "C:/Users/mao/Desktop";
//		     MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
//
//		     Map hints = new HashMap();
//		     hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//		     BitMatrix bitMatrix = multiFormatWriter.encode(message, BarcodeFormat.QR_CODE, 400, 400,hints);
//		     File file1 = new File(path,"testQRcode.jpg");
//		    // BuildQRcode.writeToFile(bitMatrix, "jpg", file1);
//		     BuildQRcode.writeToStream(bitMatrix, "jpg", response.getOutputStream());
//		 }catch(Exception e) {
//		     e.printStackTrace();
//		     return new TeacherResult(Boolean.FALSE);
//		 }
//		return new TeacherResult(Boolean.TRUE);
	}

	@RequestMapping(value="/getLatLng",method=RequestMethod.POST)
	@ResponseBody
	public TeacherResult getLocation(@RequestBody TeacherDto teacherDto){
		try {
			Object[] addr = LatAndLngUtil.getCoordinate(teacherDto.getAddress());
			teacherDto.setLng((double)addr[0]);//经度
			teacherDto.setLat((double)addr[1]);//纬度
		} catch (IOException e) {
			e.printStackTrace();
			return new TeacherResult(Boolean.FALSE);
		}
		return new TeacherResult(Boolean.TRUE,teacherDto);
	}
	
	@RequestMapping(value="/exportExcel",method=RequestMethod.PUT)
	@ResponseBody
	public TeacherResult exportExcel(@RequestBody List<HashMap<String, Object>> studentList){
		try {
			teacherService.exportExcel(studentList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
	
	@RequestMapping(value="/logOut",method=RequestMethod.POST)
	@ResponseBody
	public TeacherResult logOut(@RequestBody List<HashMap<String, Object>> studentList){
		List<StudentDto> studentDtos = new ArrayList<>();		
		for (HashMap<String, Object> hashMap : studentList) {
			StudentDto studentDto = new StudentDto();
			studentDto.setNumber((String)hashMap.get("number"));
			studentDto.setStatus((Boolean)hashMap.get("status"));
			studentDto.setLessoName("java");
			studentDtos.add(studentDto);
		}
		teacherService.logOut(studentDtos);
		return new TeacherResult(Boolean.TRUE);
	}
	
	@RequestMapping(value="/startWifi",method=RequestMethod.POST)
	@ResponseBody
	public TeacherResult startWifi(){
		String[] cmd = new String[] { "cmd.exe", "/C", "netsh wlan start hostednetwork" };
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			new Thread(new StreamDrainer(process.getInputStream())).start();
	        new Thread(new StreamDrainer(process.getErrorStream())).start();   
	        process.getOutputStream().close();
	        int exitValue = process.waitFor();
	        System.out.println("返回值：" + exitValue);
		} catch (Exception e) {
			e.printStackTrace();
			return new TeacherResult(Boolean.FALSE);
		}
		return new TeacherResult(Boolean.TRUE);
	}
	
	@RequestMapping(value="/closeWifi",method=RequestMethod.POST)
	@ResponseBody
	public TeacherResult closetWifi(){
		String[] cmd = new String[] { "cmd.exe", "/C", "netsh wlan stop hostednetwork" };
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			new Thread(new StreamDrainer(process.getInputStream())).start();
	        new Thread(new StreamDrainer(process.getErrorStream())).start();   
	        process.getOutputStream().close();
	        int exitValue = process.waitFor();
	        System.out.println("返回值：" + exitValue);
		} catch (Exception e) {
			e.printStackTrace();
			return new TeacherResult(Boolean.FALSE);
		}
		return new TeacherResult(Boolean.TRUE);
		
	}
	
	@RequestMapping(value="/initialize",method=RequestMethod.GET)
	@ResponseBody
	public TeacherResult initialize(@RequestParam("lessoName") String lessoName){
		if (lessoName!=null) {
			teacherService.initialize(lessoName);
		}	
		return new TeacherResult(Boolean.TRUE);	
	}
}
