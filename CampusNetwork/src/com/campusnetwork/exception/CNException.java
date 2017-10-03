package com.campusnetwork.exception;

import com.campusnetwork.models.ErrorModel;

public class CNException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4487030064123842098L;
	
	private ErrorModel errorModel;
	

	public CNException() {
		super();
	}
	
	public CNException(String message, Exception exception) {
		super();
		this.errorModel = new ErrorModel(message, exception);
	}

	public ErrorModel getErrorModel() {
		return errorModel;
	}

	public void setErrorModel(ErrorModel errorModel) {
		this.errorModel = errorModel;
	}
	
	

}
