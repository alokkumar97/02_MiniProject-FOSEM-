package com.FOSEM.main.ServiceImpl;

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
	
	@Override
	public String login(LoginForm form) {
		// TODO Auto-generated method stub
		return null;
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
		body.append("<h2>Use below tempory password to unlock account</h2>");
		body.append("Temporary password :"+tempPwd);
		body.append("<br/>");
		body.append("<a href=\"http:localhost:8083/unlock?email: "+to+"\">Click here to Unlock Account.</a>");
		
		mailUtil.sendMail(subject, body.toString(), to);
		
		return true;
	}

	@Override
	public boolean unlock(UnlockForm form) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String forgotPwd(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
