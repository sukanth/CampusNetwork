package com.campusnetwork.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.campusnetwork.exception.CNException;
import com.campusnetwork.models.Attendance;

public interface AttendanceMapper {
	
	public List<String> getAvailableCourses(@Param("instructorId") int instructorId,@Param("sso") int sso) throws CNException;
	
	public void insertNewAttendance(Attendance attendance) throws CNException;
	
	public List<Attendance> getAttendances(@Param("instructorId") int instructorId) throws CNException;
	
	public void deleteAttendance(@Param("attendanceId") int attendanceId) throws CNException;
	
	public void deleteAppliedAttendance(Attendance attendance) throws CNException; 
	
	public Attendance getAttendanceDetail(Attendance attendance) throws CNException;
	
	public void markAttendance(Attendance attendance) throws CNException;
	
	public int isAttendanceMarked(Attendance attendance) throws CNException;
	
	public int isAttendanceAvailable(Attendance attendance) throws CNException;
	
	public List<Attendance> getIndividualAttendance(@Param("sso") int sso) throws CNException;
	
	public void generateAttendance(Attendance attendance) throws CNException;
	
	public void setGenerated(Attendance attendance) throws CNException;
	
	
}
