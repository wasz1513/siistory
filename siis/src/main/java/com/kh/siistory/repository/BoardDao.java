package com.kh.siistory.repository;

import javax.servlet.http.HttpSession;

import com.kh.siistory.entity.BoardDto;

public interface BoardDao {
	int getSequence();
	void setWrtie(BoardDto boardDto, HttpSession session);
}
