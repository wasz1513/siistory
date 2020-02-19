<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<link href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/darkly/bootstrap.min.css" rel="stylesheet">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>




<!-- 초기 화면 페이지 > 전송 처리 후 result 값을 따로 줘서 보여주는 데이터만 결과 데이터로 보여준다. -->
<div>

	<h5>*허위신고일 경우, 신고자의 서비스 활동이 제한 될 수 있으니 신중하게 신고 해주세요 . </h5><br>
	
	<div>
		신고 대상 :${param.board_writer} <br>
		
	</div>
	
	
	
	<form action="warning" method="post">
		
			<input type="checkbox" name="content1" value="음란성  ">음란성 게시물<br>
			<input type="checkbox" name="content2" value="광고성  ">광고성 게시물 <br>
			<input type="checkbox" name="content3" value="언어적  ">욕설/반말/부적절한 언어 <br>
			<input type="checkbox" name="content4" value="분란유도  ">회원 분란 유도 <br>
			<input type="checkbox" name="content5" value="도배성  ">도배성 게시물 <br>
			<input type="checkbox" name="content6" value="저작권  ">저작권 <br>
			
			<input type="hidden" name="target_no" value="${param.target_no}">
			<input type="hidden" name="pusher_no" value="${param.pusher_no}">
			<input type="hidden" name="board_no" value="${param.board_no}">
								
		
		<input type="submit" value="신고 처리 ">
	</form>
	
		<c:if test="${param.result!=null}">신고 완료 </c:if>

</div>


</body>
</html>