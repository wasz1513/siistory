package com.kh.siistory.websocket;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;



@Slf4j
public class ListServer extends TextWebSocketHandler {

	//사용자 저장을 위한 set 저장소 생성
	Set<WebSocketSession> userList = new HashSet<>();
	
	
	
	
// 클라이언트와 연결되면 실행하는 메소드
/* 접속을 하면 무엇을 할 것인가?
 * 리스트 출력을 위한 유저 정보 저장
 * 
 * 
 * */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("접속 테스트 ");
		log.info("{}",session);
		userList.add(session); 
		
		
		
		
	}
	
	
	
	//클라이언트가 서버로 메시지 전송했을 때 실행하는 메소드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// TODO Auto-generated method stub
		super.handleTextMessage(session, message);
	}
	
	
	
	
	//클라이언트와 연결 종료시 실행되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
	}
	
}
