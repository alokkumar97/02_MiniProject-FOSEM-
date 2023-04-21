package com.FOSEM.main.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnquiryController {

	@GetMapping("/addstudent")
	public String addEnquiries() {
		return "studentregister";
	}
	
	
	@GetMapping("/viewstudents")
	public String viewEnquiries() {
		return "viewenquiries";
	}
	
}
