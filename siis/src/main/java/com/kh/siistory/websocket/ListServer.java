package com.kh.siistory.websocket;

import java.io.IOException;
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
import com.kh.siistory.vo.ChatData;
import com.kh.siistory.vo.WebSocketUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListServer extends TextWebSocketHandler {

	public static final int enter = 0;
	public static final int exit = 1;
	public static final int message = 2;
	public static final int refresh = 3;

	// 자바 객체 > json 문자열 변환 도구
	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private FriendDao friendDao;
	
	// 사용자 저장을 위한 set 저장소 생성
//	Set<WebSocketSession> userList = new HashSet<>();
	Set<WebSocketUser> userList = new HashSet<>();

	public List<FriendDto> Allrefresh(WebSocketSession session, int no) throws IOException {
		// [1]친구 리스트 생성 및 정렬 (친구번호 순) 코드 >> 추후 이름순으로 바꿀 예정

		List<FriendDto> friendList = friendDao.getList(no);
		Collections.sort(friendList, new Comparator<FriendDto>() {
			@Override
			public int compare(FriendDto o1, FriendDto o2) {
//				if (o1.getFriend() > o2.getFriend()) {
//					return 1;
//				} else if (o1.getFriend() < o2.getFriend()) {
//					return -1;
//				} else {
//					return 0;
//				}
				return o1.getFriend() - o2.getFriend();
			}
		});

		// [2]전체 유저 저장시 항시 정렬 (일단 저장소를 Array리스트로 변환 ) >> 추후 이름순으로 바꿀 예정
		List<WebSocketUser> connectUser = new ArrayList<WebSocketUser>(userList);
		// 정렬
//		System.out.println("========" + connectUser.size() + "=========");
		Collections.sort(connectUser, new Comparator<WebSocketUser>() {

			@Override
			public int compare(WebSocketUser o1, WebSocketUser o2) {
				if ((int) o1.getMember_no() > (int) o2.getMember_no()) {
					return 1;
				} else if ((int) o1.getMember_no() < (int) o2.getMember_no()) {
					return -1;
				} else {
					return 0;
				}
			}
		});

		// 상태 정보 확인
		for (int v = 0; v < connectUser.size(); v++) {
			int getno = (connectUser.get(v).getMember_no());

			for (int i = 0; i < friendList.size(); i++) {
				int getfno = friendList.get(i).getFriend();
				if (getno == getfno) {

					friendList.get(i).setConnect_state("접속중");

				} else if (getno != no) {
					friendList.get(i).setConnect_state("미접속");

				}
//				System.out.println("get fno = " + getfno + "상태값 = " + friendList.get(i).getConnect_state());
//				System.out.println(
//						"no = " + friendList.get(i).getFriend() + "접속상태=" + friendList.get(i).getConnect_state());
			}
		}

		return friendList;

	}

	// 친구들에게만 보내는 메소드
	public void friendSend(WebSocketSession session, int status_no) throws IOException {
		// 세션 집합에서 친구만 뺀다 .
		int no = (int) session.getAttributes().get("member_no");

		// [1]이미 정렬 및 갱신된 친구 리스트
		List<FriendDto> friendList = Allrefresh(session, no);

		// [2]전체 유저 저장시 항시 정렬 (일단 저장소를 Array리스트로 변환 ) >> 추후 이름순으로 바꿀 예정
		List<WebSocketUser> connectFriend = new ArrayList<WebSocketUser>(userList);
		// 정렬
//		System.out.println("===전체접속 숫자=====" + connectFriend.size() + "=========");
		Collections.sort(connectFriend, new Comparator<WebSocketUser>() {
			@Override
			public int compare(WebSocketUser o1, WebSocketUser o2) {
				if ((int) o1.getMember_no() > (int) o2.getMember_no()) {
					return 1;
				} else if ((int) o1.getMember_no() < (int) o2.getMember_no()) {
					return -1;
				} else {
					return 0;
				}
			}
		});

		// [3]존재여부 확인 및 비교 친구이면 find 에 추가 한다 .
		Set<WebSocketUser> find = new HashSet<WebSocketUser>();
		if (friendList != null && !friendList.isEmpty()) {
			// 내 친구 리스트를 불러온다.
//			int index;
			for (int i = 0; i < friendList.size(); i++) {
				FriendDto dto = friendList.get(i);

				// 전체목록에서 dto랑 같은 번호를 찾는다(찾으면 신규 목록에 추가)
				for (int v = 0; v < connectFriend.size(); v++) {
					WebSocketUser ws = connectFriend.get(v);
//				System.out.println("친구들"+friendList.get(i).getFriend()+"  총인원"+friendList.size());
//				System.out.println("접속자  객체 수  = "+connectFriend.size()+ "   접속자"+connectFriend.size());
//				System.out.println("ws = "+ws);
					if (dto.getFriend() == ws.getMember_no()) {
//				System.out.println("비교하는 접속 유저 = "+ws.getMember_no()+ " 비교 친구 넘버  = "+dto.getFriend());

						find.add(ws);
					}
//				System.out.println("인덱스  : : : "+index);
//				if(index == friendList.size())
//					break;
//				index++;
//				dto = friendList.get(index);
				}
			}
		}
//		for (int v = 0; v < connectFriend.size(); v++) {
//			int getno = Integer.parseInt(connectFriend.get(v).getAttributes().get("member_no").toString());
//			System.out.println(connectFriend.get(v).getAttributes().get("member_no"));
//			System.out.println("getno = " + getno + "===================");
//			for (int i = 0; i < friendList.size(); i++) {
//				int getfno = friendList.get(i).getFriend();
//				System.out.println("그냥 친구 === "+getfno+"  컨넥션  "+getno);
//				
//				if (getno != getfno) {
//					connectFriend.remove(v);
//				}
//
//			}
//		}

		ChatData msg = ChatData.builder().member_no(no).status(status_no).text("refresh").build();
//		no=24, text=null,status=0 ,
//		System.out.println("finds 결과값 = =" + find.size());

//		msg.setText("");

		String test = mapper.writeValueAsString(msg);
		TextMessage text = new TextMessage(test);
//		System.out.println("-------------");
//		System.out.println("text = " + text.getPayload());
//		System.out.println("-------------");

		for (WebSocketUser finds : find) {
			finds.getWs().sendMessage(text);
			;
		}

	}

	public void sendList(WebSocketUser user, List<FriendDto> friendList) throws IOException {

		String showFriend = mapper.writeValueAsString(friendList);

		TextMessage msg = new TextMessage(showFriend);
//		System.out.println(msg.getPayload());

		user.getWs().sendMessage(msg);
	}

	// 클라이언트와 연결되면 실행하는 메소드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

//		log.info("접속 테스트  session = {}", session);
		int no = (int) session.getAttributes().get("member_no");

		String email = (String) session.getAttributes().get("email");
		WebSocketUser user = WebSocketUser.builder().member_no(no).email(email).ws(session).build();
		userList.add(user);

//		log.info("접속 no={}", no);
//		log.info("접속자 수 = {}", userList.size());

		// 접속 or 종료를 한다 > 전체 조회 (기본) , 나 접속했다 정보 메시지를 친구들에게 뿌려준다 > 친구들은 해당 정보를 받아서 리스트에서
		// 상태값을 변경한다. > 상태값 변경된 리스트를 받는다.

//		String text = "{번호 : '번호', 메시지 : '?', 상태 : 'enter' or 'out' or 'message'  ";
//		Message(text);
		// 접속했을 때 기본 접속 리스트 셋팅

		// 목록 리스트 구하는 메소드
		List<FriendDto> list = Allrefresh(session, no);

		// 나에게 리스트 전송하는 메소드
		sendList(user, list);

		// 접속을 하면 친구들에게 갱신 메시지 보낸다 . >> 친구들은 메시지 받으면 갱신처리
		friendSend(session, 0);

	}

	// 클라이언트가 서버로 메시지 전송했을 때 실행하는 메소드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

		String payload = message.getPayload();
		ChatData data = mapper.readValue(payload, ChatData.class);
		System.out.println(data);
		
		

		int no = (int) session.getAttributes().get("member_no");

