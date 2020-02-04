<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<link href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/darkly/bootstrap.min.css" rel="stylesheet" integrity="sha384-rCA2D+D9QXuP2TomtQwd+uP50EHjpafN+wruul0sXZzX/Da7Txn4tB9aLMZV4DZm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.js" integrity="sha256-BTlTdQO9/fascB1drekrDVkaKd9PkwBymMlHOiG+qLI=" crossorigin="anonymous"></script>

<c:forEach var="content" items="${list }">
	<div class="card mb-3">
	  <div class="card-body">
	    <p class="card-text">${content.board_content}</p>
	  </div>
		  <ul class="list-group list-group-flush">
		  	<%-- <c:forEach var="reply" items="${list }">
			    <li class="list-group-item">${reply.reply_content }</li>
		  	</c:forEach> --%>
		  </ul>
	  <div class="card-footer text-muted">
	    2 days ago
	  </div>
	</div>
</c:forEach>





