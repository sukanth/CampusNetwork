package com.campusnetwork.models;

import java.io.Serializable;

public class ErrorModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8688495511765054071L;
	
	private String message;
	private Exception exception;
	
	public ErrorModel() {
		super();
	}
	
	public ErrorModel(String message, Exception exception) {
		super();
		this.message = message;
		this.exception = exception;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}

}
