package com.kh.siistory.websocket;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.siistory.entity.MemberDto;
import com.kh.siistory.repository.MemberDao;
import com.kh.siistory.vo.ChatData;
import com.kh.siistory.vo.WebSocketUser;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ChatServer extends TextWebSocketHandler {

//변환 도구 	
	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MemberDao memberDao;
	
	Map<WebSocketSession,WebSocketUser> chatuserList = new HashMap<>();
	Map<Integer, ChatRoom> roomList = new HashMap<>();

	// session을 제거한다 . > 해당 번호의 룸을 제거한다.(모두 나가면)
	// 1.사용자가 페이지에 접속한다. (주소에 room값을 들고 온다 .)
	// 2.클라이언트에서 서버로 접속했다고 메시지를 보낸다 (방번호를 함께 보낸다.) .
	// 3.해당 번호의 룸을 생성한다.(이미 있으면 패스) > session 추가한다.
	// 2번 메시지가 오면 해당 방의 session 들에게 메시지를 뿌려준다 .

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		int member_no = (int) session.getAttributes().get("member_no");
		MemberDto memberDto = memberDao.getMember(member_no);
		WebSocketUser user = WebSocketUser.builder()
				.member_no(memberDto.getMember_no())
				.email(memberDto.getEmail())
				.ws(session)				
				.build();
		
		chatuserList.put(session,user);
		
		log.info("chat user list = {}", chatuserList);
		
	}

	// session을 제거한다 . > 해당 번호의 룸을 제거한다.(모두 나가면)
	// 1.사용자가 페이지에 접속한다. (주소에 room값을 들고 온다 .)
	// 2.클라이언트에서 서버로 접속했다고 메시지를 보낸다 (방번호를 함께 보낸다.) .
	// 3.해당 번호의 룸을 생성한다.(이미 있으면 패스) > session 추가한다.
	// 2번 메시지가 오면 해당 방의 session 들에게 메시지를 뿌려준다 .

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 메시지 확인
		String payload = message.getPayload();

		// chatdata 형식에 맞게 읽음 제이슨
		ChatData chatdata = mapper.readValue(payload, ChatData.class);
		
		System.out.println("챗 서버 $$$$ 메시지 확인" + chatdata);

		// chatdata.get 스텟에 따라 필요한 명령문 실행
		int room_no = chatdata.getRoom_no();
		int member_no = chatdata.getMember_no();
		int status = chatdata.getStatus();
		
		MemberDto memberDto = memberDao.getMember(member_no);
		String member_name = memberDto.getMember_name();
		if (status == 0) {

			//먼저 room 존재여부 확인 및 생성
			if (!roomList.containsKey(room_no)) {
				ChatRoom chatroom = new ChatRoom();
				roomList.put(room_no, chatroom);
			}
			
			//
			roomList.get(room_no).add(chatuserList.get(session),memberDto);	
		} else if (chatdata.getStatus()==1) {
			roomList.get(room_no).remove(chatuserList.get(session),memberDto);
			
			if(roomList.get(room_no).isEmpty()) {
				roomList.remove(room_no);
			}
		} else if (chatdata.getStatus()==2) {
			// 메시지를 뿌려라 
			roomList.get(room_no).msgSend(chatuserList.get(session), chatdata,member_name);
			
		}

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//		chatuserList.remove(session);
		log.info("접속 종료!!!!");
//		friendSend(session, 1);
		WebSocketUser target = WebSocketUser.builder().ws(session).build();
		
		chatuserList.remove(target);
		
		
		
	}



}
