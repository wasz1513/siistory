<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- template 만들 페이지 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SiiStory</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/darkly/bootstrap.min.css" rel="stylesheet" integrity="sha384-rCA2D+D9QXuP2TomtQwd+uP50EHjpafN+wruul0sXZzX/Da7Txn4tB9aLMZV4DZm" crossorigin="anonymous">
<!-- <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.15/dist/summernote.min.css" rel="stylesheet"> -->
<!-- <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.15/dist/summernote.min.js"></script> -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="https://bootswatch.com/4/darkly/bootstrap.css">
<script>
	var context = '${pageContext.request.contextPath}';
	var member_no = '${member_no}';
</script>
<script src="${pageContext.request.contextPath}/resources/js/messenger.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/dashboard.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/editor.js"></script>

<style>
	#siis-main-img{
		width:100px;
		height:50px;
	}
	
	header{
		position:fixed;
		top:0;
		width:100%;
		z-index:9999;
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
	      <li class="nav-item">
	      	<c:if test="${sessionScope.email == 'admin' }">
	      		<a class="nav-link" href="${pageContext.request.contextPath}/admin/management">Management</a>
	      	</c:if>
	      </li>
	    </ul>
	    
 
<ul class="nav nav-pills">
  <li class="nav-item">
    <a class="nav-link active" href="#">Active</a>
  </li>
  <li class="nav-item dropdown show">
    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="true">Dropdown</a>
    <div class="dropdown-menu show" x-placement="bottom-start" style="position: absolute; transform: translate3d(0px, 38px, 0px); top: 0px; left: 0px; will-change: transform;">
      <a class="dropdown-item" href="#">Action</a>
      <a class="dropdown-item" href="#">Another action</a>
      <a class="dropdown-item" href="#">Something else here</a>
      <div class="dropdown-divider"></div>
      <a class="dropdown-item" href="#">Separated link</a>
    </div>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="#">Link</a>
  </li>
  <li class="nav-item">
    <a class="nav-link disabled" href="#">Disabled</a>
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