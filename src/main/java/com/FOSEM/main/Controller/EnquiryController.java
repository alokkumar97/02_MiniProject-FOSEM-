package com.FOSEM.main.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.FOSEM.main.DTO.DashBoardResponse;
import com.FOSEM.main.DTO.EnquiryForm;
import com.FOSEM.main.Service.StudentService;

@Controller
public class EnquiryController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private StudentService stdService;
	
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "index";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		Integer totalEnquries = (Integer) session.getAttribute("uid");
		DashBoardResponse dashboardData = stdService.getTotalEnquries(totalEnquries);
		model.addAttribute("dashboardData", dashboardData);
		
		return "dashboard";
	}
	
	
	@GetMapping("/addstudent")
	public String addEnquiries(Model model) {
		//get all courses
		List<String>courses=stdService.getAllCourse();
		//get all Inquiries Status
		List<String> allEnqStatus = stdService.getAllStatus();
		//create form page
		EnquiryForm formObj = new EnquiryForm();
		//binding form page
		model.addAttribute("courses", courses);
		model.addAttribute("enqStatus", allEnqStatus);
		model.addAttribute("formObj", formObj);
		return "studentregister";
	}
	
	@PostMapping("/addstudent")
	public String addEnquries(@ModelAttribute("formObj")  EnquiryForm formObj, Model model) {
		System.out.println(formObj);
		String status = stdService.addEnquiries(formObj);
		if(status=="Success") {
			model.addAttribute("succMsg", "Student Inserted successfully .");
		}else {
			model.addAttribute("errMsg", "Problem occurs in Database");
		}
		return "studentregister";
	}
	
	@GetMapping("/viewstudents")
	public String viewEnquiries(Model model) {
		
		stdService.viewEnquries(null, null);
		
		return "viewenquiries";
	}
	
}
