package com.kh.siistory.repository;

import javax.servlet.http.HttpSession;

import com.kh.siistory.entity.BoardDto;
import com.kh.siistory.vo.SeqVo;

public interface BoardDao {
	SeqVo getSequence();
	void setWrtie(BoardDto boardDto, HttpSession session);
}
