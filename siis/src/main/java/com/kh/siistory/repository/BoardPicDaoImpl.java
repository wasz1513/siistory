package com.kh.siistory.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.kh.siistory.entity.BoardPicDto;
import com.kh.siistory.vo.PhotoVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardPicDaoImpl implements BoardPicDao {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void uploadimage(List<BoardPicDto> boardpicDto) {
		Map<String, Object> map = new HashMap<>();
		map.put("boardpicDto", boardpicDto);
		sqlSession.insert("boardpic.uploadimage", map);
	}

	@Override
	public int getpicseq() {
		return sqlSession.selectOne("boardpic.getpicseq");
	}

	@Override
	public PhotoVo getimage(int boardno) {
		return sqlSession.selectOne("boardpic.getimage", boardno);
	}

	@Override
	public List<Integer> getphotolist(int boardno) {
		return sqlSession.selectList("boardpic.getphotolist", boardno);
	}

	@Override
	public PhotoVo getimageall(int pic) {
		return sqlSession.selectOne("boardpic.getimageall", pic);
	}
}
