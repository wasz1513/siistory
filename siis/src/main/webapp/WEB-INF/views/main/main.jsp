<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>세션email = ${sessionScope.email}</h1>
<h1>세션member_no = ${sessionScope.member_no}</h1>

<a href="logout">로그아웃</a>