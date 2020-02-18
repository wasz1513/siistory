package com.kh.siistory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.siistory.vo.MemberProfileVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/management")
	public String getManagement() {
		return "admin/management";
	}
	
	@PostMapping("/management")
	public String postManagement(@ModelAttribute MemberProfileVo memberProfileVo,
			@RequestParam String type,
			@RequestParam String keyword) {
		return "admin/management";
	}
	
}
