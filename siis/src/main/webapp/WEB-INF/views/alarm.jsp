<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/darkly/bootstrap.min.css" rel="stylesheet" integrity="sha384-rCA2D+D9QXuP2TomtQwd+uP50EHjpafN+wruul0sXZzX/Da7Txn4tB9aLMZV4DZm" crossorigin="anonymous">
<script>
	$(function() {

		//로딩되면 실행 
		connect();

		//연결 설정
		function connect() {
			var host = location.host; // 8080까지 주소
			var context = "${pageContext.request.contextPath}"; //프로젝트명
			var uri = "ws://" + host + context + "/messengerserver";

			//연결 코드
			var ws = window.socket;
			ws = new WebSocket(uri);

			ws.onopen = function() {
			}

			ws.onclose = function() {
			}

			ws.onmessage = function(e) {
				//서버에서 들어온 메시지 변환 처리
				var msg = JSON.parse(e.data)
				//메시지 분석 >> 메시지 상태값에 따른 처리
				//알람에서 사용할 메시지 상태값 설정
				//checkon = 4;
				if (msg.status == 4) {

				}

				//checkoff = 5;
				else if (msg.status == 5) {
				}
				//confirm = 6;
				else if (msg.status == 6) {
				} else if (msg.status == 7) {
				}
				//status 없이 왔을 때
				else if (msg[0].status == 0) {
					$(".alarmList").empty();

					//누구누구 님이 게시글을 등록했습니다.
					//누구누구 님이 나의 어떤 게시글을 좋아합니다.
					//누구누구 님이 나의 글에 댓글을 등록했습니다.
					//누구누구 님이 나의 댓글을 좋아합니다.
					for ( var index in msg) {
						var tr = $("<tr>");
						var tag = $("<a href='#'>");
						var btn = $("<button>").attr("type","button")
						.attr("class","btn btn-primary btn-lg btn-block go-content");

						var alarm_message = $("<td>").append(btn.text(msg[index].text))
						;
						
						$(".alarmList").append(tr).append(alarm_message);
						console.log(msg[index]);
						
					}
				}//

				// 보내는 메시지  메소드 및 형식 구성
				// 데이터 보낼 때 필요한 정보 = 
				// 누른사람, 타겟사람 , 컨텐츠 넘버 , 컨텐츠 형식, 행동표시, 메시지 형태
				function send(status, target_no, content_no, content_type,
						content_play) {
					var member_no = $
					{
						member_no
					}
					;

					var message = {
						status : status,
						target_no : target_no,
						content_no : content_no,
						content_type : content_type,
						content_play : content_play
					};
					var value = JSON.stringify(message);
					ws.send(value);
				}
				;

				/* 			$(".good-btn").off().click(function(){
				 //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				 //!!추후 보드 페이지에서 좋아요 클릭 연동을 해야한다 !!!!
				 //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				 send(4,23,1,"board","good");
				
				 }); */
				//좋아요 키는버튼
				$(".good-onbtn").off().click(function() {
					//DB 확인을 먼저 하고 DB값이 없으면 좋아요 있으면 좋아요 취소
					if ()
					send()
					
				});
				 
				 
				//좋아요 취소 버튼
				$(".good-offbtn").off().click(function(){
					
					
				});

			};// on message 
		}
		; //connect

		//연결 종료 코드 (윈도우가 닫히기 전에~)
		$(window).on("beforeunload", function() {
			send("refresh", 1);

			window.socket.close(); //종료

		});
	});
	//평션
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
		<tbody class=alarmList>
			<tr class=item-list>


			</tr>
		</tbody>
		<tfoot>

		</tfoot>

	</table>
	<button class="good-btn" id="change-btn">좋아요</button>



</body>
</html>