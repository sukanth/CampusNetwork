package com.campusnetwork.controllers;

import java.text.ParseException;
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
import com.campusnetwork.models.Appointment;
import com.campusnetwork.models.Instructor;
import com.campusnetwork.models.ModelValueBean;
import com.campusnetwork.models.Student;
import com.campusnetwork.service.AppointmentService;
import com.campusnetwork.utils.DateUtils;

@Controller
@RequestMapping("/entry/Appointments")
public class AppoinmentController extends BaseController{
	
	@Autowired
	AppointmentService appointmentService;
	
	@RequestMapping("/manageAppoinments")
	public ModelAndView loadManageAppoinments(){
		return new ModelAndView("manageAppointments");
	}
	
	@RequestMapping(value = {"/setAppointment"})
	public 	@ResponseBody AjaxResponse setAppointment(HttpServletRequest request,@ModelAttribute Appointment appointment){
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> responseData = new HashMap<String,Object>();
		ajaxResponse.setResponseData(responseData);
		
		try {
			
			HttpSession session = request.getSession();
			Instructor instructor = (Instructor)session.getAttribute("instructor");
			
			appointment.setInstructorId(instructor.getInstructorId());
			appointment.setAppointmentFromDate(DateUtils.formatDate(appointment.getAppointmentFromDate(),"mm/dd/yyyy", "yyyy-mm-dd"));
			appointment.setAppointmentToDate(DateUtils.formatDate(appointment.getAppointmentToDate(),"mm/dd/yyyy", "yyyy-mm-dd"));
			
			appointmentService.setAppoinment(appointment);
			responseData.put("status", "success");
		}
		catch (ParseException ex) {
			ajaxResponse = handleAjaxException(ex);
		}
		catch(CNException ex) {
			ajaxResponse = handleAjaxException(ex);
		} 
		return ajaxResponse;
	}
	
	@RequestMapping(value = {"/deleteAppointment"})
	public 	@ResponseBody AjaxResponse deleteAppointment(HttpServletRequest request,@ModelAttribute Appointment appointment){
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> responseData = new HashMap<String,Object>();
		ajaxResponse.setResponseData(responseData);
		
		try {
			
			HttpSession session = request.getSession();
			Instructor instructor = (Instructor)session.getAttribute("instructor");
			
			
			appointment.setInstructorId(instructor.getInstructorId());
			appointment.setAppointmentFromDate(DateUtils.formatDate(appointment.getAppointmentFromDate(),"mm/dd/yyyy", "yyyy-mm-dd"));
			appointment.setAppointmentToDate(DateUtils.formatDate(appointment.getAppointmentToDate(),"mm/dd/yyyy", "yyyy-mm-dd"));
			appointmentService.deleteAppoinment(appointment);
			responseData.put("status", "success");
		}
		catch (ParseException ex) {
			ajaxResponse = handleAjaxException(ex);
		}
		catch(CNException ex) {
			ajaxResponse = handleAjaxException(ex);
		}
		return ajaxResponse;
	}
	
	@RequestMapping(value = {"/getAppointments"})
	public 	@ResponseBody AjaxResponse getAppointments(HttpServletRequest request){
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> responseData = new HashMap<String,Object>();
		ajaxResponse.setResponseData(responseData);
		
		try {
			
			HttpSession session = request.getSession();
			Instructor instructor = (Instructor)session.getAttribute("instructor");
			
			List<Appointment> appointments = appointmentService.getAppoinments(instructor.getInstructorId());
			responseData.put("appointments", appointments);
		}
		catch(CNException ex) {
			ajaxResponse = handleAjaxException(ex);
		}
		return ajaxResponse;
	}
	
	
	@RequestMapping("/requestAppointments")
	public ModelAndView loadRequestAppoinments() {
		ModelAndView view = new ModelAndView("requestAppointments");

		try {
			List<ModelValueBean> instructors = appointmentService.getInstructors();
			view.addObject("instructors", instructors);
		} catch (CNException ex) {
			view = handleException(ex);
		}
		return view;
	}
	
