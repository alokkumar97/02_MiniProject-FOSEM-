package com.FOSEM.main.ServiceImpl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FOSEM.main.DTO.LoginForm;
import com.FOSEM.main.DTO.SignUpForm;
import com.FOSEM.main.DTO.UnlockForm;
import com.FOSEM.main.Entity.Users;
import com.FOSEM.main.Repository.IUserRepository;
import com.FOSEM.main.Service.IUserService;
import com.FOSEM.main.Util.MailUtil;
import com.FOSEM.main.Util.PasswordUtil;
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepo;
	@Autowired
	private MailUtil mailUtil;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public String login(LoginForm form) {
		Users user = userRepo.findByUserEmailAndPassword(form.getUserEmail(), form.getPassword());
		if(user == null) {
			return "Invalid Credentials !!";
		}
		/*if(user.getAccountStatus().equals("LOCKED")) {
			return "Your Account is Locked !!";
		}
		*/
		session.setAttribute("uid", user.getUid());
		return "Success";
	}

	@Override
	public boolean signup(SignUpForm form) {
		
		Users user =userRepo.findByUserEmail(form.getUserEmail());
		if(user != null) {
			return false;
		}		
		//TODO: copy data from binding form to entity obj	
		Users entity= new Users();
		BeanUtils.copyProperties(form, entity);
		//TODO: generate random pwd
		String tempPwd= PasswordUtil.generatePwd();
		entity.setPassword(tempPwd);
		//TODO: Set account status default - Locked 
		entity.setAccountStatus("LOCKED");
		//TODO: insert data
		userRepo.save(entity);
		//TODO: send email to user
		String subject ="Unlock Your Account !";
		String to = form.getUserEmail();
		StringBuffer body =new StringBuffer("");
		body.append("<h2>Use below temporary password to unlock account</h2>");
		body.append("Temporary password : "+tempPwd);
		body.append("<br/>");
		body.append("<a href=\"http://localhost:8083/unlock?userEmail= "+to+"\">Click here to Unlock Account.</a>");
		
		mailUtil.sendMail(subject, body.toString(), to);
		
		return true;
	}

	@Override
	public boolean unlockPage(UnlockForm form) {
		Users user = userRepo.findByUserEmail(form.getUserEmail());
		if(user.getPassword().equals(form.getTempPsswd())) {
			user.setPassword(form.getNewPwd());
			user.setAccountStatus("UNLOCKED");
			userRepo.save(user);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public String forgotPwd(String email) {
		Users users = userRepo.findByUserEmail(email);
		if(users == null) {
			return "Invalid user name !!";
		}
		String subject="Recover password !";
		String body="Your recovery password is :: "+ users.getPassword();
		mailUtil.sendMail(subject, body, email);
		return "Success";
	}

}
