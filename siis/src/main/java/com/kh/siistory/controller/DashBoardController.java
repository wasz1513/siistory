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
	public String dashboard(Model model, HttpSession session) {
		model.addAttribute("list", boardDao.dashboardlist(session));
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
	public ReplyDto replywrite(@ModelAttribute ReplyDto replyDto, HttpSession session, Model model) {
		return replyDao.insert(replyDto, session);
	}
	
	@GetMapping("/replyview")
	@ResponseBody
	public List<ReplyDto> replyview(@RequestParam int super_no){
		return replyDao.replyview(super_no);
	}
}
