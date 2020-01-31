package com.kh.siistory.repository;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.siistory.entity.BoardDto;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSession sqlsession;

	@Override
	public int getSequence() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setWrtie(BoardDto boardDto, HttpSession session) {
		boardDto = BoardDto.builder().member_no((int) session.getAttribute("member_no")).build();
		sqlsession.insert("board.wirte", boardDto);
	}

}
