package com.kh.siistory.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kh.siistory.entity.MemberDto;
import com.kh.siistory.repository.MemberDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainPageController {

	@Autowired
	private MemberDao memberDao;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@PostMapping("/")
	public String regist(@ModelAttribute MemberDto memberDto,
			HttpSession session) {
		memberDao.regist(memberDto);
		session.setAttribute("email", memberDto.getEmail());
		return "redirect:/main";
	}
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	@PostMapping("/login")
	public String postLogin(@ModelAttribute MemberDto memberDto,
			HttpSession session) {
		MemberDto login = memberDao.login(memberDto);
		if(login != null){
			session.setAttribute("email", memberDto.getEmail());
			return "redirect:/main";
		} else {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("email");
		return "redirect:/main";
	}
	
	@GetMapping("/main")
	public String main() {
		return "main/main";
	}
}
