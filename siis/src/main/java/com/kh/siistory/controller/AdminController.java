package com.kh.siistory.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.siistory.repository.AdminDao;
import com.kh.siistory.vo.AdminSearchVo;
import com.kh.siistory.vo.WarningVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminDao adminDao;
	
	@GetMapping("/management")
	public String getManagement(){
		return "admin/management";
	}
	
	@PostMapping("/management")
	public String postManagement(@ModelAttribute AdminSearchVo adminSearchVo,
			HttpServletRequest req,
			Model model) {
		System.out.println("나이검색중우웅 =============== " + adminSearchVo.getMember_birth());
		int pagesize = 10;
		int navsize = 5;
		int count = adminDao.search_member_count(adminSearchVo);
		int pno;
		try {
			pno = Integer.parseInt(req.getParameter("pno"));
			if(pno<=0) throw new Exception();
		}catch(Exception e) {
			pno = 1;
		}
		int finish = pno * pagesize;
		int start = finish - (pagesize - 1);
		int pagecount = (count + pagesize) / pagesize;
		int startBlock = (pno - 1) / navsize * navsize + 1;
		int finishBlock = startBlock + (navsize - 1);
		if(finishBlock > pagecount){
			finishBlock = pagecount;
		}
		adminSearchVo.setStart(start);
		adminSearchVo.setFinish(finish);
		model.addAttribute("list", adminDao.search_Member(adminSearchVo));
		model.addAttribute("pagecount", pagecount);
		model.addAttribute("startBlock", startBlock);
		model.addAttribute("finishBlock", finishBlock);
		model.addAttribute("pno", pno);
		model.addAttribute("member_state", adminSearchVo.getMember_state());
		model.addAttribute("member_phone", adminSearchVo.getMember_phone());
		model.addAttribute("member_gender", adminSearchVo.getMember_gender());
		model.addAttribute("member_birth", adminSearchVo.getMember_birth());
		model.addAttribute("count", count);
		model.addAttribute("type", adminSearchVo.getType());
		model.addAttribute("keyword", adminSearchVo.getKeyword());
		return "admin/management";
	}
	
	@GetMapping("/list")
	public String getList(HttpServletRequest req,
			Model model) {
		AdminSearchVo adminSearchVo = AdminSearchVo.builder()
														.type(req.getParameter("type"))
														.keyword(req.getParameter("keyword"))
														.member_state(req.getParameter("member_state"))
														.member_phone(req.getParameter("member_phone"))
														.member_gender(req.getParameter("member_gender"))
														.member_birth(req.getParameter("member_birth"))
													.build();
		int pagesize = 10;
		int navsize = 5;
		int count = Integer.parseInt(req.getParameter("count"));
		int pno;
		try {
			pno = Integer.parseInt(req.getParameter("pno"));
			if(pno<=0) throw new Exception();
		}catch(Exception e) {
			pno = 1;
		}
		int finish = pno * pagesize;
		int start = finish - (pagesize - 1);
		
		int pagecount = (count + pagesize) / pagesize;
		int startBlock = (pno - 1) / navsize * navsize + 1;
		int finishBlock = startBlock + (navsize - 1);
		if(finishBlock > pagecount){
			finishBlock = pagecount;
		}
		
		adminSearchVo.setStart(start);
		adminSearchVo.setFinish(finish);
		model.addAttribute("list", adminDao.search_member_nav(adminSearchVo));
		model.addAttribute("pagecount", pagecount);
		model.addAttribute("startBlock", startBlock);
		model.addAttribute("finishBlock", finishBlock);
		model.addAttribute("pno", pno);
		model.addAttribute("member_state", adminSearchVo.getMember_state());
		model.addAttribute("member_phone", adminSearchVo.getMember_phone());
		model.addAttribute("member_gender", adminSearchVo.getMember_gender());
		model.addAttribute("member_birth", adminSearchVo.getMember_birth());
		model.addAttribute("count", count);
		model.addAttribute("type", adminSearchVo.getType());
		model.addAttribute("keyword", adminSearchVo.getKeyword());
		return "admin/management";
	}
	
	@GetMapping("/threeout")
	public String getThreeout() {
		return "admin/threeout";
	}
	
	@PostMapping("/threeout")
	public String postThreeout(@ModelAttribute WarningVo warningVo,
			HttpServletRequest req,
			Model model) {
		int pagesize = 10;
		int navsize = 5;
		int count = adminDao.warning_list_count(warningVo);
		int pno;
		try {
			pno = Integer.parseInt(req.getParameter("pno"));
			if(pno<=0) throw new Exception();
		}catch(Exception e) {
			pno = 1;
		}
		int finish = pno * pagesize;
		int start = finish - (pagesize - 1);
		int pagecount = (count + pagesize) / pagesize;
		int startBlock = (pno - 1) / navsize * navsize + 1;
		int finishBlock = startBlock + (navsize - 1);
		if(finishBlock > pagecount){
			finishBlock = pagecount;
		}
		
		warningVo.setStart(start);
		warningVo.setFinish(finish);
		model.addAttribute("list", adminDao.warning_list(warningVo));
		model.addAttribute("pagecount", pagecount);
		model.addAttribute("startBlock", startBlock);
		model.addAttribute("finishBlock", finishBlock);
		model.addAttribute("pno", pno);
		model.addAttribute("count", count);
		model.addAttribute("target_type", warningVo.getTarget_type());
		model.addAttribute("pusher_type", warningVo.getPusher_type());
		model.addAttribute("target_keyword", warningVo.getTarget_keyword());
		model.addAttribute("pusher_keyword", warningVo.getPusher_keyword());
		model.addAttribute("content_keyword", warningVo.getContent_keyword());
		model.addAttribute("state", warningVo.getState());
		return "admin/threeout";
	}	
	
	@GetMapping("/warninglist")
	public String warninglist(HttpServletRequest req,
			Model model) {
		WarningVo warningVo = WarningVo.builder().build();
		
		int pagesize = 10;
		int navsize = 5;
		int count = Integer.parseInt(req.getParameter("count"));
		int pno;
		try {
			pno = Integer.parseInt(req.getParameter("pno"));
			if(pno<=0) throw new Exception();
		}catch(Exception e) {
			pno = 1;
		}
		int finish = pno * pagesize;
		int start = finish - (pagesize - 1);
		int pagecount = (count + pagesize) / pagesize;
		int startBlock = (pno - 1) / navsize * navsize + 1;
		int finishBlock = startBlock + (navsize - 1);
		if(finishBlock > pagecount){
			finishBlock = pagecount;
		}
		
		warningVo.setStart(start);
		warningVo.setFinish(finish);
		model.addAttribute("list", adminDao.warning_list(warningVo));
		model.addAttribute("pagecount", pagecount);
		model.addAttribute("startBlock", startBlock);
		model.addAttribute("finishBlock", finishBlock);
		model.addAttribute("pno", pno);
		model.addAttribute("count", count);
		model.addAttribute("target_type", warningVo.getTarget_type());
		model.addAttribute("pusher_type", warningVo.getPusher_type());
		model.addAttribute("target_keyword", warningVo.getTarget_keyword());
		model.addAttribute("pusher_keyword", warningVo.getPusher_keyword());
		model.addAttribute("content_keyword", warningVo.getContent_keyword());
		model.addAttribute("state", warningVo.getState());
		return "admin/threeout";
	}
	
	@GetMapping("/receipt")
	@ResponseBody
	public boolean receipt(HttpServletRequest req) {
		try {
			adminDao.warning_receipt(Integer.parseInt(req.getParameter("warning_no")));
			if(adminDao.warning_check(Integer.parseInt(req.getParameter("member_no")))==0) {
				adminDao.warning_count_newreceipt(Integer.parseInt(req.getParameter("member_no")));
			}else {
				adminDao.warning_count_addreceipt(Integer.parseInt(req.getParameter("member_no")));
			}
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	@GetMapping("/hold")
	@ResponseBody
	public boolean hold(HttpServletRequest req) {
		try {
			adminDao.warning_hold(Integer.parseInt(req.getParameter("warning_no")));
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	@GetMapping("/canclesuspend")
	public String canclesuspend(@RequestParam int member_no) {
		adminDao.cancle_suspend(member_no);
		return "redirect:../member/info?member_no="+member_no;
	}
	
	@GetMapping("suspend")
	public String suspend(@RequestParam int member_no) {
		adminDao.suspend_member(member_no);
		return "redirect:../member/info?member_no="+member_no;
	}
	
	
}
