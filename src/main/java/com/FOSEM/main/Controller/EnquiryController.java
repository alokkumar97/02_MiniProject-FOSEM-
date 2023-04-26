package com.FOSEM.main.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnquiryController {

	@Autowired
	private HttpSession session;
	
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "index";
	}
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashboard";
	}
	
	
	@GetMapping("/addstudent")
	public String addEnquiries() {
		return "studentregister";
	}
	
	
	@GetMapping("/viewstudents")
	public String viewEnquiries() {
		return "viewenquiries";
	}
	
}
