package com.campusnetwork.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.campusnetwork.exception.CNException;
import com.campusnetwork.models.Course;
import com.campusnetwork.models.CoursePlan;

public interface StudentService {
	

	public List<CoursePlan> getImportantDates(int sso) throws CNException;
	
	public List<CoursePlan> getUpcomingAssessments(@Param("sso") int sso) throws CNException;
	
	public List<Course> getEnrolledCourses(@Param("sso") int sso) throws CNException;
	
	public List<CoursePlan> getCourseSchedule(@Param("courseId") String courseId) throws CNException;
	
	

}
