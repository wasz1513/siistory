package com.kh.siistory.repository;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.siistory.entity.ReplyDto;

@Repository
public class ReplyDaoImpl implements ReplyDao {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insert(ReplyDto replyDto, HttpSession session) {
		replyDto = ReplyDto.builder().writer_no((int) session.getAttribute("member_no")).reply_writer((String)session.getAttribute("member_name")).build();
		sqlSession.insert("reply.insertreply", replyDto);
	}

}