//		log.info("메시지좀 확인 하자 = {}", data);

		// 내 친구 리스트 생성
		List<FriendDto> friendList = Allrefresh(session, no);

		// 유저가 접속한다 > 메시지가 서버에 전송된다. >내 친구중에 있는지 확인한다 > 있으면 갱신한다 > 갱신이 되었다면 나에게 보낸다.

		if (data.getStatus() == 0 || data.getStatus() == 1) {

			for (FriendDto fdto : friendList) {
				// 접속한 사람이 친구 중에 있는가?
				if (fdto.getFriend() == data.getMember_no()) {
					// 있다면? 상태 값에 따라 갱신해라
					// 접속이면
					if (data.getStatus() == 0) {
						fdto.setConnect_state("접속중");
					}
					// 종료면
					else if (data.getStatus() == 1) {
						fdto.setConnect_state("미접속");
					}

				}

			}
//		System.out.println("||||||||친구리스트 ||||| =  "+friendList);
			// 갱신완료
			// 나에게 보낸다.
			// 갱신하여 나에게 리스트 보낸다 .
//		sendList(session, friendList);
		}
			String showFriend = mapper.writeValueAsString(friendList);

			TextMessage msg = new TextMessage(showFriend);
//			System.out.println(msg.getPayload());
//			System.out.println("msg -==-====-=-=---=--" + msg.getPayload());
			// 나에게는 친구목록을 전송
//System.out.println("session get no = "+  session.getAttributes().get("member_no"));
//System.out.println("session id  = "+ session.getId());

			//나에게 친구목록
//			System.out.println("★★★★나는 누구? = " + session.getId()
//			+"   넘버는? ==" + session.getAttributes().get("member_no")
//					); 
//			
//			System.out.println(userList);
			
			// 목록 리스트 구하는 메소드
			
//			List<FriendDto> list = Allrefresh(session, no);
//
//			
//			String email = (String) session.getAttributes().get("email");
//			WebSocketUser user = WebSocketUser.builder().member_no(no).email(email).ws(session).build();
//			
//			System.out.println(user);
			
			// 나에게 리스트 전송하는 메소드
			session.sendMessage(msg);
			
			
	

	}

	// 클라이언트와 연결 종료시 실행되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		friendSend(session, 1);
		WebSocketUser target = WebSocketUser.builder().ws(session).build();
		userList.remove(target);

	}

}
