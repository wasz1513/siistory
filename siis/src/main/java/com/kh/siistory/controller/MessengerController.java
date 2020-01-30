package com.kh.siistory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MessengerController {

	@GetMapping("/messenger")
	public String main() {
		
		return "messenger";
	}
}
