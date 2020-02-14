package com.kh.siistory.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.siistory.entity.MemberDto;
import com.kh.siistory.repository.FollowDao;
import com.kh.siistory.repository.MemberDao;
import com.kh.siistory.service.EmailService;
import com.kh.siistory.service.RandomCertService;
import com.kh.siistory.vo.MemberFollowVo;
import com.kh.siistory.vo.SeqVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainPageController {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private FollowDao followDao;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private RandomCertService randomCertService;
	
	@GetMapping("/")
	public String index() {
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
				switch(login.getMember_state()) {
					case "정상" : url="redirect:/main"; break;
					case "탈퇴" : url="redirect:/withdraw"; break;
					case "휴면" : url="redirect:/dormant"; break;
					case "정지" : url="redirect:/suspend"; break;
				}
				return url;
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
	public String main(HttpSession session,
			Model model) {
		int member_no = (int) session.getAttribute("member_no");
		model.addAttribute("myfriend", followDao.myfriend(member_no));
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
	
}













