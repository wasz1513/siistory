package com.kh.siistory.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	public BoardDto addcontent(ContentVo contentVo, HttpSession session) {
		Map<String, Object> map = new HashMap<>();

		map.put("member_no", (int) session.getAttribute("member_no"));
		map.put("board_writer", (String) session.getAttribute("member_name"));
		map.put("board_content", patternconvert(contentVo.getBoard_content()));
		map.put("board_state", contentVo.getBoard_state());
		map.put("piclist", contentVo.getBoard_pic_no());
		
		if (contentVo.getBoard_pic_no() != null) {
			map.put("photo", 1);
		} else {
			map.put("photo", 0);
		}

		map.put("board_no", contentVo.getBoard_no());
		
		sqlSession.insert("board.write", map);
		return sqlSession.selectOne("board.getcontent", (int) map.get("board_no"));
	}

	@Override
	public List<BoardDto> dashboardlist(HttpSession session) {
		return sqlSession.selectList("board.dashboardlist", (int) session.getAttribute("member_no"));
	}

	@Override
	public List<BoardDto> myboardList(HttpSession session) {
		List<BoardDto> test = sqlSession.selectList("myboard.getlist", (int) session.getAttribute("member_no"));
//		System.out.println("==========testtestsets===============");
//		System.out.println(test);
		
				
				
		return sqlSession.selectList("myboard.getlist", (int) session.getAttribute("member_no"));
	}

	@Override
	public void setPrivate(BoardDto boardDto) {
		sqlSession.update("myboard.setprivate", boardDto);
	}

	@Override
	public BoardDto getphotopost(int boardno, Map<String, Integer> paging) {
		if (paging.isEmpty()) {
			paging.put("start", 1);
			paging.put("end", 10);
		}
		paging.put("board_no", boardno);

		return sqlSession.selectOne("board.getphotopost", paging);
	}

	private String patternconvert(String content) {
		Pattern pattern = Pattern.compile("(@[a-zA-Z0-9ㄱ-ㅎ가-힣_]+)|(#[a-zA-Z0-9ㄱ-ㅎ가-힣_]+)");
		Matcher match = pattern.matcher(content);

		StringBuffer result = new StringBuffer();
		while (match.find()) {
			String find = match.group();
			if (find.startsWith("@")) {
				String contenturl = find.substring(1, find.length());
				match.appendReplacement(result, "<a class='member' href='/siistory/member/" + contenturl + "'>" + find + "</a>");
			} else if (find.startsWith("#")) {
				String contenturl = find.substring(1, find.length());
				match.appendReplacement(result, "<a class='tag' href='/siistory/search?type=tag&keyword=" + contenturl + "'>" + find + "</a>");
			}
		}

		return match.appendTail(result).toString();
	}

	@Override
	public void deletepost(int board_no) {
		sqlSession.delete("board.deletepost", board_no);
	}


	@Override
	public List<BoardDto> getfriendlist(String member, int member_no) {
		Map<String, Object> map = new HashMap<>();
		map.put("member_no", member_no);
		map.put("member_name", member);
		return sqlSession.selectList("myboard.getfriendlist", map);
	}

}
