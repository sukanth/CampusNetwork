package com.campusnetwork.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.campusnetwork.exception.CNException;
import com.campusnetwork.models.Marks;
import com.campusnetwork.models.PerformanceRange;
import com.campusnetwork.models.Student;

public interface PerformanceService {
	
	public List<String> getAvailableCourses(int instructorId,int sso) throws CNException;
	
	public List<String> getAssessement(String courseId,String courseType) throws CNException;
	
	public boolean uploadMarks(Workbook workbook,Marks marks) throws CNException;
	
	public void insertMarks(Marks marks) throws CNException;
	
	public PerformanceRange getPerformanceRange(Marks marks) throws CNException;
	
	public int getCourseStrength(Marks marks) throws CNException;
	
	public List<Marks> getIndividualPerformance(Marks marks) throws CNException;
	
	public List<Student> getStudents(String courseId) throws CNException;
	
	public Marks getPosition(Marks marks) throws CNException;
	
	public void updateMarks(Marks marks) throws CNException;
	
	public boolean checkMarks(Marks marks) throws CNException;
	
	public List<Marks> getStudentsMarks (Marks marks) throws CNException;
	
	public boolean deleteExistingMarks (Marks marks) throws CNException;
	
}
