package com.kh.siistory;

import java.time.LocalDate;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.siistory.entity.ConnectTableDto;
import com.kh.siistory.repository.ConnectTableDao;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@Slf4j
public class ConnectTableTest {

	@Autowired
	private ConnectTableDao connecttableDao;
	
	@Autowired
	private SqlSession sqlSession;
	
	
	
	
	@Test
	public void new_session_test () {
		ConnectTableDto dto = new ConnectTableDto();
//		LocalDate mydate = LocalDate.now();
		

		
		sqlSession.update("connect.exitsession", dto);
		
	}
	
	
}
