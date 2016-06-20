package com.maocw.common.json;

import java.util.List;

public class BaseResult<T> {
	
	private Boolean success;
	private Integer total;
	private String message;
	private String errorCode;
	private List<T> rows;
	private T data;
	
	public BaseResult() {
		super();
	}

	public BaseResult(Boolean success) {
		this(success,null,null,null,null);
	}
	
	public BaseResult(Boolean success, String message) {
		this(success,message,null,null,null);
	}
	
	public BaseResult(Boolean success, T data) {
		this(success, null, data, null,null);
	}
	
	public BaseResult(Boolean success, String message, T data) {
		this(success, message, data, null,null);
	}
	
	public BaseResult(Boolean success,String message, List<T> rows) {
		this(success, message, null, rows, null);
	}
	
	public BaseResult(Boolean success,List<T> rows,Integer total) {
		this(success,null, null, rows,total);
	}
	
	public BaseResult(Boolean success, String message, T data, List<T> rows,Integer total) {
		super();
		this.success = success;
		this.message = message;
		this.data = data;
		this.rows = rows;
		this.total = total;
	}

	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
