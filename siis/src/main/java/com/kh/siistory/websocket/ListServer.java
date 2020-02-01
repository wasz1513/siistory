package com.kh.siistory.websocket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.siistory.entity.FriendDto;
import com.kh.siistory.repository.FriendDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ListServer extends TextWebSocketHandler {

// 클라이언트와 연결되면 실행하는 메소드
	/*
	 * 접속을 하면 무엇을 할 것인가? 리스트 출력을 위한 유저 정보 저장
	 * 
	 * 
	 */

	@Autowired
	private FriendDao friendDao;

	// 전체 전송을 위한 메소드
	private void allSending() {

	}

	// 사용자 저장을 위한 set 저장소 생성
	Set<WebSocketSession> userList = new HashSet<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

		log.info("접속 테스트 ");
		userList.add(session);

		int no = (int) session.getAttributes().get("no");
		log.info("접속 no={}", no);
		log.info("접속자 수 = {}", userList.size());

		// 접속하자마자 조회 친구 접속목록(+상태)
		// 각 유저들은 각자 접속을 할 때마다 내 친구들 목록을 갱신하게 된다.

		// 필수 = A가 접속하면 접속 유저 집합과 내 친구 집합을 비교하여 목록을 갱신한다. >> 1회접속에 1회 검색.
		// 부가적 기능 = 각 유저들은 접속을 하면 해당 유저의 친구들에게 나의 정보 메시지를 뿌린다 >> 친구들은 그것을 받고 목록을 (연결
		// 시작&종료 부분 갱신 전체조회 x)갱신한다. << 일단 보류

		// 갱신된 목록을 서버 > 클라이언트로 list형태로 받아서 출력한다.

		// [1]친구 리스트 생성 및 정렬 (친구번호 순) 코드 >> 추후 이름순으로 바꿀 예정
		List<FriendDto> friendList = friendDao.getList();
		Collections.sort(friendList, new Comparator<FriendDto>() {
			@Override
			public int compare(FriendDto o1, FriendDto o2) {
				if (o1.getFriend() > o2.getFriend()) {
					return 1;
				} else if (o1.getFriend() < o2.getFriend()) {
					return -1;
				} else {
					return 0;
				}
			}
		});

		// [2]전체 유저 저장시 항시 정렬 (일단 저장소를 Array리스트로 변환 ) >> 추후 이름순으로 바꿀 예정
		List<WebSocketSession> connectUser = new ArrayList<WebSocketSession>(userList);
		// 정렬
		Collections.sort(connectUser, new Comparator<WebSocketSession>() {

			@Override
			public int compare(WebSocketSession o1, WebSocketSession o2) {
				if ((int) o1.getAttributes().get("no") > (int) o2.getAttributes().get("no")) {
					return 1;
				} else if ((int) o1.getAttributes().get("no") < (int) o2.getAttributes().get("no")) {
					return -1;
				} else {
					return 0;
				}
			}
		});

		// [3]존재여부 확인 및 비교 (전체유저 기준으로 확인) >> 추후 이름순으로 바꿀 예정

		for (int v = 0; v < connectUser.size(); v++) {
			int getno = Integer.parseInt(connectUser.get(v).getAttributes().get("no").toString());
			System.out.println(connectUser.get(v).getAttributes().get("no"));

			for (int i = 0; i < friendList.size(); i++) {
				int getfno = friendList.get(i).getFriend();

				if (getno == getfno) {

					friendList.get(i).setConnect_state("접속중");

				} else {
					friendList.get(i).setConnect_state("미접속");
				}
				System.out.println(
						"no = " + friendList.get(i).getFriend() + "접속상태=" + friendList.get(i).getConnect_state());

				// 상태 변경 완료 클라이언트로 갱신된 친구 리스트 쏜다. 자바 오브젝트 jackson 형태로 변경하여 전송
				
				

			}

		}

		System.out.println("변환 후 리스트 = " + connectUser.get(0).getAttributes().get("no"));

	}

	// 클라이언트가 서버로 메시지 전송했을 때 실행하는 메소드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

		ObjectMapper mapper = new ObjectMapper();

	}

	// 클라이언트와 연결 종료시 실행되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
	}

	public static FriendDto friendSet() {
		FriendDto friendDto = new FriendDto();
//			friendDto.setFriend(friend);

		return friendDto;

	}

}
