package com.campusnetwork.models;

import java.io.Serializable;

public class Attendance implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3298071133943833497L;
	
	private int attendanceId;
	private String courseId;
	private int instructorId;
	private String attendanceDate;
	private String randomCode;
	private String startTime;
	private String endTime;
	private String latitude;
	private String longitude;
	private String attendanceStatus;
	private int sso;
	private String isGenerated;
	
	public int getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(int attendanceId) {
		this.attendanceId = attendanceId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public int getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}
	public String getAttendanceDate() {
		return attendanceDate;
	}
	public void setAttendanceDate(String attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	public String getRandomCode() {
		return randomCode;
	}
	public void setRandomCode(String randomCode) {
		this.randomCode = randomCode;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getAttendanceStatus() {
		return attendanceStatus;
	}
	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}
	public int getSso() {
		return sso;
	}
	public void setSso(int sso) {
		this.sso = sso;
	}
	public String getIsGenerated() {
		return isGenerated;
	}
	public void setIsGenerated(String isGenerated) {
		this.isGenerated = isGenerated;
	}
}
