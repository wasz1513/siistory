package com.kh.siistory.websocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.siistory.entity.MemberDto;
import com.kh.siistory.repository.MemberDao;
import com.kh.siistory.vo.ChatData;
import com.kh.siistory.vo.WebSocketUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChatRoom {


	ObjectMapper mapper = new ObjectMapper();

	private Set<WebSocketUser> userList = new HashSet<>();

	// 추가하는 메소드
	public void add(WebSocketUser session, MemberDto memberDto) throws IOException {
		int member_no = session.getMember_no();
				
		userList.add(session);
		
		String member_name = memberDto.getMember_name();
		
		
		// 뿌려주는 메소드 추가 예정(접속 메시지 뿌려주는 기능 )
		ChatData chatdata = ChatData.builder().member_no(member_no)
				.text("[" + member_name + "] 님이 접속했습니다.").status(0).build();

		msgSend(session, chatdata,member_name);
	}

	// 제거하는 메소드
	public void remove(WebSocketUser session, MemberDto memberDto) throws IOException {
		userList.remove(session);

		int member_no = session.getMember_no();

		String member_name = memberDto.getMember_name();

		ChatData chatdata = ChatData.builder().member_no(session.getMember_no())
				.text("[" + memberDto.getMember_name() + "] 님이 종료했습니다.").build();
		msgSend(session, chatdata,member_name);
	}

	// 메시지 뿌려주는 메소드 (서버에서는 메시지 상태 값에 따라 실행)
	public void msgSend(WebSocketUser user, ChatData chatdata, String member_name) throws IOException {

		int member_no = user.getMember_no();
		
		String come_text = chatdata.getText();
		if(chatdata.getStatus()==2) {
		chatdata.setText("["+member_name+"] " + come_text);
		}
		String text = mapper.writeValueAsString(chatdata);
		TextMessage msg = new TextMessage(text);
		for (WebSocketUser users : userList) {
			users.getWs().sendMessage(msg);
		}
	}
	
	public int count () {
		return userList.size();
	}
	
	public boolean isEmpty() {
		return count()==0;
	}

}