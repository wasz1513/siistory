package com.kh.siistory.service;

import com.kh.siistory.entity.CertDto;

public interface EmailService {

	String sendCertMessage(String email, String cert);
	
	String sendChangePassword(String email, String cert);
}
