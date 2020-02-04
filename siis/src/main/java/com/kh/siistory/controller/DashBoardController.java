package com.kh.siistory.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.siistory.entity.BoardDto;
import com.kh.siistory.repository.BoardDao;

@Controller
@RequestMapping("/dashboard")
public class DashBoardController {
	@Autowired
	private BoardDao boardDao;

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

//	@GetMapping()
}
