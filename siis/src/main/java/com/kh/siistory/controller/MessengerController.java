package com.kh.siistory.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.siistory.entity.FriendDto;
import com.kh.siistory.entity.MemberDto;
import com.kh.siistory.repository.FriendDao;
import com.kh.siistory.repository.MemberDao;
import com.kh.siistory.websocket.ListServer;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class MessengerController {

	@GetMapping("/messenger")
	public String main(Model model, HttpSession session) {

		// 임시 접속 넘버 테스트
		session.setAttribute("no", 9);
		return "messenger";
	}

}
