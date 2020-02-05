package com.kh.siistory;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({
		"file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@Slf4j
public class IdcheckTest {

	@Autowired
	private SqlSession sqlSession;
	
	
	@Test
	public void test() {
		int a = sqlSession.selectOne("member.idcheck", "asdf@badsf");
		log.info("a = {} ", a);
	}
	
}
