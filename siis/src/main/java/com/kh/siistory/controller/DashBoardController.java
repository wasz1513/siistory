package com.kh.siistory.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.multipart.MultipartFile;

import com.kh.siistory.entity.BoardDto;
import com.kh.siistory.entity.ReplyDto;
import com.kh.siistory.repository.BoardDao;
import com.kh.siistory.repository.ReplyDao;
import com.kh.siistory.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/dashboard")
@Slf4j
public class DashBoardController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardDao boardDao;

	@Autowired
	private ReplyDao replyDao;
	
	@GetMapping({ "/", "" })
	public String dashboard(Model model, HttpSession session) {
		model.addAttribute("dtolist", boardDao.dashboardlist(session));
		return "dashboard/dashboard";
	}

	@GetMapping("/write")
	public String write() {
		return "dashboard/write";
	}

	@PostMapping("/uploadimage")
	@ResponseBody
	public void uploadimage(@RequestParam List<MultipartFile> sel_files, HttpSession session) throws IllegalStateException, IOException {
		boardService.Boarduploadimage(sel_files, session);
	}

	@PostMapping("/addcontent")
	public void addcontent(HttpSession session, BoardDto boardDto) {
		boardDao.addcontent(boardDto, session);
	}

	@PostMapping("/replyinsert")
	@ResponseBody
	public ReplyDto replywrite(@ModelAttribute ReplyDto replyDto, HttpSession session, Model model) {
		return replyDao.insert(replyDto, session);
	}

	@GetMapping("/commentview")
	@ResponseBody
	public List<ReplyDto> commentview(@RequestParam Map<String, Integer> obj) {
		return replyDao.commentview(obj);
	}

	@GetMapping("/morereply")
	@ResponseBody
	public List<ReplyDto> morereply(@RequestParam Map<String, Integer> obj) {
		log.info("obj = {}", obj);
		return replyDao.morereply(obj);
	}

	@PostMapping("/private")
	public void boardPrivate(@ModelAttribute BoardDto boardDto, HttpSession session) {

		boardDao.setPrivate(boardDto);

	}

}
