package com.campusnetwork.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
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
import com.campusnetwork.models.Course;
import com.campusnetwork.models.CoursePlan;
import com.campusnetwork.models.Instructor;
import com.campusnetwork.service.InstructorService;
import com.campusnetwork.service.PerformanceService;
import com.campusnetwork.utils.DateUtils;

@Controller
@RequestMapping("/entry/instructor")
public class InstructorController extends BaseController{
	
	@Autowired
	InstructorService instructorService;
	@Autowired
	PerformanceService performanceService;
	
	@RequestMapping("/home")
	public ModelAndView loadStudentHome(HttpServletRequest request){
		
		ModelAndView view = new ModelAndView("instructorHome");
		
		try {
			HttpSession session = request.getSession();
			Instructor instructor = (Instructor)session.getAttribute("instructor");
			
			List<Course> assignedCourses = instructorService.getAssignedCourses(instructor.getInstructorId());
			view.addObject("assignedCourses", assignedCourses);
		

		}
		catch(CNException ex) {
			view = handleException(ex);
		}
		return view;
	}
	
	@RequestMapping(value = {"/courseSchedule"})
	public 	@ResponseBody AjaxResponse getCourseSchedule(@RequestParam("courseId") String courseId){
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> responseData = new HashMap<String,Object>();
		ajaxResponse.setResponseData(responseData);
		
		try {
		List<CoursePlan> courseSchedule = instructorService.getCourseSchedule(courseId);
		responseData.put("courseSchedule", courseSchedule);
		}
		catch(CNException ex) {
			ajaxResponse = handleAjaxException(ex);
		}
		return ajaxResponse;
	}
	
	@RequestMapping("/manageCoursePlan")
	public ModelAndView manageCoursePlan(HttpServletRequest request){
		
		ModelAndView view = new ModelAndView("manageCoursePlan");
		
		try {
			HttpSession session = request.getSession();
			Instructor instructor = (Instructor)session.getAttribute("instructor");
			
			List<String> availableCourses = performanceService.getAvailableCourses(instructor.getInstructorId(),0);
			view.addObject("availableCourses", availableCourses);
		}
		catch(CNException ex) {
			view = handleException(ex);
		}
		return view;
	}
	
	@RequestMapping(value = "uploadCoursePlan", method = RequestMethod.POST)
	public ModelAndView saveimage(@RequestParam("dataFile") CommonsMultipartFile file, @ModelAttribute CoursePlan coursePlan ,HttpServletRequest request){
		ByteArrayInputStream bis = null;
		ModelAndView view = new ModelAndView("manageCoursePlan");
		HSSFWorkbook workbook = null;
		try {
			bis = new ByteArrayInputStream(file.getBytes());
			if (file.getOriginalFilename().endsWith("xls")) {
			    workbook = new HSSFWorkbook(bis);
			}else {
			    throw new IllegalArgumentException("Received file does not have a standard excel extension.");
			}
			instructorService.uploadCoursePlan(workbook, coursePlan);
			HttpSession session = request.getSession();
			Instructor instructor = (Instructor)session.getAttribute("instructor");
		
			List<String> availableCourses = performanceService.getAvailableCourses(instructor.getInstructorId(),0);
			view.addObject("availableCourses", availableCourses);
		}
		catch(CNException ex) {
			view = handleException(ex);
		} catch (IOException ex) {
			view = handleException(ex);
		} catch (ParseException ex) {
			view = handleException(ex);
		}
		return view;
	}
	
	@RequestMapping(value = {"/updateCourseSchedule"})
	public 	@ResponseBody AjaxResponse updateCourseSchedule(@ModelAttribute CoursePlan coursePlan){
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> responseData = new HashMap<String,Object>();
		ajaxResponse.setResponseData(responseData);
		
		try {
			coursePlan.setTopicDate(DateUtils.formatDate(coursePlan.getTopicDate(),"mm/dd/yyyy", "yyyy-mm-dd"));
			coursePlan.setAssignmentDeadline(DateUtils.formatDate(coursePlan.getAssignmentDeadline(),"mm/dd/yyyy", "yyyy-mm-dd"));
			instructorService.updateCourseSchedule(coursePlan);
			responseData.put("success", "Course Plan Updated Successfully");
		}
		catch(CNException ex) {
			ajaxResponse = handleAjaxException(ex);
		} catch (ParseException ex) {
			ajaxResponse = handleAjaxException(ex);
		}
		return ajaxResponse;
	}

}
