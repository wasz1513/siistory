package com.kh.siistory;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.siistory.entity.BoardDto;
import com.kh.siistory.entity.ReplyDto;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@Slf4j
public class BoardListTest {
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
		List<BoardDto> list = sqlSession.selectList("board.dashboardlist");
//		log.info("size = {}", list.size());
		for(BoardDto vo : list) {
			log.info("vo = {}", vo);
			if(vo.getReplylist() != null) {
				for(ReplyDto dto : vo.getReplylist()) {
					log.info("dto = {}", dto);
				}
			}
		}
	}
}
