package com.campusnetwork.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.campusnetwork.exception.CNException;

public interface PerformanceMapper {
	
	public List<String> getAvailableCourses(@Param("instructorId") int instructorId) throws CNException;
	
	public List<String> getAssessement(@Param("courseId") String courseId,@Param("courseType") String courseType) throws CNException;

}
