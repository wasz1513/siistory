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
			
			window.socket.onopen = function(){
				appendMessage("접속했다.")
			}
			
			window.socket.onclose= function(){
				appendMessage("종료다");
			}
			
			window.socket.onmessage = function(e){
				appendMessage(e.data);
			}
			
		}
		
		
		//접속 종료
		$(window).on("beforeunload", function(){
			window.socket.close();	
		});
		
		
		
		//전송처리
		$(".send-btn").click(function(){
			
			var text = $(".user-input").val();
			if(!text) return;
			
			window.socket.send(text);
			
			$(".user-input").val("");
			
			
		});	
		
		//메시지 출력하는 구문  (일단은 p로 진행)
		function appendMessage(message) {
			$("<p>").text(message).appendTo(".chat-content")
		};
		

			

		
		
		window.socket.onerror = function() {

			appendMessage("서버 오류 발생");
		};
		
		
	});

</script>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>채팅 서버 </h1>

<h5> 나의 접속 no  = ${member_no}</h5>
<h5> 상대 접속 no  = ${member_no}</h5>

<input type="text" class="user-input">
<button class="send-btn">보내기</button>

<div class="chat-content"></div>

</body>
</html>