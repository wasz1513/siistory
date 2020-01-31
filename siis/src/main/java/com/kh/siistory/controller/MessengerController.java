package com.kh.siistory.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.socket.WebSocketSession;

import com.kh.siistory.entity.FriendDto;
import com.kh.siistory.repository.FriendDao;
import com.kh.siistory.repository.MemberDao;
import com.kh.siistory.websocket.ListServer;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MessengerController {

	
	
	
	//메인 페이지 접속하자마자 메신저에 동시 접속 진행 처리 예정
	//메인 페이지에 접속하게 되면 session에서  member_no를 가져와서 
	/**
	 * 
	 */
	
	@Autowired
	private FriendDao friendDao;
	
	@Autowired 
	private MemberDao memberDao;
	
	@Autowired
	private ListServer listServer;
	
	@GetMapping("/messenger")
	public String main(Model model, HttpSession session) {
		
		//임시 접속 넘버 테스트
		session.setAttribute("no", 1);
		
		
		//친구 리스트를 출력
		List<FriendDto> friendList = friendDao.getList();
				
		//접속 비접속 구분 = userlist에 친구 아이디가 있으면 접속 아니면 비접속
		Set<WebSocketSession> userList = listServer.getUserList();
		
		//접속자만 
		
		for(FriendDto friendDto : friendList) {
			
			if(userList.iterator().next().getId().equals(friendDto.getFriend())) {
				
				friendDto.setConnect_state("접속");
				log.info("유저 리스트 {}",userList);
				
			}
			else {
				friendDto.setConnect_state("미접속");
				log.info("유저 리스트 {}",userList.toArray());
			}
		}
		
		
		
		
		model.addAttribute("friendlist",friendList);
				
		
		return "messenger";
	}
	
	
	
	
}
