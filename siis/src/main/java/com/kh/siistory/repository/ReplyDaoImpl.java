package com.kh.siistory.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.siistory.entity.BoardDto;
import com.kh.siistory.entity.ReplyDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ReplyDaoImpl implements ReplyDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<ReplyDto> replylist(int no) {
		List<ReplyDto> list = sqlSession.selectList("reply.list", no);
		return list;
	}

}
