package com.kh.siistory.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.siistory.entity.BoardDto;
import com.kh.siistory.vo.ContentVo;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSession sqlsession;

	@Override
	public void addcontent(ContentVo contentVo, HttpSession session) {
//		BoardDto boardDto = BoardDto.builder().member_no((int)session.getAttribute("member_no")).board_writer((String)session.getAttribute("member_name")).board_content(contentVo.getBoard_content()).build();
		
		Map<String, Object> map = new HashMap<>();
		map.put("member_no", (int)session.getAttribute("member_no"));
		map.put("board_writer", (String)session.getAttribute("member_name"));
		map.put("board_content", contentVo.getBoard_content());
		map.put("piclist", contentVo.getBoard_pic_no());
		
		sqlsession.insert("board.write", map);
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
