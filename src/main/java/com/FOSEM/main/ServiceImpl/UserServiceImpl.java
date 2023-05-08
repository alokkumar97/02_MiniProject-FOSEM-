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
		// Get LoginForm obj from form page and verify user email and password from DB
		Users user = userRepo.findByUserEmailAndPassword(form.getUserEmail(), form.getPassword());
		// if User details is either null or LOCKED then return Invalid
		if(user == null) {
			return "Invalid Credentials !!";
		}
		if(user.getAccountStatus().equals("LOCKED")) {
			return "Your Account is Locked !!";
		}
		//If above conditions are not satisfy then create a session based on user id
		session.setAttribute("uid", user.getUid());
		return "Success";
	}

	@Override
	public boolean signup(SignUpForm form) {
		//Get SignUp form data from signUp page and verify user from DB based on that user email 
		Users user =userRepo.findByUserEmail(form.getUserEmail());
		//if that that user is present then return false or create new user with below details
		if(user != null) {
			return false;
		}		
		//Create a empty user entity object and copy data from binding form to entity obj	
		Users entity= new Users();
		BeanUtils.copyProperties(form, entity);
		// generate random password and store into entity obj
		String tempPwd= PasswordUtil.generatePwd();
		entity.setPassword(tempPwd);
		// Set account status default - Locked 
		entity.setAccountStatus("LOCKED");
		// insert/save user data
		userRepo.save(entity);
		//send email to user with temporary password
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
		//Get user details from UnlockForm page and fetch that user details from DB
		Users user = userRepo.findByUserEmail(form.getUserEmail().trim());
		//Verify DB password and temporary password 
		if(user.getPassword().equals(form.getTempPsswd())) {
			// If verify then only update new password and status
			user.setPassword(form.getNewPwd());
			user.setAccountStatus("UNLOCKED");
			//then save into DB
			userRepo.save(user);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public String forgotPwd(String email) {
		//Get User email from query param and verify that user email with DB user
		Users users = userRepo.findByUserEmail(email);
		// If not present then return Invalid 
		if(users == null) {
			return "Invalid user name !!";
		}
		// If present then send password to that user email
		String subject="Recover password !";
		String body="Your recovery password is :: "+ users.getPassword();
		mailUtil.sendMail(subject, body, email);
		return "Success";
	}

}
