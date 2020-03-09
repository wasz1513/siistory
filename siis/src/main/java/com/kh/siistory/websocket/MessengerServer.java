package com.kh.siistory.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.siistory.controller.MainPageController;
import com.kh.siistory.entity.AlarmDto;
import com.kh.siistory.entity.BoardLikeDto;
import com.kh.siistory.entity.ReplyLikeDto;
import com.kh.siistory.repository.AlarmDao;
import com.kh.siistory.repository.BoardLikeDao;
import com.kh.siistory.repository.FollowDao;
import com.kh.siistory.repository.MemberDao;
import com.kh.siistory.repository.ReplyLikeDao;
import com.kh.siistory.vo.AlarmData;
import com.kh.siistory.vo.FriendListData;
import com.kh.siistory.vo.MemberFollowVo;
import com.kh.siistory.vo.MessengerData;
import com.kh.siistory.vo.WebSocketUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessengerServer extends TextWebSocketHandler {
	// 메신저에서 사용할 메시지 상태값 설정
	public static final int enter = 0;
	public static final int exit = 1;
	public static final int message = 2;
	public static final int Frefresh = 3;

	// 알람에서 사용할 메시지 상태값 설정
	public static final int checkon = 4;
	public static final int checkoff = 5;
	public static final int make = 6;
	public static final int click = 7;
	public static final int Arefresh = 8;
	public static final int setting = 9;

	// 친구추가 알람 상태값
	public static final int addfriend = 10;

	// 채팅 요청이 들어왔을 때
	public static final int chatconnect = 20;
	
	// 전체 유저 카운팅
	public static final int user_count = 30;

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

//	@Autowired
//	private FriendDao friendDao;

	@Autowired
	private FollowDao followDao;

	@Autowired
	private AlarmDao alarmDao;

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private BoardLikeDao boardlikeDao;

	@Autowired
	private ReplyLikeDao replylikeDao;
	
	@Autowired
	private HttpSession session0;

	// 사용자 저장을 위한 set 저장소 생성
	// Set<WebSocketSession> userList = new HashSet<>();
	Set<WebSocketUser> userList = new HashSet<>();

	// 클라이언트와 연결되면 실행하는 메소드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		if ((String) session.getAttributes().get("email") != null) {


			int no = (int) session.getAttributes().get("member_no");

			String email = (String) session.getAttributes().get("email");
			WebSocketUser user = WebSocketUser.builder().member_no(no).email(email).ws(session).build();
			userList.add(user);
			// 접속 or 종료를 한다 > 전체 조회 (기본) , 나 접속했다 정보 메시지를 친구들에게 뿌려준다 > 친구들은 해당 정보를 받아서 리스트에서
			// 상태값을 변경한다. > 상태값 변경된 리스트를 받는다.
			// 목록 리스트 구하는 메소드
//		List<FriendDto> list = Allrefresh(session, no);
//			List<MemberFollowVo> list = Allrefresh(session, no);

			// 나에게 리스트 전송하는 메소드 (FriendListData 클래스 형식에 맞춰서 진행)
			FriendListData fdata = FriendListData.builder().member_no(no).status(0).text("friend_list").build();

			sendList(user, fdata);

			// 접속을 하면 친구들에게 갱신 메시지 보낸다 . >> 친구들은 메시지 받으면 갱신처리
			friendSend(session, 0);

			//////////////////////////////////////////////////////
			// 여기서부터 알람 기능 진행.
			//////////////////////////////////////////////////////
			// 접속시 > 알람을 보낸다.
			// [1]접속하면 나의 알람 목록을 불러온다 . (target_no <<나 기준)
			// 알람 목록은 alarmData 형식으로 뿌려줘야하므로 가공 먼저 한다.

			MessengerData cdata = MessengerData.builder().status(9).text("setting").build();

			String text = mapper.writeValueAsString(cdata);
			TextMessage msg = new TextMessage(text);

			session.sendMessage(msg);		
	
		}
	}

	// 클라이언트가 서버로 메시지 전송했을 때 실행하는 메소드 Smethod
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		if (message.getPayload() != null && session.getAttributes().get("member_no") != null) {
			
			String payload = message.getPayload();

			MessengerData data = mapper.readValue(payload, MessengerData.class);

			int no = (int) session.getAttributes().get("member_no");

			log.info("메시지좀 확인 하자 = {}", data);

			BoardLikeDto boardlikeDto = BoardLikeDto.builder().member_no(data.getPusher_no())
					.board_no(data.getContent_no()).build();

			ReplyLikeDto replylikeDto = ReplyLikeDto.builder().member_no(data.getPusher_no())
					.reply_no(data.getContent_no()).build();

			AlarmDto alarmDto = AlarmDto.builder().pusher_no(data.getPusher_no()).target_no(data.getTarget_no())
					.content_no(data.getContent_no()).content_type(data.getContent_type())
					.content_play(data.getContent_play()).build();


			// 내 친구 리스트 생성
			List<MemberFollowVo> friendList = Allrefresh(session, no);

			// 유저가 접속한다 > 메시지가 서버에 전송된다. >내 친구중에 있는지 확인한다 > 있으면 갱신한다 > 갱신이 되었다면 나에게 보낸다.

			if (data.getStatus() == 0 || data.getStatus() == 1 || data.getStatus() == 3) {

				for (MemberFollowVo fdto : friendList) {
					System.out.println(fdto);
					// 접속한 사람이 친구 중에 있는가?
					if (fdto.getMember_no() == data.getMember_no()) {
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
				FriendListData fdata = FriendListData.builder().member_no(no).flist_data(friendList).user_count(userList.size()).status(3)
						.text("refresh").build();

				String text = mapper.writeValueAsString(fdata);

				TextMessage msg = new TextMessage(text);

				session.sendMessage(msg);

			} else if (data.getStatus() == 4 || data.getStatus() == 5 || data.getStatus() == 6 || data.getStatus() == 7
					|| data.getStatus() == 8 || data.getStatus() == 10) {

				// 좋아요 라면 ?
				if (data.getStatus() == 4) {
					// 정보를 담았다 .
					if (data.getContent_type().equals("board")) {
//					게시글 좋아요 테이블DB + 알람 DB 저장
//					boardlikeDao.insert(boardlikeDto);
						alarmDao.insert(alarmDto);
					} else if (data.getContent_type().equals("reply")) {
//					replylikeDao.insert(replylikeDto);
						alarmDao.insert(alarmDto);
					}
					// 취소라면 ?
				} else if (data.getStatus() == 5) {
					
					if (data.getContent_type().equals("board")) {
//					boardlikeDao.delete(boardlikeDto);
						alarmDao.delete(alarmDto);

						// 삭제했으니 갱신하도록 다시 메시지 뿌려라
					} else if (data.getContent_type().equals("reply")) {
//					replylikeDao.delete(replylikeDto);
						alarmDao.delete(alarmDto);
					} else if (data.getContent_type().equals("friend")) {
						alarmDao.delete(alarmDto);
					}
					// 게시글 작성 시
				} else if (data.getStatus() == 6) {
					alarmDao.insert(alarmDto);
				}
				// 알람 확인 시 (좋아요 , 보드 , 친구 , 댓글 전부 alarmDto 에 정보 들어가있음)
				else if (data.getStatus() == 7) {
					alarmDao.delete(alarmDto);

				}

				// 갱신 요청이 들어왔다면?
				else if (data.getStatus() == 8) {
					for (WebSocketUser user1 : userList) {
						if (user1.getMember_no() == data.getMember_no()) {
							sendAlarmData(user1);
						}
					}
				}
				// 친구 추가 요청을 했다면?
				else if (data.getStatus() == 10) {
					
					alarmDao.insert(alarmDto);
				}

				// 갱신처리를 제외한 모든 조건의 경우 갱신처리 요청을 보낸다. (9 메시지를 클라이언트로 보낸다 .)
				// 클라이언드 : 9메시지를 받으면 다시 8번 메시지를 보내서 갱신 처리 진행.
				if (data.getStatus() != 8) {

					MessengerData cdata = MessengerData.builder().status(9).text("setting").build();

					String text = mapper.writeValueAsString(cdata);
					TextMessage msg = new TextMessage(text);
					// 이것은 나 자신의 갱신 처리를 위한 것. 따라서 행위자는 대상에게 9번 갱신 요청 -> 대상의 클라이언트에서 받고 자신에게 8번 메시지
					// (갱신)을 보내야한다.
					// 단 대상이 나인 6 make 와 7 클릭의 경우 DB를 지우고 나에게 갱신 메시지를 보내야한다.

					if (data.getStatus() == 7) {
						for (WebSocketUser user2 : userList) {
							if (user2.getMember_no() == data.getMember_no()) {
								user2.getWs().sendMessage(msg);
							}
						}
					} else {

						for (WebSocketUser user2 : userList) {
							if (user2.getMember_no() == data.getTarget_no()) {
								user2.getWs().sendMessage(msg);
							}
						}
					}

				}
				// 채팅 초대 메시지 수신 시
			} else if (data.getStatus() == 20) {
				// 해당 채팅 서버 페이지로 접속하라고 해야한다.
				// 서버에서는 메시지만 전송하고 행동은 클라이언트에서 진행
				int target_no = data.getTarget_no();
				int room_no = data.getRoom_no();
				String http = data.getText();

				MessengerData invite = MessengerData.builder().member_no(no).text("invite message").target_no(target_no)
						.room_no(room_no).status(20).build();

				String text = mapper.writeValueAsString(invite);
				TextMessage msg = new TextMessage(text);

				// 데이터 처리 후 특정 대상에게 보내야 한다.
				// == session 중에 친구 넘버가 있는 사람에게 !send
				for (WebSocketUser user3 : userList) {
					// 상대방에게
					if (user3.getMember_no() == target_no) {

						user3.getWs().sendMessage(msg);
						System.out.println("귓속말 대상 session정보 = " + user3.getWs());
					}
				}
				// 나에게도 하나 보내야한다.(나도 새창을 띄워야하기 때문)
				session.sendMessage(msg);
				// 전체 유저 카운팅
			} else if (data.getStatus()== 30) {
				
			}
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
	// 리스트 갱신 메소드
	//////////////////////////////////////////////////////

	public List<MemberFollowVo> Allrefresh(WebSocketSession session, int no) throws IOException {
		// [1]친구 리스트 생성 및 정렬 (친구번호 순) 코드 >> 추후 이름순으로 바꿀 예정

		List<MemberFollowVo> friendList = followDao.myfriend(no);
		Collections.sort(friendList, new Comparator<MemberFollowVo>() {
			@Override
			public int compare(MemberFollowVo o1, MemberFollowVo o2) {
				return o1.getMember_name().charAt(0) - o2.getMember_name().charAt(0);
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
				int getfno = friendList.get(i).getMember_no();

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
		if (session.getAttributes().get("member_no") != null) {

			// 세션 집합에서 친구만 뺀다 .
			int no = (int) session.getAttributes().get("member_no");
			// [1]이미 정렬 및 갱신된 친구 리스트
			List<MemberFollowVo> friendList = Allrefresh(session, no);
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
					MemberFollowVo dto = friendList.get(i);

					// 전체목록에서 dto랑 같은 번호를 찾는다(찾으면 신규 목록에 추가)
					for (int v = 0; v < connectFriend.size(); v++) {
						WebSocketUser ws = connectFriend.get(v);

						if (dto.getMember_no() == ws.getMember_no()) {
							find.add(ws);
						}

					}
				}
			}

			MessengerData msg = MessengerData.builder().member_no(no).status(status_no).text("refresh").build();

//	msg.setText("");

			String test = mapper.writeValueAsString(msg);
			TextMessage text = new TextMessage(test);

			for (WebSocketUser finds : find) {
				finds.getWs().sendMessage(text);
				;
			}
		}

	}

	// 나에게 친구 리스트 보내는 메소드.
	public void sendList(WebSocketUser user, FriendListData fdata) throws IOException {

		String showFriend = mapper.writeValueAsString(fdata);

		TextMessage msg = new TextMessage(showFriend);
//	System.out.println(msg.getPayload());

		user.getWs().sendMessage(msg);
	}

	////////////////////////////////////////////////////////
	// 알람 종합 메소드
	////////////////////////////////////////////////////////

	// 나에게 알람 리스트 보내는 메소드
	public void alarmSendList(WebSocketSession session, AlarmData adata) throws IOException {
		String showAlarm = mapper.writeValueAsString(adata);
		TextMessage msg = new TextMessage(showAlarm);
		session.sendMessage(msg);
	}

	// 알람 멘트 가공 및 전송 메소드
	public void sendAlarmData(WebSocketUser user) throws IOException {
		int member_no = user.getMember_no();
		System.out.println("멘트 가공-----------target_no =  " + member_no);

		List<AlarmDto> alarmList = alarmDao.getList(member_no);

		System.out.println(alarmList);

		// 1차 누구의 게시글인가
		// 2차 게시글의 번호는 무엇인가
		// 해당 게시글의 정보
		// 출력 형식의 글 형식의 스트링 리스트를 만들어야한다.
		// 8 님이 24 님의 3 컨텐츠번호 board컨텐츠 형식 make행동을 좋아합니다!
		String alarm_ment;
		String pusher_name;
		String target_name;
		String content_name = null;
		String play_ment = null;

		for (AlarmDto adto : alarmList) {
//			System.out.println("============aDTO============");
//			System.out.println(adto);
//			System.out.println("============aDTO============");
			// 누구 님이
			pusher_name = memberDao.getMember(adto.getPusher_no()).getMember_name();
			// 누구 님의
			target_name = memberDao.getMember(adto.getTarget_no()).getMember_name();

			// 뭐를
			if (adto.getContent_type().equals("board")) {
				content_name = "게시물";
			} else if (adto.getContent_type().equals("reply")) {
				content_name = "댓글";
			} else if (adto.getContent_type().equals("friend")) {
				content_name = "친구";
			}

			// 행동
			if (adto.getContent_play().equals("make")) {
				play_ment = "작성했습니다.";

				alarm_ment = "[" + pusher_name + "] 님이 " + content_name + " 을(를) " + play_ment;

				adto.setMent(alarm_ment);

			} else if (adto.getContent_play().equals("good")) {
				play_ment = "좋아합니다.";

				alarm_ment = "[" + pusher_name + "] 님이 " + target_name + " 님의 " + content_name + " 을(를) " + play_ment;

				adto.setMent(alarm_ment);
			}

			else if (adto.getContent_play().equals("add")) {
				play_ment = "요청 했습니다.";

				alarm_ment = "[" + pusher_name + "] 님이 " + target_name + " 에게 " + content_name + " 을(를)" + play_ment;

				adto.setMent(alarm_ment);
			}
		}

		AlarmData adata = AlarmData.builder().member_no(member_no).alarmList(alarmList).text("refresh").status(8)
				.build();

		log.info("알람 리스트 == {}", adata.getAlarmList());

		// 리스트 만들었으면 보내기 전에 변환 처리
		String Atext = mapper.writeValueAsString(adata);

		TextMessage msg = new TextMessage(Atext);
		log.info("알람 메시지 = {}", msg.getPayload());
		user.getWs().sendMessage(msg);

	}
	
}