package com.campusnetwork.service;

import java.util.List;

import com.campusnetwork.exception.CNException;
import com.campusnetwork.models.Appointment;
import com.campusnetwork.models.ModelValueBean;

public interface AppointmentService {
	
	public List<Appointment> getAppoinments(int instructorId) throws CNException;
	
	public void deleteAppoinment(Appointment appointment) throws CNException;
	
	public void setAppoinment(Appointment appointment) throws CNException;
	
	public List<ModelValueBean> getInstructors() throws CNException;
	
	public List<ModelValueBean> getAppoinmentTime(int instructorId,String appointmentDate) throws CNException;
	
	public void requestAppoinment(Appointment appointment) throws CNException;
	
	public List<Appointment> getRequestedAppoinments(int sso) throws CNException;
	
	public void deleteRequestedAppoinment(int appointmentId) throws CNException;
	
	public List<Appointment> getAppoinmentRequests(int instructorId) throws CNException;
	
	public void updateRequestedAppointments(Appointment appointment) throws CNException;
	
	public boolean checkIsProfessorAvailable(Appointment appointment) throws CNException;
	
	public Appointment getAppoinmentDetails(Appointment appointment) throws CNException;

}
