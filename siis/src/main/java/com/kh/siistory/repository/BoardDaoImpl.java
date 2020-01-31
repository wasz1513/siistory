package com.kh.siistory.repository;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.kh.siistory.entity.BoardDto;

@Repository
public class BoardDaoImpl implements BoardDao{

	@Override
	public int getSequence() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setWrtie(BoardDto boardDto, HttpSession session) {
		
	}

}
