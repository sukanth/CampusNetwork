package com.campusnetwork.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.campusnetwork.exception.CNException;
import com.campusnetwork.models.AjaxResponse;
import com.campusnetwork.models.Attendance;
import com.campusnetwork.models.Instructor;
import com.campusnetwork.models.PerformanceRange;
import com.campusnetwork.models.Student;
import com.campusnetwork.service.AttendanceService;
import com.campusnetwork.utils.DateUtils;

@Controller
@RequestMapping("/entry/Attendance")
public class AttendanceController extends BaseController{
	
	@Autowired
	AttendanceService attendanceService;
	
	@RequestMapping("/loadSetAttendance")
	public ModelAndView loadSetAttendance(HttpServletRequest request){
		ModelAndView view = new ModelAndView("setAttendance");
		
		try {
			HttpSession session = request.getSession();
			Instructor instructor = (Instructor)session.getAttribute("instructor");
		
			List<String> availableCourses = attendanceService.getAvailableCourses(instructor.getInstructorId(),0);
			view.addObject("availableCourses", availableCourses);
		}
		catch(CNException ex) {
			view = handleException(ex);
		}
		return view;
	}
	
	@RequestMapping(value = {"/setAttendance"})
	public 	@ResponseBody AjaxResponse setAttendance(HttpServletRequest request,@ModelAttribute Attendance attendance){
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> responseData = new HashMap<String,Object>();
		ajaxResponse.setResponseData(responseData);
		
		try {
			
			HttpSession session = request.getSession();
			Instructor instructor = (Instructor)session.getAttribute("instructor");
			
			attendance.setInstructorId(instructor.getInstructorId());
			attendance.setAttendanceDate(DateUtils.formatDate(attendance.getAttendanceDate(),"mm/dd/yyyy", "yyyy-mm-dd"));
			attendance.setRandomCode(attendanceService.getRandomString(8));
			attendance.setLatitude(Integer.toString((int) Double.parseDouble(attendance.getLatitude())));
			attendance.setLongitude(Integer.toString((int) Double.parseDouble(attendance.getLongitude())));
			
			if(attendanceService.isAttendanceAvailable(attendance)) {
				responseData.put("errMsg", "Attendence Already Created.");
				return ajaxResponse;
			}
			attendanceService.insertNewAttendance(attendance);
		}
		catch (ParseException ex) {
			ajaxResponse = handleAjaxException(ex);
		}
		catch(CNException ex) {
			ajaxResponse = handleAjaxException(ex);
		} 
		return ajaxResponse;
	}
	
	@RequestMapping(value = {"/deleteAttendance"})
	public 	@ResponseBody AjaxResponse deleteAttendance(HttpServletRequest request,@ModelAttribute Attendance attendance){
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> responseData = new HashMap<String,Object>();
		ajaxResponse.setResponseData(responseData);
		
		try {
			attendanceService.deleteAttendance(attendance.getAttendanceId());
			attendance.setAttendanceDate(DateUtils.formatDate(attendance.getAttendanceDate(),"mm/dd/yyyy", "yyyy-mm-dd"));
			attendanceService.deleteAppliedAttendance(attendance);
		}
		catch(CNException ex) {
			ajaxResponse = handleAjaxException(ex);
		} catch (ParseException ex) {
			ajaxResponse = handleAjaxException(ex);
		}
		return ajaxResponse;
	}
	
	@RequestMapping(value = {"/getAttendances"})
	public 	@ResponseBody AjaxResponse getAttendances(HttpServletRequest request){
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> responseData = new HashMap<String,Object>();
		ajaxResponse.setResponseData(responseData);
		
		try {
			
			HttpSession session = request.getSession();
			Instructor instructor = (Instructor)session.getAttribute("instructor");
			
			List<Attendance> attendances = attendanceService.getAttendances(instructor.getInstructorId());
			responseData.put("attendances", attendances);
		}
		catch(CNException ex) {
			ajaxResponse = handleAjaxException(ex);
		}
		return ajaxResponse;
	}
	
