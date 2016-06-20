package com.maocw.dto;

import java.io.Serializable;
import java.lang.Long;
/**
 * 
 * @author lee
 *
 */
public abstract class AbstractDto implements Serializable{

	private Long id;
	


	public AbstractDto(Long id) {
		super();
		this.id = id;
	}

	public AbstractDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
