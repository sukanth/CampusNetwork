package com.campusnetwork.service.impl;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusnetwork.dao.PerformanceMapper;
import com.campusnetwork.exception.CNException;
import com.campusnetwork.models.Marks;
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

	@Override
	public boolean uploadMarks(Workbook workbook, Marks marks) throws CNException {
		
		Sheet sheet = workbook.getSheetAt(0);
		for (int i = 1;i <= sheet.getPhysicalNumberOfRows();i++) {
			
		}
		return false;
	}
	
	public Marks readData(Row row, Marks marks) throws CNException {
		for(int i = 1;i <= row.getPhysicalNumberOfCells();i++) {
			
			
			
		}
		return marks;
	}

}
