package com.kh.siistory.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.kh.siistory.repository.AdminDao;
import com.kh.siistory.service.AdminChartService;
import com.kh.siistory.vo.AdminChartVo;
import com.kh.siistory.vo.AdminSearchVo;
import com.kh.siistory.vo.BoardSearchVo;
import com.kh.siistory.vo.WarningVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private AdminChartService adminChartService;
	
	@GetMapping("/management")
	public String getManagement(){
		return "admin/management";
	}
	
	@PostMapping("/management")
	public String postManagement(@ModelAttribute AdminSearchVo adminSearchVo,
			HttpServletRequest req,
			Model model) {
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
			log.info("{}", req.getParameter("board_no"));
			
			adminDao.warning_receipt(Integer.parseInt(req.getParameter("warning_no")));
			if(adminDao.warning_check(Integer.parseInt(req.getParameter("member_no")))==0) {
				adminDao.warning_count_newreceipt(Integer.parseInt(req.getParameter("member_no")));
			}else {
				adminDao.warning_count_addreceipt(Integer.parseInt(req.getParameter("member_no")));
			}
			adminDao.change_board_state(Integer.parseInt(req.getParameter("board_no")));
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
	
	@GetMapping("/statismain")
	public String getStiatismain(Model model,
			HttpServletRequest req) {
		List<AdminChartVo> list = adminChartService.day_visit();
		if(req.getParameter("state")!=null) {
			String param = (String)req.getParameter("state");
			log.info("{}",param.equals("visit"));
			if(param.equals("visit")) {
				list = adminChartService.day_visit();
			}
			if(param.equals("regist")) {
				list = adminChartService.day_regist();
			}
			if(param.equals("content")) {
				list = adminChartService.day_content();
			}			
		}
//		if((String)req.getParameter("state")=="visit") {
//			list = adminChartService.day_visit();
//		}else if((boolean)req.getParameter("state").equals("regist")) {
//			list = adminChartService.day_regist();
//		}else if((String)req.getParameter("state")=="content") {
//			list = adminChartService.day_content();
//		}
		int max = list.get(0).getCount();
		for(int i=0; i<list.size(); i++) {
			if(max < list.get(i).getCount()) {
				max = list.get(i).getCount();
			}
			/*
			 * if(list.get(i).getDt()==null) { list.get(i).setDt("셋팅 테스트"); }
			 * if(list.get(i).getCount()<1) { list.get(i).setCount(0); }
			 */
		}
		int max_value = max;
		if(max<100) {
			max = 100;
		}else if(max<1000){
			max = 1000;
		}else if(max<5000) {
			max = 5000;
		}else if(max<10000) {
			max = 10000;
		}
		model.addAttribute("list", list);
		model.addAttribute("max", max);
		model.addAttribute("max_value", max_value);
		return "admin/statismain";
	}
	
	@GetMapping("/monthchart")
	public String getMonthchart(Model model,
			HttpServletRequest req) {
		List<AdminChartVo> list = adminChartService.day_visit();
		String param = (String)req.getParameter("state");
		log.info("{}",param.equals("visit"));
		if(param.equals("visit")) {
			list = adminChartService.month_visit();
		}
		if(param.equals("regist")) {
			list = adminChartService.month_regist();
		}
		if(param.equals("content")) {
			list = adminChartService.month_content();
		}
//		if((String)req.getParameter("state")=="visit") {
//			list = adminChartService.day_visit();
//		}else if((boolean)req.getParameter("state").equals("regist")) {
//			list = adminChartService.day_regist();
//		}else if((String)req.getParameter("state")=="content") {
//			list = adminChartService.day_content();
//		}
		int max = list.get(0).getCount();
		for(int i=0; i<list.size(); i++) {
			if(max < list.get(i).getCount()) {
				max = list.get(i).getCount();
			}
			/*
			 * if(list.get(i).getDt()==null) { list.get(i).setDt("셋팅 테스트"); }
			 * if(list.get(i).getCount()<1) { list.get(i).setCount(0); }
			 */
		}
		int max_value = max;
		if(max<100) {
			max = 100;
		}else if(max<1000){
			max = 1000;
		}else if(max<5000) {
			max = 5000;
		}else if(max<10000) {
			max = 10000;
		}
		model.addAttribute("list", list);
		model.addAttribute("max", max);
		model.addAttribute("max_value", max_value);
		return "admin/monthchart";
	}
	
	@GetMapping("/yearchart")
	public String getYearchart(Model model,
			HttpServletRequest req) {
		List<AdminChartVo> list = adminChartService.day_visit();
		String param = (String)req.getParameter("state");
		log.info("{}",param.equals("visit"));
		if(param.equals("visit")) {
			list = adminChartService.year_visit();
		}
		if(param.equals("regist")) {
			list = adminChartService.year_regist();
		}
		if(param.equals("content")) {
			list = adminChartService.year_content();
		}
//		if((String)req.getParameter("state")=="visit") {
//			list = adminChartService.day_visit();
//		}else if((boolean)req.getParameter("state").equals("regist")) {
//			list = adminChartService.day_regist();
//		}else if((String)req.getParameter("state")=="content") {
//			list = adminChartService.day_content();
//		}
		int max = list.get(0).getCount();
		for(int i=0; i<list.size(); i++) {
			if(max < list.get(i).getCount()) {
				max = list.get(i).getCount();
			}
			/*
			 * if(list.get(i).getDt()==null) { list.get(i).setDt("셋팅 테스트"); }
			 * if(list.get(i).getCount()<1) { list.get(i).setCount(0); }
			 */
		}
		int max_value = max;
		if(max<100) {
			max = 100;
		}else if(max<1000){
			max = 1000;
		}else if(max<5000) {
			max = 5000;
		}else if(max<10000) {
			max = 10000;
		}
		model.addAttribute("list", list);
		model.addAttribute("max", max);
		model.addAttribute("max_value", max_value);
		return "admin/yearchart";
	}
	
	@GetMapping("/statistics")
	public String getStiatistics(Model model,
			@RequestParam int state) {
		List<AdminChartVo> list = adminChartService.day_visit();
		int max = list.get(0).getCount();
		for(int i=1; i<list.size(); i++) {
			if(max < list.get(i).getCount()) {
				max = list.get(i).getCount();
			}
		}
		int max_value = max;
		if(max<100) {
			max = 100;
		}else if(max<1000){
			max = 1000;
		}else if(max<5000) {
			max = 5000;
		}else if(max<10000) {
			max = 10000;
		}
		log.info("{}",max);
		log.info("{}",max_value);
		model.addAttribute("list", list);
		model.addAttribute("max", max);
		model.addAttribute("max_value", max_value);
		return "admin/statistics";
	}
	
	@GetMapping("/boardmanagement")
	public String getBoardmanagement() {
		return "admin/boardmanagement";
	}
	
	@PostMapping("/boardmanagement")
	public String postBoardmanagement(@ModelAttribute BoardSearchVo boardSearchVo,
			HttpServletRequest req,
			Model model) {
		int pagesize = 10;
		int navsize = 5;
		int count = adminDao.search_board_count(boardSearchVo);
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
		log.info("{}", boardSearchVo.getKeyword());
		log.info("{}",count);
		boardSearchVo.setStart(start);
		boardSearchVo.setFinish(finish);
		model.addAttribute("list", adminDao.search_board(boardSearchVo));
		model.addAttribute("pagecount", pagecount);
		model.addAttribute("startBlock", startBlock);
		model.addAttribute("finishBlock", finishBlock);
		model.addAttribute("pno", pno);
		model.addAttribute("board_state", boardSearchVo.getBoard_state());
		model.addAttribute("board_content", boardSearchVo.getBoard_content());
		model.addAttribute("board_wdate", boardSearchVo.getBoard_wdate());
		model.addAttribute("photo", boardSearchVo.getPhoto());
		model.addAttribute("count", count);
		model.addAttribute("type", boardSearchVo.getType());
		model.addAttribute("keyword", boardSearchVo.getKeyword());
		return "admin/boardmanagement";
	}
	
	@GetMapping("boardlist")
	public String getBoardlist(HttpServletRequest req,
			Model model) {
		BoardSearchVo boardSearchVo = BoardSearchVo.builder()
														.type(req.getParameter("type"))
														.keyword(req.getParameter("keyword"))
														.board_state(Integer.parseInt(req.getParameter("board_state")))
														.board_content(req.getParameter("board_content"))
														.board_wdate(req.getParameter("board_wdate"))
														.photo(Integer.parseInt(req.getParameter("photo")))
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
		log.info("{}", boardSearchVo.getKeyword());
		boardSearchVo.setStart(start);
		boardSearchVo.setFinish(finish);
		model.addAttribute("list", adminDao.search_board(boardSearchVo));
		model.addAttribute("pagecount", pagecount);
		model.addAttribute("startBlock", startBlock);
		model.addAttribute("finishBlock", finishBlock);
		model.addAttribute("pno", pno);
		model.addAttribute("board_state", boardSearchVo.getBoard_state());
		model.addAttribute("board_content", boardSearchVo.getBoard_content());
		model.addAttribute("board_wdate", boardSearchVo.getBoard_wdate());
		model.addAttribute("photo", boardSearchVo.getPhoto());
		model.addAttribute("count", count);
		model.addAttribute("type", boardSearchVo.getType());
		model.addAttribute("keyword", boardSearchVo.getKeyword());
		return "admin/boardmanagement";
	}
	
	
}