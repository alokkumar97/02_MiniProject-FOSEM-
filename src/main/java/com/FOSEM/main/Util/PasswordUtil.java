package com.FOSEM.main.Util;

import org.apache.commons.lang3.RandomStringUtils;

public class PasswordUtil {

	public static String generatePwd() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String pwd = RandomStringUtils.random( 6, characters );
		return pwd;
	}
	
	
}
