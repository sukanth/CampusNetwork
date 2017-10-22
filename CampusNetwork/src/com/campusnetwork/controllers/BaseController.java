package com.campusnetwork.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.campusnetwork.exception.CNException;
import com.campusnetwork.models.AjaxResponse;

public class BaseController {
	
public ModelAndView handleException(Exception ex){
	
	ModelAndView view = new ModelAndView("Error");
	if(ex instanceof CNException)
		view.addObject("errMsg", ((CNException) ex).getErrorModel().getMessage());
	else
		view.addObject("errMsg", "Error while performing the operation. Please Try again.");
	return view;
		
}

public AjaxResponse handleAjaxException(Exception ex){
	
	AjaxResponse ajaxResponse = new AjaxResponse();
	Map<String, Object> responseData = new HashMap<String,Object>();
	ajaxResponse.setResponseData(responseData);
	if(ex instanceof CNException)
		responseData.put("errMsg", ((CNException) ex).getErrorModel().getMessage());
	else
		responseData.put("errMsg", "Error while performing the operation. Please Try again.");

	return ajaxResponse;
}

}
