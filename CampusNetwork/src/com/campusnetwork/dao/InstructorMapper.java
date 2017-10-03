package com.campusnetwork.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.campusnetwork.exception.CNException;
import com.campusnetwork.models.Course;
import com.campusnetwork.models.CoursePlan;

public interface InstructorMapper {
	
public List<Course> getAssignedCourses(@Param("instructorId") int instructorId) throws CNException;
	
public List<CoursePlan> getCourseSchedule(@Param("courseId") String courseId) throws CNException;

}
