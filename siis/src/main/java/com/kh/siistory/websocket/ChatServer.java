package com.kh.siistory.websocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ChatServer extends TextWebSocketHandler {

//변환 도구 	
	ObjectMapper mapper = new ObjectMapper();

	Set<WebSocketSession> chatuserList = new HashSet<>();

	public void Allsending(String msg) throws IOException {
		TextMessage message = new TextMessage(msg);

		System.out.println(chatuserList.size());
		System.out.println(message);
			for (WebSocketSession list : chatuserList) {
				list.sendMessage(message);
		

		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		chatuserList.add(session);

		log.info("접속 완료 아이디=  " + session.getAttributes().get("member_no"));

		String connectNO = session.getAttributes().get("member_no").toString();
		log.info("" + connectNO);

		String text = ("[ " + connectNO + "] 님이 접속했습니다.");

		Allsending(text);

	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.info("들어온 메시지  = {}", message);
		int connectNO = (int) session.getAttributes().get("member_no");
		String text = ("[ " + connectNO + "] " + message.getPayload());
		System.out.println(message.getPayload());
		Allsending(text);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		chatuserList.remove(session);
		log.info("접속 종료!!!!");
	}

}
