package com.kh.siistory.websocket;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.siistory.vo.WebSocketUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlarmServer extends TextWebSocketHandler{

	//계획
	/* [1] 유저가 접속한다. (타겟이 나인 DB 시간정렬하여 가져온다.)  <<<<<< 내가 기준임
	 *  - 접속하면 어딘가에 저장된 내 알람 리스트를 불러와서 표시
	 *    
	 * [2] 좋아요를 누른다 or 해제한다 (onmessage에서 조건별 처리) <<<<타인 기준임
	 * 
	 *  ### (체크) on 
	 *  - 메시지를 보낸다.
	 *  - 메시지 형태를 확인한다. 
	 *  - 조건이 on이라면 컨텐츠 보유 대상(작성한 target_no)으로  데이터 DB저장 
	 *     
	 *  ### (체크) off 
	 *  - 메시지를 보낸다.
	 *  - 메시지 형태를 확인한다.
	 *  - 조건이 off라면 컨텐츠 보유 대상(작성한 target_no)으로  데이터 DB삭제
	 *  
	 *  
	 *  [3] 알람을 확인한다 <<<<< 내가 기준임
	 *  ### (알람확인) confirm  
	 *  - 컨텐츠 클릭 > 페이지 이동 + 메시지를 보낸다.
	 *  - 메시지 형태를 확인한다.
	 *  - 조건이 confirm이면    target_no 가 자신인 데이터 DB삭제
	 *  
	 *  
	 *  ### 알림 등록 후 5일이 지나면 알림 자동 삭제 처리
	 * 
	 */
	
	public static final int checkon = 00;
	public static final int checkoff = 01;
	public static final int confirm = 02;
	public static final int reset = 03;
	
	ObjectMapper mapper = new ObjectMapper();
	
	//user 저장소
	Set<WebSocketUser> userList = new HashSet<>();	
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionEstablished(session);
	}
	
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub
		super.handleMessage(session, message);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
	}
	
	
	
	
}
