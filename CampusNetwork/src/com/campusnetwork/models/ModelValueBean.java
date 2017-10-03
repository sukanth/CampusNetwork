package com.campusnetwork.models;

import java.io.Serializable;

public class ModelValueBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -819169191792791735L;
	
	
	private String key;
	private String value;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
	

}
