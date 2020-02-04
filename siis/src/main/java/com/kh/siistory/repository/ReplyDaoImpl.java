package com.kh.siistory.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.siistory.entity.BoardDto;
import com.kh.siistory.entity.ReplyDto;

@Repository
public class ReplyDaoImpl implements ReplyDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private BoardDto boardDto;
	
	@Override
	public BoardDto replylist(int no) {
		List<ReplyDto> list = sqlSession.selectList("reply.list");
		boardDto.setReplylist(list);
		return boardDto;
	}

}
