package com.kh.siistory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.siistory.repository.AdminDao;
import com.kh.siistory.vo.AdminSearchVo;
import com.kh.siistory.vo.MemberProfileVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminDao adminDao;
	
	@GetMapping("/management")
	public String getManagement() {
		return "admin/management";
	}
	
	@PostMapping("/management")
	public String postManagement(@ModelAttribute AdminSearchVo adminSearchVo,
			Model model) {
		model.addAttribute("list", adminDao.search_Member(adminSearchVo));
		return "admin/management";
	}
	
}
