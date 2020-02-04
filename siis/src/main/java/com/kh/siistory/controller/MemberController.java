package com.kh.siistory.controller;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.siistory.repository.MemberDao;
import com.kh.siistory.vo.MemberVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {

	@Autowired
	private MemberDao memberDao;
	
	@GetMapping("/mypage")
	public String mypage(HttpSession session,
			Model model) {
		MemberVo memberVo = memberDao.getMemberVo_no((int)session.getAttribute("member_no"));
		model.addAttribute("memberVo", memberVo);
//		log.info("memberVo = {}", memberVo);
		return "member/mypage";
	}
	
	@GetMapping("/modify")
	public String getModify(HttpSession session,
			Model model) {
		MemberVo memberVo = memberDao.getMemberVo_no((int)session.getAttribute("member_no"));
		model.addAttribute("memberVo", memberVo);
		return "member/modify";
	}
	
	@PostMapping("/modify")
	public String postModify(@ModelAttribute MemberVo memberVo,
			@RequestParam MultipartFile member_file) {
		
		return "redirect:member/mypage";
	}
	
}
