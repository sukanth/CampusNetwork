package com.campusnetwork.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusnetwork.dao.InstructorMapper;
import com.campusnetwork.exception.CNException;
import com.campusnetwork.models.Course;
import com.campusnetwork.models.CoursePlan;
import com.campusnetwork.service.InstructorService;

@Service
public class InstructorServiceImpl implements InstructorService{
	
	@Autowired
	InstructorMapper instructorMapper;

	@Override
	public List<Course> getAssignedCourses(int instructorId) throws CNException {
		
		return instructorMapper.getAssignedCourses(instructorId);
	}

	@Override
	public List<CoursePlan> getCourseSchedule(String courseId) throws CNException {
		
		return instructorMapper.getCourseSchedule(courseId);
	}

}
