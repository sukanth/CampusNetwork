package com.campusnetwork.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.campusnetwork.models.Student;
import com.campusnetwork.models.Instructor;

public interface LoginMapper {

	public List<String> getAll();
	
	public int validateUser(@Param("email") String email,@Param("password") String password);
	
	public Student getStudentDetails(@Param("email") String email);
	
	public int validateInstructor(@Param("email") String email,@Param("password") String password);
	
	public Instructor getInstructorDetails(@Param("email") String email);
}
