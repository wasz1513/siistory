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
	
	@GetMapping("/")
	public String search(@RequestParam String type,
			@RequestParam String keyword,
			Model model,
			HttpSession session) {
		int member_no = (int) session.getAttribute("member_no");
		MemberDto memberDto = MemberDto.builder()
											.email(keyword)
											.member_no(member_no)
										.build();
		switch(type) {
			case "popular": break;
			case "email":
				model.addAttribute("list", memberDao.getMember_Email(memberDto));
				break;
			case "tag": break;
			case "location": break;
		}
		return "search/search";
	}
	
	@PostMapping("/follow")
	@ResponseBody
	public int follow(@ModelAttribute FollowDto followDto) {
		log.info("following = {}", followDto.getFollowing());
		if(followDto.getFollowing()==1) {
			followDao.follower(followDto);
			return followDao.following(followDto);			
		}else {
			followDto.setFollowing(1);
			followDao.unfollower(followDto);
			return followDao.unfollowing(followDto);
		}
	}
}
