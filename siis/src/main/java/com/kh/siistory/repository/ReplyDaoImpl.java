package com.kh.siistory.repository;

import java.util.List;
import java.util.Map;

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
	public ReplyDto insert(ReplyDto replyDto, HttpSession session) {
		replyDto.setReply_writer((String) session.getAttribute("member_name"));
		replyDto.setWriter_no((int) session.getAttribute("member_no"));
		sqlSession.insert("reply.insertreply", replyDto);
		return sqlSession.selectOne("reply.replyviewadd", replyDto.getReply_no());
	}

	@Override
	public List<ReplyDto> commentview(Map<String, Integer> obj) {
		return sqlSession.selectList("reply.commentview", obj);
	}

	@Override
	public List<ReplyDto> morereply(Map<String, Integer> obj) {
		return sqlSession.selectList("reply.morereply", obj);
	}
}
