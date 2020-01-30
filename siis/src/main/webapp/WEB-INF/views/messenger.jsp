<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>

$(function(){
	
	//페이지 로딩시 바로 웹소켓 서버 접속
	connect();
	
	//웹소켓 연결 함수	
	function connect(){
		var host = location.host;
		var context = "${pageContext.request.contextPath}"
		var uri = "ws://" + host + context + "/listserver"
		
		//연결 코드
		window.socket = new WebSocket(uri);
		
	}
	
});
</script>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table>
		<thead>
			<tr>
				<th>1test</th>
				<th>2test</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1</td>
				<td>2</td>
			</tr>
		</tbody>
		<tfoot>

		</tfoot>

	</table>


</body>
</html>