package com.campusnetwork.models;

import java.io.Serializable;

public class Course implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7982184552187149125L;
	
	private String courseId;
	private String courseName;
	private String startDate;
	private String endDate;
	private String credits;
	private String courseDept;
	
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCredits() {
		return credits;
	}
	public void setCredits(String credits) {
		this.credits = credits;
	}
	public String getCourseDept() {
		return courseDept;
	}
	public void setCourseDept(String courseDept) {
		this.courseDept = courseDept;
	}

}
