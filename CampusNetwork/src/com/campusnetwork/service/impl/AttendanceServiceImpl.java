package com.campusnetwork.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusnetwork.dao.AttendanceMapper;
import com.campusnetwork.exception.CNException;
import com.campusnetwork.models.Attendance;
import com.campusnetwork.models.PerformanceRange;
import com.campusnetwork.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService{

	@Autowired
	AttendanceMapper attendanceMapper;
	
	@Override
	public List<String> getAvailableCourses(int instructorId, int sso) throws CNException {
		return attendanceMapper.getAvailableCourses(instructorId, sso);
	}

	@Override
	public void insertNewAttendance(Attendance attendance) throws CNException {
		attendanceMapper.insertNewAttendance(attendance);		
	}
	
	@Override
	public String getRandomString(int size)
    {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        char ch;
        for (int i = 0; i < size; i++)
        {
            ch = chars[random.nextInt(chars.length)];
            builder.append(ch);
        }
        return builder.toString().toUpperCase();
    }

	@Override
	public List<Attendance> getAttendances(int instructorId) throws CNException {
		
		return attendanceMapper.getAttendances(instructorId);
	}

	@Override
	public void deleteAttendance(int attendanceId) throws CNException {
		attendanceMapper.deleteAttendance(attendanceId);
	}

	@Override
	public Attendance getAttendanceDetail(Attendance attendance) throws CNException {
		return attendanceMapper.getAttendanceDetail(attendance);
	}

	@Override
	public void markAttendance(Attendance attendance) throws CNException {
		attendanceMapper.markAttendance(attendance);
	}

	@Override
	public boolean isAttendanceMarked(Attendance attendance) throws CNException {
		
		return attendanceMapper.isAttendanceMarked(attendance) > 0;
	}

	@Override
	public boolean isAttendanceAvailable(Attendance attendance) throws CNException {
		
		return attendanceMapper.isAttendanceAvailable(attendance) > 0;
	}

	@Override
	public List<Attendance> getIndividualAttendance(int sso) throws CNException {
		return attendanceMapper.getIndividualAttendance(sso);
	}

	@Override
	public void generateAttendance(Attendance attendance) throws CNException {
		attendanceMapper.generateAttendance(attendance);
		attendanceMapper.setGenerated(attendance);
	}

	@Override
	public void deleteAppliedAttendance(Attendance attendance) throws CNException {
		attendanceMapper.deleteAppliedAttendance(attendance);
		
	}

	@Override
	public List<String> getAttendanceDates(String courseId) throws CNException {
		
		return attendanceMapper.getAttendanceDates(courseId);
	}

	@Override
	public PerformanceRange getCourseAttendance(Attendance attendance) throws CNException {
		
		return attendanceMapper.getCourseAttendance(attendance);
	}

	@Override
	public List<Attendance> getIndividualIAttendance(Attendance attendance) throws CNException {
		
		return attendanceMapper.getIndividualIAttendance(attendance);
	}
	
	@Override
	public int getCourseStrength(Attendance attendance) throws CNException {
		
		return attendanceMapper.getCourseStrength(attendance);
	}
	
}
