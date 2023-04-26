package com.FOSEM.main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.FOSEM.main.DTO.LoginForm;
import com.FOSEM.main.DTO.SignUpForm;
import com.FOSEM.main.DTO.UnlockForm;
import com.FOSEM.main.Service.IUserService;

@Controller
public class UserController {
	
	@Autowired
	private IUserService userService;
	

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}
	
	@PostMapping("/login")
	public String loginForm(@ModelAttribute("loginForm")  LoginForm form, Model model) {
		System.out.println(form);
		String status = userService.login(form);
		if(status=="Success") {
			return "redirect:/dashboard";
		}else {
			model.addAttribute("errMsg", status);
		}		
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
	public String unlockPage(@RequestParam("userEmail") String userEmail, Model model) {
		UnlockForm unlockFormObj = new UnlockForm();
		unlockFormObj.setUserEmail(userEmail);
		model.addAttribute("unlock", unlockFormObj);
		return "unlock";
	}
	
	@PostMapping("/unlock")
	public String unlockPageHandler(@ModelAttribute("unlock") UnlockForm unlock, Model model) {
		System.out.println(unlock);
		if(unlock.getNewPwd().equals(unlock.getConfirmPwd())) {
			boolean status = userService.unlockPage(unlock);
			if(status) {
				model.addAttribute("succMsg", "Your account is unlocked now .");
			}else {
				model.addAttribute("errMsg", "Given temporary password is incorrect, please check your email !!");
			}
		}else {
			model.addAttribute("errorMsg", "New Password and Confirm Password should be same !!");
		}		
		return "unlock";
	}
		
	@GetMapping("/forgot")
	public String forgotPwd() {
		return "forgotpwd";
	}
	@PostMapping("/forgotPwd")
	public String forgotPwdPage(@RequestParam("userEmail") String userEmail, Model model) {
		System.out.println(userEmail);
		String pwd = userService.forgotPwd(userEmail);
		if(pwd =="Success") {
			model.addAttribute("succMsg", "Password Sent to your email, Please check !!");
		}else {
			model.addAttribute("errMsg", pwd);
		}
		return "forgotpwd";
	}
	
}
