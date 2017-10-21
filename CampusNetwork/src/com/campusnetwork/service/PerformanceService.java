package com.campusnetwork.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.campusnetwork.exception.CNException;
import com.campusnetwork.models.Marks;

public interface PerformanceService {
	
	public List<String> getAvailableCourses(int instructorId) throws CNException;
	
	public List<String> getAssessement(String courseId,String courseType) throws CNException;
	
	public boolean uploadMarks(Workbook workbook,Marks marks) throws CNException;

}
