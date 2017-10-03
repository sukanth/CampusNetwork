package com.campusnetwork.service;

import java.util.List;

import com.campusnetwork.models.Instructor;
import com.campusnetwork.models.Student;

public interface LoginService {
	
	public List<String> getAll();
	
	public boolean validateUser(String email,String password);
	
	public Student getStudentDetails(String email);
	
	public boolean validateInstructor(String email,String password);
	
	public Instructor getInstructorDetails(String email);
	
	

}
