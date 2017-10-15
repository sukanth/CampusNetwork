package com.campusnetwork.service;

import java.util.List;

import com.campusnetwork.exception.CNException;

public interface PerformanceService {
	
	public List<String> getAvailableCourses(int instructorId) throws CNException;
	
	public List<String> getAssessement(String courseId,String courseType) throws CNException;

}
