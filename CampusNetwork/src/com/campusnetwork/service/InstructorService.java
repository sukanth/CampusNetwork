package com.campusnetwork.service;

import java.text.ParseException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.campusnetwork.exception.CNException;
import com.campusnetwork.models.Course;
import com.campusnetwork.models.CoursePlan;

public interface InstructorService {
	
	public List<Course> getAssignedCourses(int instructorId) throws CNException;
	
	public List<CoursePlan> getCourseSchedule(String courseId) throws CNException;
	
	public boolean uploadCoursePlan(Workbook workbook,CoursePlan coursePlan) throws CNException, ParseException;
	
	public void insertCourseSchedule(CoursePlan coursePlan) throws CNException;
	
	public void updateCourseSchedule(CoursePlan coursePlan) throws CNException;

}
