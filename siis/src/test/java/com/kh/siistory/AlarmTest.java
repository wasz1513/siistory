package com.kh.siistory;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.siistory.entity.AlarmDto;
import com.kh.siistory.repository.AlarmDao;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
		"file:src/main/webapp/WEB-INF/spring/root-context.xml" )
@Slf4j
public class AlarmTest {

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private AlarmDao alarmDao;
	
	AlarmDto alarmDto = AlarmDto.builder()
			.target_no(23)
			.content_no(4)
			.content_type("board")
			.content_play("make")
			
			.build();
	
	
//	@Test
	public void get() {
		int no = 3;
		
		AlarmDto dto = sqlSession.selectOne("alarm.get", no);
		log.info("dto = {}",dto);
	}
	
//    @Test
	public void insert() {
		
		sqlSession.insert("alarm.insert", alarmDto);
		
	}
	// 실행 보류 
//	@Test
	public void delete() {
		sqlSession.delete("alarm.delete", alarmDto);
	}
	
//	@Test
	public void getList() {
		List <AlarmDto>list = alarmDao.getList(alarmDto);

		log.info("dddd{}", list);
	}
	
	
	
	
	
}
