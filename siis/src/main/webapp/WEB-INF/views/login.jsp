<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>세션email = ${sessionScope.email }</h1>

<form action="login" method="post">
	<input type="text" name="email" placeholder="이메일주소" required>
	<input type="password" name="member_pw" placeholder="비밀번호" required>
	<button type="submit">로그인</button>
</form>
