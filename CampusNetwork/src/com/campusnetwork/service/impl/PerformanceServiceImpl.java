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
import com.campusnetwork.models.PerformanceRange;
import com.campusnetwork.models.Student;
import com.campusnetwork.service.PerformanceService;

@Service
public class PerformanceServiceImpl implements PerformanceService{
	
	@Autowired
	PerformanceMapper performanceMapper;

	@Override
	public List<String> getAvailableCourses(int instructorId,int sso) throws CNException {

		return performanceMapper.getAvailableCourses(instructorId,sso);
	}

	@Override
	public List<String> getAssessement(String courseId, String courseType) throws CNException {
		
		return performanceMapper.getAssessement(courseId, courseType);
	}

	@Override
	public boolean uploadMarks(Workbook workbook, Marks marks) throws CNException {
		
		Sheet sheet = workbook.getSheetAt(0);
		for (int i = 1;i <= sheet.getPhysicalNumberOfRows();i++) {
			Row row = sheet.getRow(i);
			if(row != null) {
				marks = readData(row,marks);
				if(checkMarks(marks))
					updateMarks(marks);
				else
					insertMarks(marks);
			}
		}
		return true;
	}
	
	public Marks readData(Row row, Marks marks) throws CNException {
		for(int i = 0;i <= 4;i++) {
			Cell cell = row.getCell(i);
			String value = null;
			cell.setCellType(Cell.CELL_TYPE_STRING);
			value = cell.getStringCellValue();
			    switch (i) {
			        case 0:
			        		marks.setSso(value);
			            break;
			        case 1:
			        	marks.setTopicName(value);
			            break;
			        case 2:
			        	marks.setTotalmarks(Integer.parseInt(value));
			            break;
			        case 3:
			        	marks.setMarksObtained(Integer.parseInt(value));
			            break;
			        case 4:
			        	marks.setComments(value);
			            break;
			}
		}
		marks.setPercentage(((marks.getMarksObtained() *100)/ marks.getTotalmarks()));
		return marks;
	}

	@Override
	public void insertMarks(Marks marks) throws CNException {
		 performanceMapper.insertMarks(marks);
	}

	@Override
	public PerformanceRange getPerformanceRange(Marks marks) throws CNException {
		return performanceMapper.getPerformanceRange(marks);
	}

	@Override
	public int getCourseStrength(Marks marks) throws CNException {
		return performanceMapper.getCourseStrength(marks);
	}

	@Override
	public List<Marks> getIndividualPerformance(Marks marks) throws CNException {
		return performanceMapper.getIndividualPerformance(marks);
	}

	@Override
	public List<Student> getStudents(String courseId) throws CNException {
		return performanceMapper.getStudents(courseId);
	}

	@Override
	public Marks getPosition(Marks marks) throws CNException {
		return performanceMapper.getPosition(marks);
	}

	@Override
	public void updateMarks(Marks marks) throws CNException {
		performanceMapper.updateMarks(marks);
	}

	@Override
	public boolean checkMarks(Marks marks) throws CNException {
		return performanceMapper.checkMarks(marks) > 0;
	}

	@Override
	public List<Marks> getStudentsMarks(Marks marks) throws CNException {
		return performanceMapper.getStudentsMarks(marks);
	}

	@Override
	public boolean deleteExistingMarks(Marks marks) throws CNException {
		return performanceMapper.deleteExistingMarks(marks) > 0;
	}
}
