package com.FOSEM.main.DTO;

import lombok.Data;

@Data
public class UnlockForm{

	private String email;
	private String tempPsswd;
	private String newPwd;
	private String confirmPwd;
	
}
