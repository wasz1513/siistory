package com.kh.siistory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@Slf4j
public class ContentTest {

	@Test
	public void test() {
		String content = "<p>@test 테스트 @두번째 @test @두번째 #test 입니다</p>";
		Pattern pattern = Pattern.compile("(@[a-zA-Z0-9ㄱ-ㅎ가-힣_]+)|(#[a-zA-Z0-9ㄱ-ㅎ가-힣_]+)");
		Matcher match = pattern.matcher(content);

		StringBuffer result = new StringBuffer();
		while (match.find()) {
			String find = match.group();
			if(find.startsWith("@")) {
				String contenturl = find.substring(1, find.length());
				match.appendReplacement(result, "<a href='/" + contenturl + "'>" + find + "</a>");
			} else if(find.startsWith("#")) {
				String contenturl = find.substring(1, find.length());
				match.appendReplacement(result, "<a href='/" + contenturl + "'>" + find + "</a>");
			}
		}
		
		match.appendTail(result);
		
		

		log.info("content = {}", result);
	}
}
