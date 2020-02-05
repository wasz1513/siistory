package com.kh.siistory.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kh.siistory.entity.MemberDto;
import com.kh.siistory.repository.MemberDao;
import com.kh.siistory.vo.SeqVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainPageController {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@PostMapping("/")
	public String regist(@ModelAttribute MemberDto memberDto,
			HttpSession session) {
		SeqVo seqVo = memberDao.seq_no();
//		log.info("no = {}",seqVo.getSeq_no());
		memberDto.setMember_no(seqVo.getSeq_no());
		memberDto.setMember_pw(encoder.encode(memberDto.getMember_pw()));
//		log.info("memberDto = {}",memberDto);
		memberDao.regist(memberDto);
		memberDao.regist_profile(memberDto);
		session.setAttribute("email", memberDto.getEmail());
		session.setAttribute("member_no", seqVo.getSeq_no());
		session.setAttribute("member_name", memberDto.getMember_name());
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
//		log.info("member_pw = {}", memberDto.getMember_pw());
//		log.info("login = {}", login);
		if(login != null){
			boolean correct = encoder.matches(memberDto.getMember_pw(), login.getMember_pw());
			if(correct) {
				session.setAttribute("email", login.getEmail());
				session.setAttribute("member_no", login.getMember_no());
				session.setAttribute("member_name", login.getMember_name());
				return "redirect:/main";				
			}else {
				return "redirect:/login";
			}
		} else {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("email");
		session.removeAttribute("member_no");
		session.removeAttribute("member_name");
		return "redirect:/";
	}
	
	@GetMapping("/main")
	public String main() {
		return "main/main";
	}
	
	
}













