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
import com.kh.siistory.entity.MemberDto;
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
	
	@GetMapping("")
	public String search(@RequestParam String type,
			@RequestParam String keyword,
			Model model,
			HttpSession session) {
		int member_no = (int) session.getAttribute("member_no");
		MemberDto memberDto = MemberDto.builder()
											.member_no(member_no)
										.build();
		int count = -1;
		switch(type) {
			case "popular": 
				count=0;
				break;
			case "email":
				memberDto.setEmail(keyword);
				model.addAttribute("list", memberDao.getMember_Email(memberDto));
				count=1;
				break;
			case "member_name":
				memberDto.setMember_name(keyword);
				model.addAttribute("list", memberDao.getMember_Name(memberDto));
				count=2;
				break;
			case "tag": 
				model.addAttribute("dtolist", memberDao.search_tag(keyword));
				count=3;
				break;
			case "location": 
				count=4;
				break;
		}
		
		if(count==0) {
			return "search/search";			
		}else if(count==1) {
			return "search/search";	
		}else if(count==2) {
			return "search/search";	
		}else if(count==3) {
			return "search/searchtag";
		}else if(count==4) {
			return "search/search";
		}else {
			return "search/search";
		}
	}
	
	
}
