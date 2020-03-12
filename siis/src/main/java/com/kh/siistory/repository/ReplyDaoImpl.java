package com.kh.siistory.repository;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		
		String content = patternconvert(replyDto.getReply_content());
		replyDto.setReply_content(content);
		
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
	
	private String patternconvert(String content) {
		Pattern pattern = Pattern.compile("(@[a-zA-Z0-9ㄱ-ㅎ가-힣_]+)|(#[a-zA-Z0-9ㄱ-ㅎ가-힣_]+)");
		Matcher match = pattern.matcher(content);

		StringBuffer result = new StringBuffer();
		while (match.find()) {
			String find = match.group();
			if (find.startsWith("@")) {
				String contenturl = find.substring(1, find.length());
				match.appendReplacement(result, "<a class='member' href='/siistory/member/" + contenturl + "'>" + find + "</a>");
			} else if (find.startsWith("#")) {
				String contenturl = find.substring(1, find.length());
				match.appendReplacement(result, "<a class='tag' href='/siistory/search?type=tag&keyword=" + contenturl + "'>" + find + "</a>");
			}
		}

		return match.appendTail(result).toString();
	}
}
