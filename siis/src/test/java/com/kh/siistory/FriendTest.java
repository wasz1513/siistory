package com.kh.siistory;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.siistory.entity.FriendDto;
import com.kh.siistory.repository.FriendDao;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@Slf4j
public class FriendTest {

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private FriendDao friendDao;
	
	
	public void getlist() {
		
		List<FriendDto> list = sqlSession.selectList("friend.getlist");
		
		log.info("list = {}", list);
		log.info("friendDto = {}", list.get(0));
	}
	
	
	
	@Test
	public void test() {
		List<FriendDto> friendlist = friendDao.getList();
		
		for(FriendDto fdto : friendlist) {
			
//			memberDao.  no값으로 member 단일 조회 및 이름값 출력예정  
			log.info("{}",fdto.getFriend());
			log.info("{}",fdto.getMember_no());
		}
	}
	
	
}
