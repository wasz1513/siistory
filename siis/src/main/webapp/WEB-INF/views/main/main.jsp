<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

	<h1>세션email = ${sessionScope.email}</h1>
	<h1>세션member_no = ${sessionScope.member_no}</h1>
	
	<a href="member/mypage">내정보</a>
	
	<a href="logout">로그아웃</a>

<footer>
	
		
	
</footer>


</body>
</html>
