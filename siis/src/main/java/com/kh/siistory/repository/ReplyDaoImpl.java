package com.kh.siistory.repository;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.siistory.entity.ReplyDto;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ReplyDaoImpl implements ReplyDao {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insert(ReplyDto replyDto, HttpSession session) {
		replyDto.setReply_writer((String)session.getAttribute("member_name"));
		replyDto.setWriter_no((int) session.getAttribute("member_no"));
//		ReplyDto.builder().writer_no((int) session.getAttribute("member_no")).reply_writer((String)session.getAttribute("member_name")).build();
		log.info("log = {}", replyDto);
		sqlSession.insert("reply.insertreply", replyDto);
	}

}
