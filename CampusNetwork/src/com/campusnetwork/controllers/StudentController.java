package com.campusnetwork.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.campusnetwork.exception.CNException;
import com.campusnetwork.models.AjaxResponse;
import com.campusnetwork.models.Course;
import com.campusnetwork.models.CoursePlan;
import com.campusnetwork.models.Student;
import com.campusnetwork.service.StudentService;

@Controller
@RequestMapping("/entry/student")
public class StudentController extends BaseController{
	
	@Autowired
	StudentService studentService;
	
	@RequestMapping("/home")
	public ModelAndView loadStudentHome(HttpServletRequest request){
		
		ModelAndView view = new ModelAndView("studentHome");
		
		try {
			HttpSession session = request.getSession();
			Student student = (Student)session.getAttribute("student");
		
			List<CoursePlan> importantDates = studentService.getImportantDates(student.getSso());
			view.addObject("importantDates", importantDates);
			
			List<CoursePlan> upcomingAssessments = studentService.getUpcomingAssessments(student.getSso());
			view.addObject("upcomingAssessments", upcomingAssessments);
			
			List<Course> enrolledCourses = studentService.getEnrolledCourses(student.getSso());
			view.addObject("enrolledCourses", enrolledCourses);
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
		List<CoursePlan> courseSchedule = studentService.getCourseSchedule(courseId);
		responseData.put("courseSchedule", courseSchedule);
		}
		catch(CNException ex) {
			ajaxResponse = handleAjaxException(ex);
		}
		return ajaxResponse;
	}

}