	@RequestMapping("/loadMarkAttendance")
	public ModelAndView loadMarkAttendance(HttpServletRequest request){
		ModelAndView view = new ModelAndView("markAttendance");
		
		try {
			HttpSession session = request.getSession();
			Student student = (Student)session.getAttribute("student");
		
			List<String> availableCourses = attendanceService.getAvailableCourses(0,student.getSso());
			view.addObject("availableCourses", availableCourses);
		}
		catch(CNException ex) {
			view = handleException(ex);
		}
		return view;
	}
	
	@RequestMapping(value = {"/markAttendance"})
	public 	@ResponseBody AjaxResponse markAttendance(HttpServletRequest request,@ModelAttribute Attendance attendance){
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> responseData = new HashMap<String,Object>();
		ajaxResponse.setResponseData(responseData);
		
		try {
			
			HttpSession session = request.getSession();
			Student student = (Student)session.getAttribute("student");
			
			attendance.setSso(student.getSso());
			attendance.setAttendanceDate(DateUtils.formatDate(attendance.getAttendanceDate(),"mm/dd/yyyy", "yyyy-mm-dd"));
			attendance.setLatitude(Integer.toString((int) Double.parseDouble(attendance.getLatitude())));
			attendance.setLongitude(Integer.toString((int) Double.parseDouble(attendance.getLongitude())));
			
			Attendance attendanceDetails = attendanceService.getAttendanceDetail(attendance);
			
			if(attendanceDetails == null) {
				responseData.put("errMsg", "No Attendance found. Please select valid course and Date");
				return ajaxResponse;
			}
			if(!attendanceDetails.getRandomCode().equalsIgnoreCase(attendance.getRandomCode())) {
				responseData.put("errMsg", "Invalid Code. Please Enter valid Attendance Code.");
				return ajaxResponse;
			}
			if(!attendanceDetails.getLatitude().equals(attendance.getLatitude()) || !attendanceDetails.getLongitude().equals(attendance.getLongitude())) {
				responseData.put("errMsg", "You should be in class to Mark Attendence");
				return ajaxResponse;
			}
			if(!validateTime(attendanceDetails)) {
				responseData.put("errMsg", "Attendence Time has Expired");
				return ajaxResponse;
			}
			if(attendanceService.isAttendanceMarked(attendance)) {
				responseData.put("errMsg", "Attendence Already marked.");
				return ajaxResponse;
			}
			attendanceService.markAttendance(attendance);
			
		}
		catch(CNException ex) {
			ajaxResponse = handleAjaxException(ex);
		} catch (ParseException ex) {
			ajaxResponse = handleAjaxException(ex);
		}
		return ajaxResponse;
	}
	
	public boolean validateTime(Attendance attendance) throws CNException {	
		
		try {
			Calendar calendar = Calendar.getInstance();
			
		    String string1 = attendance.getStartTime();
		    Date time1 = new SimpleDateFormat("hh:mm").parse(string1);
		    calendar.setTime(time1);
		    calendar.add(Calendar.MINUTE, -1);
		    time1 = calendar.getTime();


		    String string2 = attendance.getEndTime();
		    Date time2 = new SimpleDateFormat("hh:mm").parse(string2);
		    calendar.setTime(time2);
		    calendar.add(Calendar.MINUTE, 1);
		    calendar.add(Calendar.DAY_OF_MONTH, 1);
		    time2 = calendar.getTime();

		    Date x = new SimpleDateFormat("hh:mm").parse(new SimpleDateFormat("hh:mm").format(new Date()));
		    return (x.after(time1) && x.before(time2));
		    
		} catch (ParseException e) {
			throw new CNException("", e);
		}
	}
	
