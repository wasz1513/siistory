package com.kh.siistory.repository;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.kh.siistory.entity.BoardDto;

public interface BoardDao {
	int getboardseq();
	void addcontent(BoardDto boardDto, HttpSession session);
	List<BoardDto> dashboardlist(HttpSession session);
	List<BoardDto> myboardList(HttpSession session);
	void setPrivate(BoardDto boardDto);
}

