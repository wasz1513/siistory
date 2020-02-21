package com.kh.siistory.repository;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.siistory.entity.BoardDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSession sqlsession;
	
	// 게시판 시퀀스 선 발급 메소드
	@Override
	public int getboardseq() {
		return sqlsession.selectOne("board.getseq");
	}
	
	@Override
	public void addcontent(BoardDto boardDto, HttpSession session) {
		boardDto.setMember_no((int)session.getAttribute("member_no"));
		boardDto.setBoard_writer((String)session.getAttribute("member_name"));
		sqlsession.insert("board.write", boardDto);
	}
	
	@Override
	public List<BoardDto> dashboardlist(HttpSession session) {
//		List<BoardDto> list = sqlsession.selectList("board.dashboardlist", (int) session.getAttribute("member_no"));
//		
//		List<BoardDto> dtolist = new ArrayList<>();
//		for(BoardDto dto : list) {
//			int count = dto.getReplylist().size();
//			dto.setBoard_reply_count(count);
//			dtolist.add(dto);
//		}
		return sqlsession.selectList("board.dashboardlist", (int) session.getAttribute("member_no"));
	}

	@Override
	public List<BoardDto> myboardList(HttpSession session) {
		return sqlsession.selectList("myboard.getlist", (int) session.getAttribute("member_no"));
	}

	@Override
	public void setPrivate(BoardDto boardDto) {
		sqlsession.update("myboard.setprivate", boardDto);
	}


}
