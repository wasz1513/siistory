package com.kh.siistory.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.siistory.entity.AlarmDto;
import com.kh.siistory.entity.FriendDto;
import com.kh.siistory.repository.AlarmDao;
import com.kh.siistory.repository.FriendDao;
import com.kh.siistory.repository.MemberDao;
import com.kh.siistory.vo.AlarmData;
import com.kh.siistory.vo.ChatData;
import com.kh.siistory.vo.WebSocketUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessengerServer extends TextWebSocketHandler {
	//메신저에서 사용할 메시지 상태값 설정
	public static final int enter = 0;
	public static final int exit = 1;
	public static final int message = 2;
	public static final int refresh = 3;
	
	//알람에서 사용할 메시지 상태값 설정
	public static final int checkon = 4;
	public static final int checkoff = 5;
	public static final int make = 6;
	public static final int confirm = 7;
	
	

	// 계획
	/*
	 * [1] 유저가 접속한다. (타겟이 나인 DB 시간정렬하여 가져온다.) <<<<<< 내가 기준임 - 접속하면 어딘가에 저장된 내 알람
	 * 리스트를 불러와서 표시
	 * 
	 * [2] 좋아요를 누른다 or 해제한다 (onmessage에서 조건별 처리) <<<<타인 기준임
	 * 
	 * ### (체크) on - 메시지를 보낸다. - 메시지 형태를 확인한다. - 조건이 on이라면 컨텐츠 보유 대상(작성한
	 * target_no)으로 데이터 DB저장
	 * 
	 * ### (체크) off - 메시지를 보낸다. - 메시지 형태를 확인한다. - 조건이 off라면 컨텐츠 보유 대상(작성한
	 * target_no)으로 데이터 DB삭제
	 * 
	 * 
	 * [3] 알람을 확인한다 <<<<< 내가 기준임 ### (알람확인) confirm - 컨텐츠 클릭 > 페이지 이동 + 메시지를 보낸다. -
	 * 메시지 형태를 확인한다. - 조건이 confirm이면 target_no 가 자신인 데이터 DB삭제
	 * 
	 * 
	 * ### 알림 등록 후 5일이 지나면 알림 자동 삭제 처리
	 * 
	 */

	// 자바 객체 > json 문자열 변환 도구
	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private FriendDao friendDao;

	@Autowired
	private AlarmDao alarmDao;
	
	@Autowired
	private MemberDao memberDao;

	// 사용자 저장을 위한 set 저장소 생성
	//	Set<WebSocketSession> userList = new HashSet<>();
	Set<WebSocketUser> userList = new HashSet<>();

	// 클라이언트와 연결되면 실행하는 메소드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

		int no = (int) session.getAttributes().get("member_no");

		String email = (String) session.getAttributes().get("email");
		WebSocketUser user = WebSocketUser.builder().member_no(no).email(email).ws(session).build();
		userList.add(user);
		// 접속 or 종료를 한다 > 전체 조회 (기본) , 나 접속했다 정보 메시지를 친구들에게 뿌려준다 > 친구들은 해당 정보를 받아서 리스트에서
		// 상태값을 변경한다. > 상태값 변경된 리스트를 받는다.
		// 목록 리스트 구하는 메소드
		List<FriendDto> list = Allrefresh(session, no);

		// 나에게 리스트 전송하는 메소드
		sendList(user, list);
		// 접속을 하면 친구들에게 갱신 메시지 보낸다 . >> 친구들은 메시지 받으면 갱신처리
		friendSend(session, 0);

		//////////////////////////////////////////////////////
		// 여기서부터 알람 기능 진행.
		//////////////////////////////////////////////////////
		// 접속시 > 알람을 보낸다.
		// [1]접속하면 나의 알람 목록을 불러온다 . (target_no <<나 기준)
		// 알람 목록은 alarmData 형식으로 뿌려줘야하므로 가공 먼저 한다.
		
		List<AlarmDto> alarmList = alarmDao.getList(no);
		log.info("알람 리스트 = {}",alarmList);
		// [2]리스트를 나에게 보낸다.
		alarmSendList(session, alarmList);
		
		sendAlarmData(user);
		

	}

	
	
	
	
	
	// 클라이언트가 서버로 메시지 전송했을 때 실행하는 메소드   Smethod
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

		if (data.getStatus() == 0 || data.getStatus() == 1 || data.getStatus() == 2) {

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
			
			// 갱신완료
			// 나에게 보낸다.
			// 갱신하여 나에게 리스트 보낸다 .
			String showFriend = mapper.writeValueAsString(friendList);

			TextMessage msg = new TextMessage(showFriend);

			session.sendMessage(msg);

			
		//좋아요 라면 ? 
		} else if (data.getStatus()==4 ) {
			AlarmDto alarmDto = AlarmDto.builder()
			.pusher_no(data.getMember_no())
			.target_no(data.getTarget_no())
			.content_no(data.getContent_no())
			.content_type(data.getContent_type())
			.content_play(data.getContent_play())
			.build();
			System.out.println(alarmDto.getTarget_no());
			alarmDao.insert(alarmDto);
			log.info("알람 등록 완료");
			
		}


	}

	// 클라이언트와 연결 종료시 실행되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		friendSend(session, 1);
		WebSocketUser target = WebSocketUser.builder().ws(session).build();
		userList.remove(target);

	}

	////////////////////////////////////////////////////////
	//리스트 갱신 메소드 
	//////////////////////////////////////////////////////

	public List<FriendDto> Allrefresh(WebSocketSession session, int no) throws IOException {
		// [1]친구 리스트 생성 및 정렬 (친구번호 순) 코드 >> 추후 이름순으로 바꿀 예정

		List<FriendDto> friendList = friendDao.getList(no);
		Collections.sort(friendList, new Comparator<FriendDto>() {
			@Override
			public int compare(FriendDto o1, FriendDto o2) {
				return o1.getFriend() - o2.getFriend();
			}
		});

		// [2]전체 유저 저장시 항시 정렬 (일단 저장소를 Array리스트로 변환 ) >> 추후 이름순으로 바꿀 예정
		List<WebSocketUser> connectUser = new ArrayList<WebSocketUser>(userList);
		// 정렬
//	System.out.println("========" + connectUser.size() + "=========");
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
			for (int i = 0; i < friendList.size(); i++) {
				FriendDto dto = friendList.get(i);

				// 전체목록에서 dto랑 같은 번호를 찾는다(찾으면 신규 목록에 추가)
				for (int v = 0; v < connectFriend.size(); v++) {
					WebSocketUser ws = connectFriend.get(v);

					if (dto.getFriend() == ws.getMember_no()) {
						find.add(ws);
					}

				}
			}
		}

		ChatData msg = ChatData.builder().member_no(no).status(status_no).text("refresh").build();

