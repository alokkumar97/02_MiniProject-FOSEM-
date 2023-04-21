package com.FOSEM.main.Util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public boolean sendMail(String subject, String body, String to) {
		
		MimeMessage sme = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(sme);
		try {
			helper.setSubject(subject);
			helper.setText(body, true);
			helper.setTo(to);
			
			javaMailSender.send(sme);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return true;
	}
}
