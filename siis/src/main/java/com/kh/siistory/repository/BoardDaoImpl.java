package com.kh.siistory.repository;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.siistory.entity.BoardDto;
import com.kh.siistory.vo.SeqVo;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSession sqlsession;

	@Override
	public SeqVo getSequence() {
		SeqVo seqVo = sqlsession.selectOne("board.seqno");
		return seqVo;
	}

	@Override
	public void setWrtie(BoardDto boardDto, HttpSession session) {
		boardDto.setMember_no((int)session.getAttribute("member_no"));
		boardDto.setBoard_writer((String)session.getAttribute("member_name"));
		boardDto.setBoard_no(getSequence().getSeq_no());
		sqlsession.insert("board.write", boardDto);
	}

	@Override
	public List<BoardDto> dashboardlist(HttpSession session) {
		List<BoardDto> list = sqlsession.selectList("board.dashboardlist", (int) session.getAttribute("member_no"));
		return list;
	}

}
