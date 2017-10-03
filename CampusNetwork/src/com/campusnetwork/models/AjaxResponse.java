package com.campusnetwork.models;

import java.io.Serializable;
import java.util.Map;


public class AjaxResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7406612024294579745L;
	
	
	Map<String, Object> responseData = null;

	public Map<String, Object> getResponseData() {
		return responseData;
	}

	public void setResponseData(Map<String, Object> responseData) {
		this.responseData = responseData;
	}
	
	


}
