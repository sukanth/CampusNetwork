package com.campusnetwork.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusnetwork.dao.StudentMapper;
import com.campusnetwork.exception.CNException;
import com.campusnetwork.models.Course;
import com.campusnetwork.models.CoursePlan;
import com.campusnetwork.service.StudentService;


@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentMapper studentMapper;

	@Override
	public List<CoursePlan> getImportantDates(int sso)  throws CNException{
		
		return studentMapper.getImportantDates(sso);
	}

	@Override
	public List<CoursePlan> getUpcomingAssessments(int sso) throws CNException{
		
		return studentMapper.getUpcomingAssessments(sso);
	}

	@Override
	public List<Course> getEnrolledCourses(int sso) throws CNException{
		
		return studentMapper.getEnrolledCourses(sso);
	}

	@Override
	public List<CoursePlan> getCourseSchedule(String courseId) throws CNException{
		
		return studentMapper.getCourseSchedule(courseId);
	}



	

}
