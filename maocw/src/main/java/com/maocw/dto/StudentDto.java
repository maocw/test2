package com.maocw.dto;

public class StudentDto extends AbstractDto{
	
	private String name;
	private String gradeClass;
	private String number;
	private Boolean status;
	
	private Integer absenTimes;
	private Integer totalTimes; 
	private String rate;
	
	private String sort;
	private String order;
	
	private String score;
	private String lessoName;
	
	public StudentDto(){
		
	}
	
	public StudentDto(Long id, String name, String gradeClass, String number, Boolean status,String score,String lessoName) {
		super(id);
		this.name = name;
		this.gradeClass = gradeClass;
		this.number = number;
		this.status = status;
		this.score = score;
		this.lessoName = lessoName;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGradeClass() {
		return gradeClass;
	}
	public void setGradeClass(String gradeClass) {
		this.gradeClass = gradeClass;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getAbsenTimes() {
		return absenTimes;
	}

	public void setAbsenTimes(Integer absenTimes) {
		this.absenTimes = absenTimes;
	}

	public Integer getTotalTimes() {
		return totalTimes;
	}

	public void setTotalTimes(Integer totalTimes) {
		this.totalTimes = totalTimes;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getLessoName() {
		return lessoName;
	}

	public void setLessoName(String lessoName) {
		this.lessoName = lessoName;
	}

}
