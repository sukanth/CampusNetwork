package com.campusnetwork.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.campusnetwork.exception.CNException;
import com.campusnetwork.models.AjaxResponse;
import com.campusnetwork.models.Instructor;
import com.campusnetwork.models.Marks;
import com.campusnetwork.service.PerformanceService;

@Controller
@RequestMapping("/entry/Performance")
public class PerformanceController extends BaseController{
	
	@Autowired
	PerformanceService performanceService;
	
	@RequestMapping("/uploadMarks")
	public ModelAndView loadUploadMarks(HttpServletRequest request){
		ModelAndView view = new ModelAndView("uploadMarks");
		
		try {
			HttpSession session = request.getSession();
			Instructor instructor = (Instructor)session.getAttribute("instructor");
		
			List<String> availableCourses = performanceService.getAvailableCourses(instructor.getInstructorId());
			view.addObject("availableCourses", availableCourses);
		}
		catch(CNException ex) {
			view = handleException(ex);
		}
		return view;
	}
	
	
	@RequestMapping(value = {"/getAssessement"})
	public 	@ResponseBody AjaxResponse getAssessement(@RequestParam("courseId") String courseId,@RequestParam("courseType")String courseType){
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> responseData = new HashMap<String,Object>();
		ajaxResponse.setResponseData(responseData);
		
		try {
			List<String> assessements = performanceService.getAssessement(courseId, courseType);
			responseData.put("assessements", assessements);
		}

		catch(CNException ex) {
			ajaxResponse = handleAjaxException(ex);
		}
		return ajaxResponse;
	}
	
	

	@RequestMapping(value = "uploadMarksData", method = RequestMethod.POST)
	public ModelAndView saveimage(@RequestParam("dataFile") CommonsMultipartFile file, @ModelAttribute Marks marks){
		ByteArrayInputStream bis = null;
		ModelAndView view = new ModelAndView("uploadMarks");
		HSSFWorkbook workbook = null;
		try {
			bis = new ByteArrayInputStream(file.getBytes());
			if (file.getOriginalFilename().endsWith("xls")) {
			    workbook = new HSSFWorkbook(bis);
			}else {
			    throw new IllegalArgumentException("Received file does not have a standard excel extension.");
			}
			performanceService.uploadMarks(workbook, marks);
		}
		catch(CNException ex) {
			view = handleException(ex);
		} catch (IOException e) {
			
		}
		return view;
	}

}
