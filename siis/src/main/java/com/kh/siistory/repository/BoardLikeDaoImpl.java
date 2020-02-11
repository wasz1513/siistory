package com.kh.siistory.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.siistory.entity.BoardLikeDto;

@Repository
public class BoardLikeDaoImpl implements BoardLikeDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public void insert(BoardLikeDto boardlikeDto) {
		
		sqlSession.insert("boardlike.insert", boardlikeDto);
		
	}

	@Override
	public void delete(BoardLikeDto boardlikeDto) {
		
		sqlSession.delete("boardlike.delete", boardlikeDto);
		
	}

}