	@RequestMapping(value = {"/getIndividualAttendance"})
	public 	@ResponseBody AjaxResponse getIndividualAttendance(HttpServletRequest request){
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> responseData = new HashMap<String,Object>();
		ajaxResponse.setResponseData(responseData);
		
		try {
			
			HttpSession session = request.getSession();
			Student student = (Student)session.getAttribute("student");
			
			List<Attendance> attendances = attendanceService.getIndividualAttendance(student.getSso());
			responseData.put("attendances", attendances);
		}
		catch(CNException ex) {
			ajaxResponse = handleAjaxException(ex);
		}
		return ajaxResponse;
	}
	
	@RequestMapping(value = {"/generateAttendance"})
	public 	@ResponseBody AjaxResponse generateAttendance(HttpServletRequest request,@ModelAttribute Attendance attendance){
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> responseData = new HashMap<String,Object>();
		ajaxResponse.setResponseData(responseData);
		
		try {
			attendance.setAttendanceDate(DateUtils.formatDate(attendance.getAttendanceDate(),"mm/dd/yyyy", "yyyy-mm-dd"));
			attendanceService.generateAttendance(attendance);
		}
		catch(CNException ex) {
			ajaxResponse = handleAjaxException(ex);
		} catch (ParseException ex) {
			ajaxResponse = handleAjaxException(ex);
		}
		return ajaxResponse;
	}
	
	@RequestMapping("/loadAnalyzeAttendance")
	public ModelAndView loadAnalyzeAttendance(HttpServletRequest request){
		ModelAndView view = new ModelAndView("analyzeAttendance");
		
		try {
			HttpSession session = request.getSession();
			Instructor instructor = (Instructor)session.getAttribute("instructor");
		
			List<String> availableCourses = attendanceService.getAvailableCourses(instructor.getInstructorId(),0);
			view.addObject("availableCourses", availableCourses);
		}
		catch(CNException ex) {
			view = handleException(ex);
		}
		return view;
	}
	
	@RequestMapping(value = {"/getAttendanceDates"})
	public 	@ResponseBody AjaxResponse getAttendanceDates(HttpServletRequest request,@RequestParam("courseId") String courseId){
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> responseData = new HashMap<String,Object>();
		ajaxResponse.setResponseData(responseData);
		
		try {
			
			List<String> attendanceDates = attendanceService.getAttendanceDates(courseId);
			responseData.put("attendanceDates", attendanceDates);
		}
		catch(CNException ex) {
			ajaxResponse = handleAjaxException(ex);
		}
		return ajaxResponse;
	}
	
	@RequestMapping(value = {"/getCourseAttendance"})
	public 	@ResponseBody AjaxResponse getCourseAttendance(HttpServletRequest request,@ModelAttribute Attendance attendance){
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> responseData = new HashMap<String,Object>();
		ajaxResponse.setResponseData(responseData);
		
		try {
			attendance.setAttendanceDate(DateUtils.formatDate(attendance.getAttendanceDate(),"mm/dd/yyyy", "yyyy-mm-dd"));
			PerformanceRange  range = attendanceService.getCourseAttendance(attendance);
			responseData.put("range", range);
			
			int courseStrength = attendanceService.getCourseStrength(attendance);
			responseData.put("courseStrength", courseStrength);
		}
		catch(CNException ex) {
			ajaxResponse = handleAjaxException(ex);
		} catch (ParseException ex) {
			ajaxResponse = handleAjaxException(ex);
		}
		return ajaxResponse;
	}
	
	@RequestMapping(value = {"/getIndividualIAttendance"})
	public 	@ResponseBody AjaxResponse getIndividualIAttendance(HttpServletRequest request,@ModelAttribute Attendance attendance){
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> responseData = new HashMap<String,Object>();
		ajaxResponse.setResponseData(responseData);
		
		try {
			
			List<Attendance> attendances = attendanceService.getIndividualIAttendance(attendance);
			responseData.put("attendances", attendances);
		}
		catch(CNException ex) {
			ajaxResponse = handleAjaxException(ex);
		}
		return ajaxResponse;
	}

}
