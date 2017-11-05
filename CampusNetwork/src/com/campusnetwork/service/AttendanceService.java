package com.campusnetwork.service;

import java.util.List;

import com.campusnetwork.exception.CNException;
import com.campusnetwork.models.Attendance;
import com.campusnetwork.models.PerformanceRange;

public interface AttendanceService {
	
	public List<String> getAvailableCourses(int instructorId,int sso) throws CNException;
	
	public String getRandomString(int size);
	
	public void insertNewAttendance(Attendance attendance) throws CNException;
	
	public List<Attendance> getAttendances(int instructorId) throws CNException;
	
	public void deleteAttendance(int attendanceId) throws CNException;
	
	public Attendance getAttendanceDetail(Attendance attendance) throws CNException;
	
	public void markAttendance(Attendance attendance) throws CNException;
	
	public boolean isAttendanceMarked(Attendance attendance) throws CNException;
	
	public boolean isAttendanceAvailable(Attendance attendance) throws CNException;
	
	public List<Attendance> getIndividualAttendance(int sso) throws CNException;
	
	public void generateAttendance(Attendance attendance) throws CNException;
	
	public void deleteAppliedAttendance(Attendance attendance) throws CNException; 
	
	public List<String> getAttendanceDates(String courseId) throws CNException;
	
	public PerformanceRange getCourseAttendance(Attendance attendance) throws CNException;
	
	public List<Attendance> getIndividualIAttendance(Attendance attendance) throws CNException;
	
	public int getCourseStrength(Attendance attendance) throws CNException;
	
	
	
}
