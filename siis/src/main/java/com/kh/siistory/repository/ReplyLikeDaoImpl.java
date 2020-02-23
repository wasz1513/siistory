package com.kh.siistory.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.kh.siistory.entity.ReplyLikeDto;

public class ReplyLikeDaoImpl implements ReplyLikeDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insert(ReplyLikeDto replylikeDto) {
 
		sqlSession.insert("replylike.insert", replylikeDto);
		
		
	}

	@Override
	public void delete(ReplyLikeDto replylikeDto) {
		sqlSession.delete("replylike.delete",replylikeDto);
		
	}

	
	
}
