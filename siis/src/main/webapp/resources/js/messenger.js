$(function() {

	// 페이지 로딩시 바로 웹소켓 서버 접속
	connect(context);

	// 웹소켓 연결 함수
	function connect(context) {
		var host = location.host;
		var context = context;
		var uri = "ws://" + host + context + "/messengerserver";

		// 연결 예약 코드
		window.socket = new WebSocket(uri);

		// 시작시 예약 코드
		window.socket.onopen = function() {

		}

		// 종료 예약 코드
		window.socket.onclose = function() {

		}

		// 메시지 도착경우
		window.socket.onmessage = function(e) {

			var msg = JSON.parse(e.data)

			if (msg.status == 0) {

				send(member_no, 0)
			}

			else if (msg.status == 1) {
				send(member_no, 1)
			}

			else if (msg.status == 3) {

				/*
				 * $(".showList").empty(); for ( var index in Friend.flist_data) {
				 * 
				 * var tr = $("<tr>"); var Fno = $("<td>").text( "no = " +
				 * Friend.member_no); var Fname = $("<td>").text( "Fno = " +
				 * Friend.flist_data[index].friend); var Fstate = $("<td>").text(
				 * "state = " + Friend.flist_data[index].connect_state);
				 * 
				 * $(".showList").append(tr).append(Fno).append(Fname)
				 * .append(Fstate); } ;
				 */
				$(".testList").empty();
				var room_no = 0;
				var ul = $("<ul>").addClass("set-list");
				$(".testList").append(ul);

				for ( var index in msg.flist_data) {
					var http = "http://" + host + context
							+ "/messenger/chat?room_no=" + room_no
							+ "&friend_no=" + msg.flist_data[index].member_no;

					var text = "  no = " + msg.member_no + "  fno = "
							+ msg.flist_data[index].member_no + "  state = "
							+ msg.flist_data[index].connect_state + "  이름 = "
							+ msg.flist_data[index].member_name

					var room = $("<a>").addClass("room_no-data").hide().text(
							room_no);

					var friend = $("<a>").addClass("friend_no-data").hide()
							.text(msg.flist_data[index].member_no);

					var li = $("<li>");

					var tag = $("<a href=" + http + ">")
							.addClass("invite-chat").text(text);

					li.append(tag).append(room).append(friend).appendTo(
							".set-list");

					room_no++;
				}
				;

			} // 3 일 때
			// 새 창을 띄운다 > 해당 창에 로케이션을 정해진 주소로 변경시킨다 .
			else if (msg.status == 20) {
				window
						.open(
								"http://" + host + context
										+ "/messenger/chat?room_no="
										+ msg.room_no,
								"1:1 채팅",
								"width=300, height=450, toolbar=no, menubar=no,scrollbars=yes, resizable=no, location=no, left=1400px,top=1300px");
			}

			// ////////////////////////////////////////////////////////////////
			// 메시지 분석 >> 메시지 상태값에 따른 처리
			// 알람에서 사용할 메시지 상태값 설정
			// checkon = 4;
			if (msg.status == 4) {

			}

			// checkoff = 5;
			else if (msg.status == 5) {
			}
			// confirm = 6;
			else if (msg.status == 6) {
			} else if (msg.status == 7) {
			}
			// Arefresh = 8;
			else if (msg.status == 8) {
				$(".alarmList").empty();

				// 누구누구 님이 게시글을 등록했습니다.
				// 누구누구 님이 나의 어떤 게시글을 좋아합니다.
				// 누구누구 님이 나의 글에 댓글을 등록했습니다.
				// 누구누구 님이 나의 댓글을 좋아합니다.
				for ( var index in msg.alarmList) {

					var tag = $("<a href='#'>");
					var btn = $("<button>").attr("type", "button").attr(
							"class",
							"btn btn-primary btn-lg btn-block go-content");

					var alarm_message = btn.text(msg.alarmList[index].ment);
					var member_no1 = $("<a>").addClass("member_no-data").hide()
							.text(msg.alarmList[index].member_no);
					var target_no = $("<a>").addClass("target_no-data").hide()
							.text(msg.alarmList[index].target_no);
					var pusher_no = $("<a>").addClass("pusher_no-data").hide()
							.text(msg.alarmList[index].pusher_no);
					var content_no = $("<a>").addClass("content_no-data")
							.hide().text(msg.alarmList[index].content_no);
					var content_type = $("<a>").addClass("content_tpye-data")
							.hide().text(msg.alarmList[index].content_type);
					var content_play = $("<a>").addClass("content_play-data")
							.hide().text(msg.alarmList[index].content_play);

					$(".alarmList").append($("<tr>").append($("<td>").append(alarm_message).append(member_no1)
							.append(target_no).append(pusher_no).append(content_no).append(content_type)
							.append(content_play)));
					
					

					console.log(msg[index]);

				}

			}
			// setting = 9;
			else if (msg.status == 9) {
				send_alarm(member_no, 8);

			}//

			console.log(e.data);
		};
		
		
			

	}// connect
	;

	// 메시지 받고 출력하는 코드

	// 무엇을 할 것인가? 받아온 jacson message 객체를 분할하여
	// 번호 , 이름 , 접속 상태 ul li 리스트로 출력한다
	// 해야할 것은?
	// [1] 리스트 생성 구문 구현 (테이블 형식으로 만든다.)

	/*
	 * $.each(Friend, function (index, item){
	 * 
	 * var tr = $("<tr>"); var Fno = $("<td>").text(item.friend); var Fname =
	 * $("<td>").text("name"); var Fstate = $("<td>").text("state");
	 * 
	 * $(".showList").append(tr).append(Fno).append(Fname).append(Fstate); });
	 */

	// [2] 각 칸에 text 입력되게끔 구현 (jsp 에서는 jackson 객체에서 뽑아서 넣는다.)
	// [3] text를 jacson 객체의 원하는 내용 끼워 넣기
	function send(member_no, text, status, target_no, room_no) {

		var message = {
			member_no : member_no,
			text : text,
			status : status,
			target_no : target_no,
			room_no : room_no
		};
		var value = JSON.stringify(message);
		window.socket.send(value)

	}
	
	
	$(document).off().on("click", ".go-content", function(event) {
		console.log("컨텐츠 이동")
		
					
		var target_no = $(this).next().next().text();
		console.log (target_no)
		var pusher_no = $(this).next().next().next().text();
		console.log (pusher_no)
		var content_no = $(this).next().next().next().next().text();
		console.log (content_no)
		var content_type = $(this).next().next().next().next().next().text();
		console.log (content_type)
		var content_play = $(this).next().next().next().next().next().next().text();
		console.log (content_play)
		
		send_alarm(member_no,7,target_no,pusher_no,content_no,content_type,content_play);
		
		  window.location.href = "http://" + host + context
						+ "/member/follow";

	});
	
	
	
	

	// 보내는 메시지 메소드 및 형식 구성
	// 데이터 보낼 때 필요한 정보 =
	// 누른사람, 타겟사람 , 컨텐츠 넘버 , 컨텐츠 형식, 행동표시, 메시지 형태
	function send_alarm(member_no, status, target_no, pusher_no, content_no, content_type,
			content_play, text) {

		var message = {
			member_no : member_no,
			status : status,
			target_no : target_no,
			pusher_no : pusher_no,
			content_no : content_no,
			content_type : content_type,
			content_play : content_play,
			text : text
		};
		var value = JSON.stringify(message);
		window.socket.send(value);
	}
	;

	// 연결 종료 코드 (윈도우가 닫히기 전에~)
	$(window).on("beforeunload", function() {
		send(member_no, "refresh", 1);

		window.socket.close(); // 종료

	});

	// 채팅을 상대방이 접속하게 하려면 필요한 것 .

	// 접속 주소 , 방번호 , 상대방 넘버
	// 1:1 채팅을 시도하면 상대방에게 주소를 보낸다.
	$(document).on("click", ".invite-chat", function(event) {
		console.log("채팅 초대")
		event.preventDefault();
		var location = $(this).attr("href");
		var room_no = $(this).next().text();
		var target_no = $(this).next().next().text();

		console.log(target_no);

		console.log(room_no);

		send(member_no, location, 20, target_no, room_no);

	})



	/*
	 * $(".good-btn").off().click(function(){
	 * //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! //!!추후 보드 페이지에서 좋아요 클릭 연동을
	 * 해야한다 !!!! //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * send(4,23,1,"board","good");
	 * 
	 * });
	 */
	// 좋아요 상태 값에 따라서 최초 출력 상태 표시(class)
	// 좋아요 키는버튼
	$(".good-onbtn").off().click(function() {
		send_alarm(member_no, 4, 24, member_no ,86, "board", "good")
	});

	// 좋아요 취소 버튼
	$(".good-offbtn").off().click(function() {
		send_alarm(member_no, 5, 24 , member_no, 86, "board", "good")
	});

	// 친구 요청 버튼
	// $(".friend-add").off().click(function(){
	// send_alarm(member_no,10,24,0,"friend","add")
	// });

	$(".follow-btn").off().click(function() {

		var target_no = $(this).prev().val();
		var member_no = $(this).prev().prev().val();

		send_alarm(member_no, 10, target_no , member_no, 0, "friend", "add")
	});

});