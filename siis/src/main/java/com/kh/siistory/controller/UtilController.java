package com.kh.siistory.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.siistory.entity.FollowDto;
import com.kh.siistory.entity.Member_profile_fileDto;
import com.kh.siistory.repository.FileuploadDao;
import com.kh.siistory.repository.FollowDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/util")
public class UtilController {

	@Autowired
	private FileuploadDao fileuploadDao;
	
	@Autowired
	private FollowDao followDao;
	
	//이미지 출력
	@GetMapping("/download")
	public void download(@RequestParam int member_no,
			HttpServletResponse resp) throws IOException {
		List<Member_profile_fileDto> list_fileDto = fileuploadDao.getFileInfo(member_no);
		
		Member_profile_fileDto fileDto = list_fileDto.get(0);
		
		File target = new File("D:/upload/kh2f/member", fileDto.getProfile_file_savename());
		byte[] data = FileUtils.readFileToByteArray(target);
		
		resp.setHeader("Content-Type", "application/octet=stream; charset=UTF-8");
		resp.setHeader("Content-Disposition", "attachment; filename=\""+URLEncoder.encode(fileDto.getProfile_file_uploadname(), "UTF-8")+"\"");
		resp.setHeader("Content-Length", String.valueOf(fileDto.getProfile_file_size()));

		resp.getOutputStream().write(data);
	}
	
	// 팔로잉 신청,취소
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
	
	// 친구요청 수락
	@PostMapping("/followok")
	@ResponseBody
	public int followok(@ModelAttribute FollowDto followDto) {
//		log.info("following = {}", followDto.getFollowing());
		if(followDto.getFollowing()==1) {
			followDao.following_ok(followDto);
			return followDao.follower_ok(followDto);			
		}else {
			followDao.following_no(followDto);
			return followDao.follower_no(followDto);
		}
	}
}
