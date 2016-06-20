package com.maocw.dto;

public class PageInfo {
	
	private String sortColumn;
	
	private String sortType;
	
	public PageInfo(){
		
	}
	
	public PageInfo(String sortColumn, String sortType){
		this.sortColumn = sortColumn;
		this.sortType = sortType;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	

}
