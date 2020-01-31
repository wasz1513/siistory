package com.kh.siistory;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.siistory.entity.BoardDto;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@Slf4j
public class BoardTest {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private HttpSession session; 
	
	BoardDto boardDto = BoardDto.builder().board_content("테스트 입니다").build();
	
	@Test
	public void write() {
		log.info("a = {}", boardDto);
	}
	
	
}
