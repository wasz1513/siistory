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
											.member_no(member_no)
										.build();
		switch(type) {
			case "popular": break;
			case "email":
				memberDto.setEmail(keyword);
				model.addAttribute("list", memberDao.getMember_Email(memberDto));
				break;
			case "member_name":
				memberDto.setMember_name(keyword);
				model.addAttribute("list", memberDao.getMember_Name(memberDto));
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
//			검색을 진행 ( friend_no 가 이미 나를 팔로잉 했는데 검색을 한다 )
			log.info("followDto = {} ", followDto);
			log.info("check = {} " , followDao.check_following(followDto));
			if(followDao.check_following(followDto)==1) {
//				이미 팔로잉을 했다면 db를 update
				followDao.following_ok(followDto);
				return followDao.follower_ok(followDto);
			}else {
//				팔로잉을 안했다면 db를 insert
				followDao.follower(followDto);
				return followDao.following(followDto);							
			}
			
		}else {
			followDto.setFollowing(1);
			followDao.unfollower(followDto);
			return followDao.unfollowing(followDto);
		}
	}
}
