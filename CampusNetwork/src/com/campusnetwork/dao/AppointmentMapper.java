package com.campusnetwork.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.campusnetwork.exception.CNException;
import com.campusnetwork.models.Appointment;
import com.campusnetwork.models.ModelValueBean;

public interface AppointmentMapper {
	
public List<Appointment> getAppoinments(@Param("instructorId") int instructorId) throws CNException;
	
public void deleteAppoinment(Appointment appointment) throws CNException;

public void setAppoinment(Appointment appointment) throws CNException;

public List<ModelValueBean> getInstructors() throws CNException;

public List<ModelValueBean> getAppoinmentTime(@Param("instructorId") int instructorId,@Param("appointmentDate") String appointmentDate) throws CNException;

public void requestAppoinment(Appointment appointment) throws CNException;

public List<Appointment> getRequestedAppoinments(@Param("sso") int sso) throws CNException;

public void deleteRequestedAppoinment(@Param("appointmentId") int appointmentId) throws CNException;

public List<Appointment> getAppoinmentRequests(@Param("instructorId") int instructorId) throws CNException;

public void updateRequestedAppointments(Appointment appointment) throws CNException;

public int checkIsProfessorAvailable(Appointment appointment) throws CNException;


}
