package com.kh.siistory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.siistory.repository.MemberDao;

@Controller
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private MemberDao memberDao;
	
	@GetMapping("/")
	public String search(@RequestParam String type,
			@RequestParam String keyword,
			Model model) {
		switch(type) {
			case "popular": break;
			case "email": model.addAttribute("list", memberDao.getMember_Email(keyword)); break;
			case "tag": break;
			case "location": break;
		}
		return "search/search";
	}
}