//	msg.setText("");

		String test = mapper.writeValueAsString(msg);
		TextMessage text = new TextMessage(test);

		for (WebSocketUser finds : find) {
			finds.getWs().sendMessage(text);
			;
		}

	}

	// 나에게 친구 리스트 보내는 메소드.
	public void sendList(WebSocketUser user, List<FriendDto> friendList) throws IOException {

		String showFriend = mapper.writeValueAsString(friendList);

		TextMessage msg = new TextMessage(showFriend);
//	System.out.println(msg.getPayload());

		user.getWs().sendMessage(msg);
	}
	
	////////////////////////////////////////////////////////
	//알람 종합 메소드 
	////////////////////////////////////////////////////////

	// 나에게 알람 리스트 보내는 메소드
	public void alarmSendList(WebSocketSession session, List<AlarmDto> alarmList) throws IOException {
		String showAlarm = mapper.writeValueAsString(alarmList);
		TextMessage msg = new TextMessage(showAlarm);
		session.sendMessage(msg);
	}
	
	//알람 멘트 가공 및 전송 메소드
	public void sendAlarmData(WebSocketUser user) throws IOException {
		int target_no = user.getMember_no();
		List<AlarmDto> alarmList = alarmDao.getList(target_no);
		
	
		//1차 누구의 게시글인가
		//2차 게시글의 번호는 무엇인가
		//해당 게시글의 정보
		// 출력 형식의 글 형식의 스트링 리스트를 만들어야한다.
		//8 님이 24 님의 3 컨텐츠번호 board컨텐츠 형식 make행동을 좋아합니다!
		String alarm_ment;
		String pusher_name;
		String target_name;
		String content_name = null;
		String play_ment = null;
		
		List<AlarmData> mentList = new ArrayList<>(); 
		
		for(AlarmDto adto : alarmList) {
			
			//누구 님이
			pusher_name = memberDao.getMember(adto.getPusher_no()).getMember_name();
			// 누구 님의 	
			target_name = memberDao.getMember(adto.getTarget_no()).getMember_name();
			
			// 뭐를
			if (adto.getContent_type().equals("board")) {
				content_name = "게시물";			
			} else if (adto.getContent_type().equals("reply")) {
				content_name = "댓글";
			}
			
			//행동
			if(adto.getContent_play().equals("make")) {
				play_ment = "작성했습니다.";
				
				alarm_ment = "["+pusher_name+"] 님이 "+content_name+" 를 "+play_ment;
				
				AlarmData alarmData = AlarmData.builder()
						.status(0).pusher_no(adto.getPusher_no())
						.text(alarm_ment)
						.target_no(adto.getTarget_no())
						.content_no(adto.getContent_no())
						.content_type(adto.getContent_type())
						.content_play(adto.getContent_play())
						.build();
				
				mentList.add(alarmData);	
				
			} else if (adto.getContent_play().equals("good")) {
				play_ment = "좋아합니다.";
				
				alarm_ment = "["+pusher_name+"] 님이 "+target_name+
						" 님의 "+content_name+" 를 "+play_ment;
			
				AlarmData alarmData = AlarmData.builder()
						.status(0).pusher_no(adto.getPusher_no())
						.text(alarm_ment)
						.target_no(adto.getTarget_no())
						.content_no(adto.getContent_no())
						.content_type(adto.getContent_type())
						.content_play(adto.getContent_play())
						.build();
				mentList.add(alarmData);	
			}
			
	
		}
		
		
		log.info("알람 리스트 == {}", mentList);
		
		//리스트 만들었으면 보내기 전에 변환 처리 
		String ment = mapper.writeValueAsString(mentList);
		
		TextMessage msg = new TextMessage(ment);
		log.info("알람 메시지 = {}",msg.getPayload());
		user.getWs().sendMessage(msg);
		
	}
	
	
	
	
	
}
