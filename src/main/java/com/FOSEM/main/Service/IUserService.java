package com.FOSEM.main.Service;

import com.FOSEM.main.DTO.LoginForm;
import com.FOSEM.main.DTO.SignUpForm;
import com.FOSEM.main.DTO.UnlockForm;

public interface IUserService {

	
	public String login(LoginForm form);
	
	public boolean signup(SignUpForm form);
	
	public boolean unlockPage(UnlockForm form);
	
	public String forgotPwd(String email);
	
}
