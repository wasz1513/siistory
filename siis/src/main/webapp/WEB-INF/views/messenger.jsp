<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(function() {

		//페이지 로딩시 바로 웹소켓 서버 접속
		connect();

		//웹소켓 연결 함수	
		function connect() {
			var host = location.host;
			var context = "${pageContext.request.contextPath}";
			var uri = "ws://" + host + context + "/messengerserver";

			//연결 예약 코드
			window.socket = new WebSocket(uri);

			//시작시 예약 코드 
			window.socket.onopen = function() {

			}

			//종료 예약 코드
			window.socket.onclose = function() {

			}

			//메시지 도착경우
			window.socket.onmessage = function(e) {

				var Friend = JSON.parse(e.data)

				if (Friend.status == 0) {

					send(0)
				}

				else if (Friend.status == 1) {
					send(1)
				}

				else if (Friend.status == 3) {

					/* 					$(".showList").empty();
					 for ( var index in Friend.flist_data) {

					 var tr = $("<tr>");
					 var Fno = $("<td>").text(
					 "no = " + Friend.member_no);
					 var Fname = $("<td>").text(
					 "Fno = " + Friend.flist_data[index].friend);
					 var Fstate = $("<td>").text(
					 "state = " + Friend.flist_data[index].connect_state);

					 $(".showList").append(tr).append(Fno).append(Fname)
					 .append(Fstate);

					 }
					 ; */
					$(".testList").empty();
					var room_no = 0;
					var ul = $("<ul>").addClass("set-list");
					$(".testList").append(ul);
				
					for ( var index in Friend.flist_data) {
						var http = "http://" + host + context
								+ "/messenger/chat?room_no=" + room_no
								+ "&friend_no="
								+ Friend.flist_data[index].friend;

						var text = "  no = " + Friend.member_no + "  fno = "
								+ Friend.flist_data[index].friend
								+ "  state = "
								+ Friend.flist_data[index].connect_state
								
						var room = $("<a>").addClass("room_no-data").hide().text(room_no);
						
						var friend = $("<a>").addClass("friend_no-data").hide().text(Friend.flist_data[index].friend);
							
							
							
							
								
						var li = $("<li>");
								
						var tag = $(
								"<a href="+http+">")
								.addClass("invite-chat").text(text);

						li.append(tag).append(room).append(friend).appendTo(".set-list");

						room_no++;
					}
					;

				}  //3 일 때 
				// 새 창을 띄운다 > 해당 창에 로케이션을 정해진 주소로 변경시킨다 . 
				else if (Friend.status == 20 ) {
				    window.open("http://" + host + context+ 
				    "/messenger/chat?room_no="+Friend.room_no,
				    "1:1 채팅", "width=300, height=450, toolbar=no, menubar=no,scrollbars=yes, resizable=no, location=no, left=1400px,top=1300px");  
				}  
		
				
				
				console.log(e.data);
			};

		}//connect 
		;

		//메시지 받고 출력하는 코드

		//무엇을 할 것인가? 받아온 jacson message 객체를 분할하여
		// 번호 , 이름 , 접속 상태   ul li 리스트로 출력한다
		// 해야할 것은?
		//[1] 리스트 생성 구문 구현 (테이블 형식으로 만든다.) 

		/*
		$.each(Friend, function (index, item){
		
		 var tr  = $("<tr>");
		 var Fno = $("<td>").text(item.friend);
		 var Fname = $("<td>").text("name");
		 var Fstate = $("<td>").text("state");
		
		 $(".showList").append(tr).append(Fno).append(Fname).append(Fstate);
		 }); */

		//[2] 각 칸에 text 입력되게끔 구현 (jsp 에서는 jackson 객체에서 뽑아서 넣는다.)
		//[3] text를 jacson 객체의 원하는 내용 끼워 넣기
		function send(text, status, target_no, room_no) {
			var member_no = $
			{
				member_no
			}
			;
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

		//연결 종료 코드 (윈도우가 닫히기 전에~)
		$(window).on("beforeunload", function() {
			send("refresh", 1);

			window.socket.close(); //종료

		});

		//채팅을 상대방이 접속하게 하려면 필요한 것 .

		//접속 주소 , 방번호 , 상대방 넘버 
		//1:1 채팅을 시도하면 상대방에게 주소를 보낸다.
		$(document).on("click",".invite-chat",function(event){
			console.log("채팅 초대")
			event.preventDefault();
			var location  = $(this).attr("href");
			var room_no = $(this).next().text();
			var target_no = $(this).next().next().text();
			
			console.log (target_no);
			
			console.log (room_no);
			
			send(location, 20, target_no , room_no);
			
		})




	});
	

</script>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>나의 접속 no = ${member_no}</h5>
	<h5>상대 접속 no = ${member_no}</h5>
	<table>
		<thead>
			<tr>
				<th>1test</th>
				<th>2test</th>
			</tr>

		</thead>
		<tbody class=showList>
			<tr class=item-connect>
				<form action="#" method="post">
					<td colspan="2">검색 : <input type="text"> <input
						type="submit" value="조회 "></td>
				</form>
			</tr>
		</tbody>
		<tfoot>

		</tfoot>

	</table>


	<div class="testList"></div>
	
	
	<a href="#" role="button" class="invite-chat">  no = 24  fno = 8  state = 테스트</a>


</body>
</html>