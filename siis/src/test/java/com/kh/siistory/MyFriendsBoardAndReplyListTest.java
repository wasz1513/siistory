package com.kh.siistory;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.siistory.vo.MyFriendsBoardReplyVO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@Slf4j
public class MyFriendsBoardAndReplyListTest {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
		List<MyFriendsBoardReplyVO> list = sqlSession.selectList("board.getMyFriendsBoardAndReplyList", 103);
		for(MyFriendsBoardReplyVO vo : list) {
			log.info("vo = {}", vo);
		}
	}
	
}
