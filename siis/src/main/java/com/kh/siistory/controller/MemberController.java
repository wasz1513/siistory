package com.kh.siistory.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.siistory.repository.BoardDao;
import com.kh.siistory.repository.FileuploadDao;
import com.kh.siistory.repository.FollowDao;
import com.kh.siistory.repository.MemberDao;
import com.kh.siistory.service.FileService;
import com.kh.siistory.vo.MemberVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private FollowDao followDao;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private FileuploadDao fileuploadDao;
	
	@Autowired
	private BoardDao boardDao;
	
	@GetMapping("/mypage")
	public String mypage(HttpSession session,
			Model model) {
		MemberVo memberVo = memberDao.getMemberVo_no((int)session.getAttribute("member_no"));
		model.addAttribute("memberVo", memberVo);
//		log.info("memberVo = {}", memberVo);
		return "member/mypage";
	}
	
	@GetMapping("/info")
	public String info(HttpSession session,
			Model model,
			@RequestParam int member_no) {
		int my_member_no = (int) session.getAttribute("member_no");
		model.addAttribute("memberInfo", memberDao.memberInfo(my_member_no, member_no));
		return "member/info";
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
			@RequestParam MultipartFile member_file) throws IllegalStateException, IOException {
		
//		log.info("memberVo = {}", memberVo);
//		log.info("member_file = {}", member_file.getSize());
		
		if(member_file.getSize() != 0 ) {
			if(memberDao.checkFile(memberVo.getMember_no())>=1) {
				fileService.change(memberVo, member_file);
			}else {				
				fileService.upload(memberVo, member_file);			
			}
		}else {
			memberDao.update_profile(memberVo);
		}
		
		return "redirect:mypage";
	}
	

	
	@GetMapping("/changeName")
	@ResponseBody
	public int changeName(@RequestParam String member_name,
			HttpSession session) {
		int member_no = (int) session.getAttribute("member_no");
		session.setAttribute("member_name", member_name);
		return memberDao.changeName(member_name, member_no);
	}
	
	@GetMapping("/follow")
	public String follow(HttpSession session,
			Model model) {
		int member_no = (int) session.getAttribute("member_no");
		model.addAttribute("list", followDao.myfollower(member_no));
		return "member/follow";
	}
	
	
	@GetMapping("/myboard")
	public String boardInfo(Model model, HttpSession session) {
		model.addAttribute("dtolist", boardDao.myboardList(session));
		
		
		return "member/myboard";
	}
	
	

	
}
