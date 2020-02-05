package com.kh.siistory.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.siistory.entity.BoardDto;
import com.kh.siistory.entity.ReplyDto;
import com.kh.siistory.repository.BoardDao;
import com.kh.siistory.repository.ReplyDao;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/dashboard")
@Slf4j
public class DashBoardController {
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private ReplyDao replyDao;

	@GetMapping({ "/", "" })
	public String dashboard(Model model) {
		model.addAttribute("list", boardDao.dashboardlist());
		return "dashboard/dashboard";
	}

	@GetMapping("/write")
	public String write() {
		return "dashboard/write";
	}

	@PostMapping("/write")
	public String write(@ModelAttribute BoardDto boardDto, HttpSession session) {
		boardDao.setWrtie(boardDto, session);
		return "redirect:/";
	}

	@PostMapping("/replyinsert")
	@ResponseBody
	public void replywrite(@ModelAttribute ReplyDto replyDto) {
		replyDao.insert(replyDto);
	}
}
