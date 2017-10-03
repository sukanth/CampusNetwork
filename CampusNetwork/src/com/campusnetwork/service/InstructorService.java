package com.campusnetwork.service;

import java.util.List;

import com.campusnetwork.exception.CNException;
import com.campusnetwork.models.Course;
import com.campusnetwork.models.CoursePlan;

public interface InstructorService {
	
	public List<Course> getAssignedCourses(int instructorId) throws CNException;
	
	public List<CoursePlan> getCourseSchedule(String courseId) throws CNException;

}
