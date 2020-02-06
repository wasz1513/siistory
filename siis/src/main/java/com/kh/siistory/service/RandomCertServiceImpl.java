package com.kh.siistory.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class RandomCertServiceImpl implements RandomCertService{

	@Override
	public String RandomCertNumber(int size) {
		StringBuffer buffer = new StringBuffer();
		Random r = new Random();
		for(int i=0; i<size; i++) {
			buffer.append(r.nextInt(10));
		}
		return buffer.toString();
	}
	
}
