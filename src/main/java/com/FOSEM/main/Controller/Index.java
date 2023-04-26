package com.FOSEM.main.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Index {

	@GetMapping("/")
	public String home() {
		return "index";
	}
}
