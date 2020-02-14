package com.kh.siistory.repository;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.kh.siistory.entity.BoardDto;
import com.kh.siistory.entity.ReplyDto;
import com.kh.siistory.vo.SeqVo;

public interface BoardDao {
	void setWrtie(BoardDto boardDto, HttpSession session);
	List<BoardDto> dashboardlist(HttpSession session);
}
