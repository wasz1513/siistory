<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<script>
	$(function(){
		//페이지 로딩시 접속
		connect();
		
		function connect(){
			var host = location.host; 
			var context = "${pageContext.request.contextPath}";
		
			var uri = "ws://" + host +context + "/chatserver" 
			
			window.socket = new WebSocket(uri);
			
			//채팅방 접속시 방번호 같이 보내야함.(파라미터로 알아서 처리됨 . ) 
			window.socket.onopen = function(){
				send(0,"ffff");
			};
			
			window.socket.onclose= function(){
								
			};
			
			
			window.socket.onerror = function() {

				appendMessage("서버 오류 발생");
			};
			
			
			
			window.socket.onmessage = function(e){
				var data = JSON.parse(e.data);
				
				if (data.status == 0){		
					appendMessage(data.text)
				}
				
				else if (data.status==1){
					appendMessage(data.text)
				}
				
				else if (data.status==2){
					appendMessage(data.text)
				}
	
			};
		};
			
		
		
		//메시지 출력하는 구문  (일단은 p로 진행)
		function appendMessage(message) {
			$("<p>").text(message).appendTo(".chat-content")
		};
		
		//서버로 메시지 전송하는 함수
		function send (status,text){
			var member_no = ${member_no};
			var room_no = ${param.room_no};
			var message = {
					member_no : member_no,
					room_no : room_no,
					status :status,
					text : text
			};
			var value = JSON.stringify(message)
			window.socket.send(value)
			
		};
		
		
		
		//접속 종료
		$(window).on("beforeunload", function(){
			send(1);
			window.socket.close();	
		});
		
		
		
		//전송처리
		$(".send-btn").click(function(){
			
			var text = $(".user-input").val();
			if(!text) return;
			
			send(2,text);
			
			$(".user-input").val("");
			
			
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
	<h1>채팅 서버</h1>

	<h5>나의 접속 no = ${member_no}</h5>
	<h5>상대 접속 no = ${member_no}</h5>

	<input type="text" class="user-input">
	<button class="send-btn">보내기</button>

	<div class="chat-content"></div>

</body>
</html>