package com.campusnetwork.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusnetwork.dao.InstructorMapper;
import com.campusnetwork.exception.CNException;
import com.campusnetwork.models.Course;
import com.campusnetwork.models.CoursePlan;
import com.campusnetwork.service.InstructorService;
import com.campusnetwork.utils.DateUtils;

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

	@Override
	public boolean uploadCoursePlan(Workbook workbook, CoursePlan coursePlan) throws CNException, ParseException {
		Sheet sheet = workbook.getSheetAt(0);
		for (int i = 1;i <= sheet.getPhysicalNumberOfRows();i++) {
			Row row = sheet.getRow(i);
			if(row != null) {
				coursePlan = readData(row,coursePlan);
				insertCourseSchedule(coursePlan);
			}
		}
		return true;
	}
	
	public CoursePlan readData(Row row, CoursePlan coursePlan) throws CNException, ParseException {
		for (int i = 0; i <= 4; i++) {
			Cell cell = row.getCell(i);
			String value = null;
			if(cell != null) {
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					cell.setCellType(Cell.CELL_TYPE_STRING);
					value = cell.getStringCellValue();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {
						SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
						value = (cell.getDateCellValue() != null) ? DateUtils.formatDate(dateFormat.format(cell.getDateCellValue()), "mm/dd/yyyy", "yyyy-mm-dd") : null;
					} else {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						value = cell.getStringCellValue();
					}
					break;
				default:
					cell.setCellType(Cell.CELL_TYPE_STRING);
					value = cell.getStringCellValue();
					break;
				}
			}

			switch (i) {
			case 0:
				coursePlan.setCourseId(value);
				break;
			case 1:
				coursePlan.setTopicDate(value);
				break;
			case 2:
				coursePlan.setTopicName(value);
				break;
			case 3:
				coursePlan.setAssignmentName(value);
				break;
			case 4:
				value = ("NULL".equals(value)) ? null : value;
				coursePlan.setAssignmentDeadline(value);
				break;
			}
		}
		return coursePlan;
	}

	@Override
	public void insertCourseSchedule(CoursePlan coursePlan) throws CNException {
		instructorMapper.insertCourseSchedule(coursePlan);
	}

	@Override
	public void updateCourseSchedule(CoursePlan coursePlan) throws CNException {
		instructorMapper.updateCourseSchedule(coursePlan);
	}

}
