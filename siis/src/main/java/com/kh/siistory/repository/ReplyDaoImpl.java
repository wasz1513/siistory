package com.kh.siistory.repository;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
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
	public ReplyDto insert(ReplyDto replyDto, HttpSession session) {
		replyDto.setReply_writer((String) session.getAttribute("member_name"));
		replyDto.setWriter_no((int) session.getAttribute("member_no"));
		sqlSession.insert("reply.insertreply", replyDto);
		return sqlSession.selectOne("reply.return", replyDto.getReply_no());
	}
}
