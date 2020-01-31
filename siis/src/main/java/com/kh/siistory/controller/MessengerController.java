package com.kh.siistory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.siistory.entity.FriendDto;
import com.kh.siistory.repository.FriendDao;
import com.kh.siistory.repository.MemberDao;

@Controller
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
	
	@GetMapping("/messenger")
	public String main(Model model) {
		

		
		List<FriendDto> friendlist = friendDao.getList();
		
		for(FriendDto fdto : friendlist) {
			
//			memberDao   
			
//			fdto.getMember_no();
			
		}
		
		
		
		
		model.addAttribute("friendlist");
				
		
		return "messenger";
	}
	
	
	
	
}
