<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				send("refresh",0);
			}

			//종료 예약 코드
			window.socket.onclose = function() {
				
			}
			
			//메시지 도착경우
			window.socket.onmessage = function(e) {
				
				
				var Friend = JSON.parse(e.data)
				
				if(Friend.status==0 || Friend.status==1 || Friend.status==3 ){
					send("refresh",2);
					
					if( Friend.status==2){					
					$(".showList").empty();
					var tr = $("<tr>");
					var Fno = $("<td>").text("no = " + Friend.member_no);
					var Fname = $("<td>").text("Fno = " + Friend.text);
					var Fstate = $("<td>").text(
							"state = " + Friend.status);

					$(".showList").append(tr).append(Fno).append(Fname).append(
							Fstate);
					}
				}
				else if (Friend[0] != null){
					
					
					$(".showList").empty();
					for ( var index in Friend) {
						
						var tr = $("<tr>");
						var Fno = $("<td>").text("no = " + Friend[index].member_no);
						var Fname = $("<td>").text("Fno = " + Friend[index].friend);
						var Fstate = $("<td>").text(
								"state = " + Friend[index].connect_state);

						$(".showList").append(tr).append(Fno).append(Fname).append(
								Fstate);
						
						
					}
					;
	
				}
				;
				console.log(e.data);
				
			};
		}
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
	

		
		function send(text,status){
			var member_no = ${member_no};
			var message = {
					member_no : member_no,
					text : text,
					status : status
			};
			var value = JSON.stringify(message);
			window.socket.send(value)
			
		}
		

		//연결 종료 코드 (윈도우가 닫히기 전에~)
		$(window).on("beforeunload", function() {
			send("refresh",1);
			
			
			window.socket.close(); //종료

		});

/* 		//리스트  출력하는 구문  (일단은 p로 진행)
		function appendMessage(message) {

			$("<p>").attr("id","target").text(message).appendTo(".chat-content")
		}
		; */

		//item-connect 이벤트 설정
		$(".refresh-btn").click(function() {

			window.socket.send("restart")

		});

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
	<button class="refresh-btn">갱신</button>

</body>
</html>