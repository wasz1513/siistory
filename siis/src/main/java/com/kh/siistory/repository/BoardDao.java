package com.kh.siistory.repository;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.kh.siistory.entity.BoardDto;
import com.kh.siistory.vo.ContentVo;

public interface BoardDao {
	BoardDto addcontent(ContentVo contentVo, HttpSession session);
	List<BoardDto> dashboardlist(HttpSession session);
	List<BoardDto> myboardList(HttpSession session);
	void setPrivate(BoardDto boardDto);
	BoardDto getphotopost(int boardno, Map<String, Integer> paging);
}

