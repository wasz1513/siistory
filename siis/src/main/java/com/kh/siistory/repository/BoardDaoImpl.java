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
	private SqlSession sqlSession;

	@Override
	public void addcontent(ContentVo contentVo, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		map.put("member_no", (int)session.getAttribute("member_no"));
		map.put("board_writer", (String)session.getAttribute("member_name"));
		map.put("board_content", contentVo.getBoard_content());
		map.put("piclist", contentVo.getBoard_pic_no());
		if(contentVo.getBoard_pic_no() != null) {
			map.put("photo", 1);
		} else {
			map.put("photo", 0);
		}
		
		sqlSession.insert("board.write", map);
	}

	@Override
	public List<BoardDto> dashboardlist(HttpSession session) {
		return sqlSession.selectList("board.dashboardlist", (int) session.getAttribute("member_no"));
	}

	@Override
	public List<BoardDto> myboardList(HttpSession session) {
		return sqlSession.selectList("myboard.getlist", (int) session.getAttribute("member_no"));
	}

	@Override
	public void setPrivate(BoardDto boardDto) {
		sqlSession.update("myboard.setprivate", boardDto);
	}

	@Override
	public BoardDto getphotopost(int boardno, Map<String, Integer> paging) {
		if(paging.isEmpty()) {
			paging.put("start", 1);
			paging.put("end", 10);
		}
		paging.put("board_no", boardno);
	
		return 	sqlSession.selectOne("board.getphotopost", paging);
	}

}
