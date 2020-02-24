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
				send(0,"room-fresh");
			};
			
			window.socket.onclose= function(){
								
			};
			
			
			window.socket.onerror = function() {

				appendMessage("서버 오류 발생");
			};
			
			
			
			window.socket.onmessage = function(e){
				var data = JSON.parse(e.data);
				
				if (data.status == 0){		
					appendMessage(data)
				}
				
				else if (data.status==1){
					appendMessage(data)
				}
				
				else if (data.status==2){
					
					console.log(data)
					appendMessage(data)
					
					
				}
	
			};

		};
		
		
		var me = {};
		var you = {};
		//메시지 출력하는 구문  (일단은 p로 진행)
 		function appendMessage(data) {
			
			if("${member_no}" == data.member_no){
			

				me.avatar = "${pageContext.request.contextPath}/util/download?member_no="+data.member_no;
				console.log (me.avatar)
				
				insertChat("me", data.text, 0);
				
				
				
			} else {
		
				you.avatar = "${pageContext.request.contextPath}/util/download?member_no="+data.member_no;
				console.log (you.avatar)
				insertChat("you", data.text, 0);
			}

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
		

		///////////////////////////////////////////////////////////////
		
		

		function formatAMPM(date) {
		    var hours = date.getHours();
		    var minutes = date.getMinutes();
		    var ampm = hours >= 12 ? 'PM' : 'AM';
		    hours = hours % 12;
		    hours = hours ? hours : 12; // the hour '0' should be '12'
		    minutes = minutes < 10 ? '0'+minutes : minutes;
		    var strTime = hours + ':' + minutes + ' ' + ampm;
		    return strTime;
		}            

		//-- No use time. It is a javaScript effect.
		function insertChat(who, text, time){
		    if (time === undefined){
		        time = 0;
		    }
		    var control = "";
		    var date = formatAMPM(new Date());
		    
		    if (who == "me"){
		        control = '<li style="width:100%">' +
		                        '<div class="msj macro">' +
		                        '<div class="avatar"><img class="img-circle" style="width:100%;" src="'+ me.avatar +'" /></div>' +
		                            '<div class="text text-l">' +
		                                '<p>'+ text +'</p>' +
		                                '<p><small>'+date+'</small></p>' +
		                            '</div>' +
		                        '</div>' +
		                    '</li>';                    
		    }else{
		        control = '<li style="width:100%;">' +
		                        '<div class="msj-rta macro">' +
		                            '<div class="text text-r">' +
		                                '<p>'+text+'</p>' +
		                                '<p><small>'+date+'</small></p>' +
		                            '</div>' +
		                        '<div class="avatar" style="padding:0px 0px 0px 10px !important"><img class="img-circle" style="width:100%;" src="'+you.avatar+'" /></div>' +                                
		                  '</li>';
		    }
		    setTimeout(
		        function(){                        
		            $("ul").append(control).scrollTop($("ul").prop('scrollHeight'));
		        }, time);
		    
		}

		function resetChat(){
		    $("ul").empty();
		}

		$(".mytext").on("keydown", function(e){
		    if (e.which == 13){
		        var text = $(this).val();
		        if (text !== ""){
		        	send(2,text);
		                         
		            $(this).val('');
		        }
		        
		    }
		    
		    
		    
		});

		$('body > div > div > div:nth-child(2) > span').click(function(){
		    $(".mytext").trigger({type: 'keydown', which: 13, keyCode: 13});
		})

		//-- Clear Chat
		resetChat();




		//-- NOTE: No use time on insertChat.
		
		//전송처리
		$(".send-btn").click(function(){
			
			var text = $(".user-input").val();
			console.log(text);
			if(!text) return;
			
			send(2,text);
			
			$(".user-input").val("");
			
			
		});	
		
		
	
		
	});

</script>

<style>
.mytext {
	border: 0;
	padding: 10px;
	background: whitesmoke;
}

.text {
	width: 75%;
	display: flex;
	flex-direction: column;
}

.text>p:first-of-type {
	width: 100%;
	margin-top: 0;
	margin-bottom: auto;
	line-height: 13px;
	font-size: 12px;
}

.text>p:last-of-type {
	width: 100%;
	text-align: right;
	color: silver;
	margin-bottom: -7px;
	margin-top: auto;
}

.text-l {
	float: left;
	padding-right: 10px;
}

.text-r {
	float: right;
	padding-left: 10px;
}

.avatar {
	display: flex;
	justify-content: center;
	align-items: center;
	width: 25%;
	float: left;
	padding-right: 10px;
}

.macro {
	margin-top: 5px;
	width: 85%;
	border-radius: 5px;
	padding: 5px;
	display: flex;
}

.msj-rta {
	float: right;
	background: whitesmoke;
}

.msj {
	float: left;
	background: white;
}

.frame {
	background: #e0e0de;
	height: 450px;
	overflow: hidden;
	padding: 0;
}

.frame>div:last-of-type {
	position: absolute;
	bottom: 0;
	width: 100%;
	display: flex;
}

body>div>div>div:nth-child(2)>span {
	background: whitesmoke;
	padding: 10px;
	font-size: 21px;
	border-radius: 50%;
}

body>div>div>div.msj-rta.macro {
	margin: auto;
	margin-left: 1%;
}

ul {
	width: 100%;
	list-style-type: none;
	padding: 18px;
	position: absolute;
	bottom: 47px;
	display: flex;
	flex-direction: column;
	top: 0;
	overflow-y: scroll;
}

.msj:before {
	width: 0;
	height: 0;
	content: "";
	top: -5px;
	left: -14px;
	position: relative;
	border-style: solid;
	border-width: 0 13px 13px 0;
	border-color: transparent #ffffff transparent transparent;
}

.msj-rta:after {
	width: 0;
	height: 0;
	content: "";
	top: -5px;
	left: 14px;
	position: relative;
	border-style: solid;
	border-width: 13px 13px 0 0;
	border-color: whitesmoke transparent transparent transparent;
}

input:focus {
	outline: none;
}

::-webkit-input-placeholder { /* Chrome/Opera/Safari */
	color: #d4d4d4;
}

::-moz-placeholder { /* Firefox 19+ */
	color: #d4d4d4;
}

:-ms-input-placeholder { /* IE 10+ */
	color: #d4d4d4;
}

:-moz-placeholder { /* Firefox 18- */
	color: #d4d4d4;
}
</style>


<script>


</script>


<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html>
<body>
	<div class="col-sm-3 col-sm-offset-4 frame">
		<ul></ul>
		<div>
			<div class="msj-rta macro">
				<div class="text text-r" style="background: whitesmoke !important">
					<input class="mytext user-input" placeholder="Type a message" />
				</div>

			</div>
			<div style="padding: 10px;">
				<span class="glyphicon glyphicon-share-alt send-btn"></span>
			</div>
		</div>
	</div>
</body>
</html>
