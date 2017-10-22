package com.campusnetwork.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.campusnetwork.exception.CNException;
import com.campusnetwork.models.Marks;
import com.campusnetwork.models.PerformanceRange;
import com.campusnetwork.models.Student;

public interface PerformanceMapper {
	
	public List<String> getAvailableCourses(@Param("instructorId") int instructorId,@Param("sso") int sso) throws CNException;
	
	public List<String> getAssessement(@Param("courseId") String courseId,@Param("courseType") String courseType) throws CNException;
	
	public void insertMarks(Marks marks) throws CNException;
	
	public PerformanceRange getPerformanceRange(Marks marks) throws CNException;
	
	public int getCourseStrength(Marks marks) throws CNException;
	
	public List<Marks> getIndividualPerformance(Marks marks) throws CNException;
	
	public List<Student> getStudents(@Param("courseId") String courseId) throws CNException;
	
	public Marks getPosition(Marks marks) throws CNException;
	
	public void updateMarks(Marks marks) throws CNException;
	
	public int checkMarks(Marks marks) throws CNException;
	

}
