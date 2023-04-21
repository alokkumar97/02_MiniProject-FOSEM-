package com.FOSEM.main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.FOSEM.main.DTO.SignUpForm;
import com.FOSEM.main.Service.IUserService;

@Controller
public class UserController {
	
	@Autowired
	private IUserService userService;
	

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String userRegisterPage(Model model) {
		model.addAttribute("user", new SignUpForm());
		return "signup";
	}
	
	@PostMapping("/signup")
	public String userRegisterHandle(@ModelAttribute("user") SignUpForm form, Model model) {
		boolean status = userService.signup(form);
		if(status) {
			model.addAttribute("successMsg", "Account is created, Check Your Email .");
		}else {
			model.addAttribute("errorMsg", "Please use unique email !");
		}		
		return "signup";
	}
	
	@GetMapping("/unlock")
	public String unlockPage() {
		return "unlock";
	}
	
	@GetMapping("/forgot")
	public String forgotPwd() {
		return "forgotpwd";
	}
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashboard";
	}
}
