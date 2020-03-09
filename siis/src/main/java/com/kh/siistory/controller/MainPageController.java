package com.kh.siistory.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kh.siistory.entity.ConnectTableDto;
import com.kh.siistory.entity.MemberDto;
import com.kh.siistory.repository.BoardDao;
import com.kh.siistory.repository.ConnectTableDao;
import com.kh.siistory.repository.FollowDao;
import com.kh.siistory.repository.MemberDao;
import com.kh.siistory.service.EmailService;
import com.kh.siistory.service.RandomCertService;
import com.kh.siistory.vo.SeqVo;
import com.kh.siistory.vo.WebSocketUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainPageController {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private FollowDao followDao;
	
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private RandomCertService randomCertService;
	
	@Autowired
	private ConnectTableDao connecttableDao;
	
	@Autowired
	private ConnectTableDto connectDto;
	
	@GetMapping("/")
	public String index(HttpSession session) {
		
		
		
		//신규 세션이면 (리스터의 객체를 가져와서 셋팅 시킨다.)
		//접속 중인 인원 표시의 경우 지금 단계에서 처리하기에는 생각보다 세부적인 사항들이 많이 들어가므로
		//이번 프로젝트에서는 제외.
		if(session.isNew()) {
			connecttableDao.true_session(connectDto);
			
		//기존 세션이면 (db 접속 건수들만 처리)
		} else if (!session.isNew()) {
			connecttableDao.false_session(connectDto);
		}
		
		
		return "index";		
	}
	
	@PostMapping("/")
	public String regist(@ModelAttribute MemberDto memberDto,
			HttpSession session) {
		SeqVo seqVo = memberDao.seq_no();
//		log.info("no = {}",seqVo.getSeq_no());
		memberDto.setMember_no(seqVo.getSeq_no());
		memberDto.setMember_pw(encoder.encode(memberDto.getMember_pw()));
//		log.info("memberDto = {}",memberDto);
		memberDao.regist(memberDto);
		memberDao.regist_profile(memberDto);
//		memberDao.me(seqVo);
		session.setAttribute("email", memberDto.getEmail());
		session.setAttribute("member_no", seqVo.getSeq_no());
		session.setAttribute("member_name", memberDto.getMember_name());
		return "redirect:/main";
	}
	
	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	@PostMapping("/login")
	public String postLogin(@ModelAttribute MemberDto memberDto,
			HttpSession session) {
		MemberDto login = memberDao.login(memberDto);
//		log.info("member_pw = {}", memberDto.getMember_pw());
//		log.info("login = {}", login);
		if(login != null){
			boolean correct = encoder.matches(memberDto.getMember_pw(), login.getMember_pw());
			if(correct) {
				String url = "";
				session.setAttribute("email", login.getEmail());
				session.setAttribute("member_no", login.getMember_no());
				session.setAttribute("member_name", login.getMember_name());
				if(login.getEmail().equals("admin")) {
					return "redirect:/admin/management";
				}else {
					switch(login.getMember_state()) {
						case "정상" : url="redirect:/main"; break;
						case "탈퇴" : url="redirect:/withdraw"; break;
						case "휴면" : url="redirect:/dormant"; break;
						case "정지" : url="redirect:/suspend"; break;
					}
					memberDao.last_login(memberDto);
					return url;
				}
			}else {
				return "redirect:/login?error=false";
			}
		} else {
			return "redirect:/login?error=false";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("email");
		session.removeAttribute("member_no");
		session.removeAttribute("member_name");
		return "redirect:/";
	}
	
	@GetMapping("/main")
	public String main(HttpSession session, Model model) {
		int member_no = (int) session.getAttribute("member_no");
		model.addAttribute("followingcount", followDao.check_followingcount(member_no));
		model.addAttribute("myfriend", followDao.myfriend(member_no));
		model.addAttribute("dtolist", boardDao.dashboardlist(session));
		
		return "login/main";
	}
	
	@GetMapping("/suspend")
	public String suspend() {
		return "login/suspend";
	}
	
	@GetMapping("/withdraw")
	public String withdraw() {
		return "login/withdraw";
	}
	
	@GetMapping("/dormant")
	public String getDormant() {
		return "login/dormant";
	}
	
	@PostMapping("/dormant")
	public String postDormant(@ModelAttribute MemberDto memberDto) {
		MemberDto login = memberDao.login(memberDto);
		boolean correct = encoder.matches(memberDto.getMember_pw(), login.getMember_pw());
		if(correct) {
			memberDao.dormant(memberDto);
			return "redirect:main";
		}else {
			return "redirect:dormant?error=false";
		}
	}
	
	@GetMapping("/idcheck")
	@ResponseBody
	public int idcheck(@RequestParam String email) {
		return memberDao.idcheck(email);
	}
	
	@GetMapping("/namecheck")
	@ResponseBody
	public int namecheck(@RequestParam String username) {
		return memberDao.namecheck(username);
	}
	
	@GetMapping("/findPw")
	public String getFindPw() {
		return "findPw";
	}
	
	@GetMapping("/getCert")
	@ResponseBody
	public String getCert(@RequestParam String email,
			HttpSession session) {
		String cert = randomCertService.RandomCertNumber(6);
		session.setAttribute("cert", cert);
		return emailService.sendCertMessage(email, cert);
	}
	
	@GetMapping("/validate")
	@ResponseBody
	public String validate(@RequestParam String cert,
			HttpSession session) {
		String value = (String)session.getAttribute("cert");
		if(value.equals(cert)) {
			session.removeAttribute("cert");
			return "success";
		}else {
			return "fail";			
		}
	}
	
	@GetMapping("/changePw")
	public String getChangePw(@RequestParam String email,
			Model model) {
		model.addAttribute("email", email);
		return "changePw";
	}
	
	@PostMapping("/changePw")
	public String postChangePw(@ModelAttribute MemberDto memberDto) {
		memberDto.setMember_pw(encoder.encode(memberDto.getMember_pw()));
		memberDao.changePw(memberDto);
		return "redirect:/login?email="+memberDto.getEmail();
	}
	
	
	//웹소켓 접속현황
	public void user_count(int user_count) {
		
		int count = user_count;

		
		
		 HttpServletRequest req =
		 ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).
		 getRequest(); req.setAttribute("user_count", count);
		 
	}
	

}













