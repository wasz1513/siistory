package com.kh.siistory.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.siistory.entity.FollowDto;
import com.kh.siistory.entity.Member_profile_fileDto;
import com.kh.siistory.repository.FileuploadDao;
import com.kh.siistory.repository.FollowDao;
import com.kh.siistory.repository.MemberDao;
import com.kh.siistory.service.FileService;
import com.kh.siistory.vo.FileVo;
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
			fileService.upload(memberVo, member_file);			
		}
		memberDao.update_profile(memberVo);
		
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
	

	
}
