package com.campusnetwork.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusnetwork.dao.PerformanceMapper;
import com.campusnetwork.exception.CNException;
import com.campusnetwork.service.PerformanceService;

@Service
public class PerformanceServiceImpl implements PerformanceService{
	
	@Autowired
	PerformanceMapper performanceMapper;

	@Override
	public List<String> getAvailableCourses(int instructorId) throws CNException {

		return performanceMapper.getAvailableCourses(instructorId);
	}

	@Override
	public List<String> getAssessement(String courseId, String courseType) throws CNException {
		
		return performanceMapper.getAssessement(courseId, courseType);
	}

}
