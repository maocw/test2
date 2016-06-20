package com.maocw.dto;

public class TeacherDto extends AbstractDto{


	private String teacherName;
	private String userName;
	private String password;
	
	/**
	 * 服务端经度
	 */
	private double lng;
	
	/**
	 * 服务端纬度
	 */
	private double lat;
	
	/**
	 * 服务端当前地址
	 */
	private String address;
	
	public TeacherDto(){
		
	}
	
	public TeacherDto(Long id){
		super(id);
	}	
	public TeacherDto(Long id, String teacherName, String userName, String password) {
		super(id);
		this.teacherName = teacherName;
		this.userName = userName;
		this.password = password;
	}


	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
