<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- template 만들 페이지 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SiiStory</title>
<link href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/darkly/bootstrap.min.css" rel="stylesheet" integrity="sha384-rCA2D+D9QXuP2TomtQwd+uP50EHjpafN+wruul0sXZzX/Da7Txn4tB9aLMZV4DZm" crossorigin="anonymous">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style>
	#siis-main-img{
		width:100px;
		height:50px;
	}
	
	header{
		position:fixed;
		top:0;
		width:100%;
	}
	
	.left-aside{
		margin-top:250px;
		margin-buttom:100px;
	}

	section{
		margin-top:250px;
		margin-buttom:100px;
	}

	.right-aside{
		min-height: 600px;
		margin-top:250px;
		margin-buttom:100px;
	}	
	
	.navbar{
		z-index: 300px;
	}
	
</style>

</head>
<body>

<header>
<!-- 상단 네비 부분 -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	  <a class="navbar-brand" href="${pageContext.request.contextPath}/main">
	  	<img src="${pageContext.request.contextPath}/resources/image/logo2.png" id="siis-main-img">
	  </a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse" id="navbarColor01">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item active">
	        <a class="nav-link" href="${pageContext.request.contextPath}/main">Home <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="${pageContext.request.contextPath}/member/mypage">Mypage</a>
	      </li>
	      <li class="nav-item">
	      	<c:choose>
	      		<c:when test="${empty sessionScope.email}">
			      	<a class="nav-link" href="${pageContext.request.contextPath}/logout">Login</a>
	      		</c:when>
	      		<c:otherwise>
			      	<a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
	      		</c:otherwise>
	      	</c:choose>
	      </li>
	    </ul>
	    <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/search/" method="get">
	      <select class="form-control" id="exampleSelect1" name="type">
		    <option value="popular">Popular</option>
		    <option value="email">Email</option>
		    <option value="member_name">Name</option>
		    <option value="tag">Tag</option>
		    <option value="location">Location</option>
	      </select>
	      <input class="form-control mr-sm-2" type="text" placeholder="Search" name="keyword">
	      <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
	    </form>
	  </div>
	</nav>
</header>