	@RequestMapping("/applyAppointments")
	public ModelAndView loadApplyAppoinments(){
		return new ModelAndView("applyAppointments");
	}
	
	
	@RequestMapping(value = {"/getAppointmentTime"})
	public 	@ResponseBody AjaxResponse getAppointmentTime(HttpServletRequest request,@RequestParam("instructorId") int instructorId,@RequestParam("appointmentDate") String appointmentDate){
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> responseData = new HashMap<String,Object>();
		ajaxResponse.setResponseData(responseData);
		
		try {
			appointmentDate = DateUtils.formatDate(appointmentDate,"mm/dd/yyyy", "yyyy-mm-dd");
			List<ModelValueBean> getAppointmentTime = appointmentService.getAppoinmentTime(instructorId, appointmentDate);
			responseData.put("getAppointmentTime", getAppointmentTime);
		}
		catch (ParseException ex) {
			ajaxResponse = handleAjaxException(ex);
		}
		catch(CNException ex) {
			ajaxResponse = handleAjaxException(ex);
		}
		return ajaxResponse;
	}
	
	@RequestMapping(value = {"/requestAppointment"})
	public 	@ResponseBody AjaxResponse requestAppointment(HttpServletRequest request,@ModelAttribute Appointment appointment){
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> responseData = new HashMap<String,Object>();
		ajaxResponse.setResponseData(responseData);
		
		try {
			
			HttpSession session = request.getSession();
			Student student = (Student)session.getAttribute("student");
			
			appointment.setSso(student.getSso());
			appointment.setAppointmentDate(DateUtils.formatDate(appointment.getAppointmentDate(),"mm/dd/yyyy", "yyyy-mm-dd"));
			
			if(appointmentService.checkIsProfessorAvailable(appointment)) {
				responseData.put("errMsg", "Professor is Scheduled with Another Appoinment. Please Select another Time.");
				return ajaxResponse;
			}
			
			appointmentService.requestAppoinment(appointment);
			responseData.put("status", "success");
		}
		catch (ParseException ex) {
			ajaxResponse = handleAjaxException(ex);
		}
		catch(CNException ex) {
			ajaxResponse = handleAjaxException(ex);
		} 
		return ajaxResponse;
	}
	
	@RequestMapping(value = {"/getRequestedAppointments"})
	public 	@ResponseBody AjaxResponse getRequestedAppointments(HttpServletRequest request){
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> responseData = new HashMap<String,Object>();
		ajaxResponse.setResponseData(responseData);
		
		try {
			
			HttpSession session = request.getSession();
			Student student = (Student)session.getAttribute("student");
			
			List<Appointment> appointments = appointmentService.getRequestedAppoinments(student.getSso());
			responseData.put("appointments", appointments);
		}
		catch(CNException ex) {
			ajaxResponse = handleAjaxException(ex);
		}
		return ajaxResponse;
	}
	
	@RequestMapping(value = {"/deleteRequestedAppointment"})
	public 	@ResponseBody AjaxResponse deleteAppointment(@RequestParam("appointmentId") int appointmentId){
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> responseData = new HashMap<String,Object>();
		ajaxResponse.setResponseData(responseData);
		
		try {
			appointmentService.deleteRequestedAppoinment(appointmentId);
			responseData.put("status", "success");
		}
		catch(CNException ex) {
			ajaxResponse = handleAjaxException(ex);
		}
		return ajaxResponse;
	}
	
	@RequestMapping(value = {"/getAppoinmentRequests"})
	public 	@ResponseBody AjaxResponse getAppoinmentRequests(HttpServletRequest request){
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> responseData = new HashMap<String,Object>();
		ajaxResponse.setResponseData(responseData);
		
		try {
			
			HttpSession session = request.getSession();
			Instructor instructor = (Instructor)session.getAttribute("instructor");
			
			List<Appointment> appointments = appointmentService.getAppoinmentRequests(instructor.getInstructorId());
			responseData.put("appointments", appointments);
		}
		catch(CNException ex) {
			ajaxResponse = handleAjaxException(ex);
		}
		return ajaxResponse;
	}
	
	@RequestMapping(value = {"/updateRequestedAppointments"})
	public 	@ResponseBody AjaxResponse updateRequestedAppointments(@ModelAttribute Appointment appointment){
		
		AjaxResponse ajaxResponse = new AjaxResponse();
		Map<String, Object> responseData = new HashMap<String,Object>();
		ajaxResponse.setResponseData(responseData);
		
		try {
			appointmentService.updateRequestedAppointments(appointment);
			responseData.put("status", "success");
		}
		catch(CNException ex) {
			ajaxResponse = handleAjaxException(ex);
		}
		return ajaxResponse;
	}
	

}
