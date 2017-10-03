package com.campusnetwork.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.campusnetwork.models.Instructor;
import com.campusnetwork.models.Student;
import com.campusnetwork.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping("/login")
	public ModelAndView loadLogin(){
		return new ModelAndView("login");
	}
	
	@RequestMapping("/validateUser")
	public ModelAndView validateUser(@RequestParam("email") String email,@RequestParam("password") String password,
			HttpServletRequest request,HttpServletResponse response){
		ModelAndView view = null;
		HttpSession session = request.getSession();
		
		boolean isValidUser = loginService.validateUser(email, password);
		if(isValidUser) {
			view = new ModelAndView("redirect:/entry/student/home.htm");
			Student student = loginService.getStudentDetails(email);
			session.setAttribute("student", student);
			session.setAttribute("loginType", "student");
			
		}else if(loginService.validateInstructor(email, password)){
			view = new ModelAndView("redirect:/entry/instructor/home.htm");
			Instructor instructor = loginService.getInstructorDetails(email);
			session.setAttribute("instructor", instructor);
			session.setAttribute("loginType", "instructor");
			
		}else {
			String message = (isValidUser) ? null : "Wrong Credentials! Please Try Again...";
			view = new ModelAndView("login","msg",message);
		}
		return view;
		
	}
	
	@RequestMapping("/logout")
	public ModelAndView validateUser(HttpServletRequest request,HttpServletResponse response){
		ModelAndView view = new ModelAndView("login");
		HttpSession session = request.getSession();
		session.invalidate();
		return view;
		
	}
	
	

}
