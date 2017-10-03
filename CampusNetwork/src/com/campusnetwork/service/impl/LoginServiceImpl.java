package com.campusnetwork.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.campusnetwork.models.Instructor;
import com.campusnetwork.dao.LoginMapper;
import com.campusnetwork.models.Student;
import com.campusnetwork.service.LoginService;


@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	LoginMapper loginMapper;

	@Override
	public List<String> getAll() {

		return loginMapper.getAll();
	}

	@Override
	public boolean validateUser(String email, String password) {
		
		return loginMapper.validateUser(email, password) > 0;
	}

	@Override
	public Student getStudentDetails(String email) {
		
		return loginMapper.getStudentDetails(email);
	}

	@Override
	public boolean validateInstructor(String email, String password) {
		
		return loginMapper.validateInstructor(email, password) > 0;
	}

	@Override
	public Instructor getInstructorDetails(String email) {
		
		return loginMapper.getInstructorDetails(email);
	}


}
