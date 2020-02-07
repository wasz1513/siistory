package com.kh.siistory.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MessengerController {

	@GetMapping("/messenger")
	public String main(HttpSession session) {

		// 임시 접속 넘버 테스트
		System.out.println("session member no = {}" + session.getAttribute("member_no"));
		return "messenger";
	}

	@GetMapping("/messenger/chat")
	public String chat(HttpSession session) {
		// 임시 접속 넘버 테스트
		System.out.println("session member no = {}" + session.getAttribute("member_no"));

		return "chat";

	}
	
	@GetMapping("/messenger/alarm")
	public String alarm() {
		return "alarm";
	}
	

}
