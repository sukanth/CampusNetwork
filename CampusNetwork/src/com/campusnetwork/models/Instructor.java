package com.campusnetwork.models;

import java.io.Serializable;

public class Instructor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2608844209714994139L;
	
	
	private int instructorId;
	private String InstructorName;
	private String gender;
	private String degree;
	private String email;
	private String contactNumber;
	private String dob;
	
	public int getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}
	public String getInstructorName() {
		return InstructorName;
	}
	public void setInstructorName(String instructorName) {
		InstructorName = instructorName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	

}
