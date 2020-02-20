package com.kh.siistory.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.kh.siistory.entity.BoardPicDto;

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
		log.info("map={}", map.toString());
	}
}
