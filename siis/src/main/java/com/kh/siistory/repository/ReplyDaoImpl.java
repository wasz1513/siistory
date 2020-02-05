package com.kh.siistory.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.siistory.entity.ReplyDto;

@Repository
public class ReplyDaoImpl implements ReplyDao {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insert(ReplyDto replyDto) {
		
	}

}
