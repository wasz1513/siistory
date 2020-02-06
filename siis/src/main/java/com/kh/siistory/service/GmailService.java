package com.kh.siistory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.kh.siistory.entity.CertDto;
import com.kh.siistory.repository.MemberDao;

@Service
public class GmailService implements EmailService{

	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private RandomCertService randomCertService;

	@Override
	public String sendCertMessage(String email, String cert) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			
			String[] to = {email};
			message.setTo(to);
			
			message.setSubject("[SiiStory]인증번호");
			
			message.setText("인증번호 : " + cert);
			
			sender.send(message);
			return "success";			
		}catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@Override
	public String sendChangePassword(String email, String cert) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
