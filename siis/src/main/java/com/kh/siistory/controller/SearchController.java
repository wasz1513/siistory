package com.kh.siistory.controller;

import java.util.List;

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

import com.kh.siistory.entity.FollowDto;
import com.kh.siistory.repository.FollowDao;
import com.kh.siistory.repository.MemberDao;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/search")
@Slf4j
public class SearchController {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private FollowDao followDao;
	
	@GetMapping("/")
	public String search(@RequestParam String type,
			@RequestParam String keyword,
			Model model,
			HttpSession session) {

		switch(type) {
			case "popular": break;
			case "email":
				model.addAttribute("list", followDao.search_email_follow(keyword));
				break;
			case "tag": break;
			case "location": break;
		}
		return "search/search";
	}
	
	@PostMapping("/follow")
	@ResponseBody
	public int follow(@ModelAttribute FollowDto followDto) {
		if(followDto.getFollowing()==1) {
			return followDao.following(followDto);			
		}else {
			return followDao.unfollowing(followDto);
		}
	}
}
