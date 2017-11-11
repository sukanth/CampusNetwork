package com.campusnetwork.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusnetwork.dao.AppointmentMapper;
import com.campusnetwork.exception.CNException;
import com.campusnetwork.models.Appointment;
import com.campusnetwork.models.ModelValueBean;
import com.campusnetwork.service.AppointmentService;

@Service
public class AppoinmentServiceImpl implements AppointmentService{
	
	@Autowired
	AppointmentMapper appointmentMapper;

	@Override
	public void setAppoinment(Appointment appointment) throws CNException {
		
		appointmentMapper.setAppoinment(appointment);
	}



	@Override
	public List<Appointment> getAppoinments(int instructorId) throws CNException {
		
		return appointmentMapper.getAppoinments(instructorId);
	}



	@Override
	public void deleteAppoinment(Appointment appointment) throws CNException {
		appointmentMapper.deleteAppoinment(appointment);
	}



	@Override
	public List<ModelValueBean> getInstructors() throws CNException {
		
		return appointmentMapper.getInstructors();
	}



	@Override
	public List<ModelValueBean> getAppoinmentTime(int instructorId, String appointmentDate) throws CNException {
		
		return appointmentMapper.getAppoinmentTime(instructorId, appointmentDate);
	}



	@Override
	public void requestAppoinment(Appointment appointment) throws CNException {
		appointmentMapper.requestAppoinment(appointment);
	}



	@Override
	public List<Appointment> getRequestedAppoinments(int sso) throws CNException {

		return appointmentMapper.getRequestedAppoinments(sso);
	}



	@Override
	public void deleteRequestedAppoinment(int appointmentId) throws CNException {
		appointmentMapper.deleteRequestedAppoinment(appointmentId);
	}



	@Override
	public List<Appointment> getAppoinmentRequests(int instructorId) throws CNException {

		return appointmentMapper.getAppoinmentRequests(instructorId);
	}



	@Override
	public void updateRequestedAppointments(Appointment appointment) throws CNException {
		appointmentMapper.updateRequestedAppointments(appointment);
	}



	@Override
	public boolean checkIsProfessorAvailable(Appointment appointment) throws CNException {
		return appointmentMapper.checkIsProfessorAvailable(appointment) > 0;
	}



	@Override
	public Appointment getAppoinmentDetails(Appointment appointment) throws CNException {
		return appointmentMapper.getAppoinmentDetails(appointment);
	}

}
