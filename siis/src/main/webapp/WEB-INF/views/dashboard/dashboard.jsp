<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/darkly/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.slim.js"></script>

<script>





</script>

<c:forEach var="content" items="${list }">
	<div class="card mb-3">
		<div class="card-body">
			<p class="card-text">@${content.board_writer} ${content.board_content}</p>
		</div>
		<ul class="list-group list-group-flush">
			<c:forEach var="reply" items="${content.replylist }">
				<li class="list-group-item">@${reply.reply_writer} ${reply.reply_content}</li>
			</c:forEach>
		</ul>
		<div class="card-footer text-muted">2 days ago</div>
		<form method="post">
			<fieldset>
				<div class="form-group">
     				<label for="exampleTextarea">Example textarea</label>
      				<textarea class="form-control" rows="1"></textarea>
    			</div>
    				<input type="hidden" name="id" value="${sessionScope.member_name }">
    				<input type="hidden" name="no" value="${content.board_no }">
					<button type="submit" class="btn btn-primary">Submit</button>
			</fieldset>
		</form>
	</div>
</c:forEach>





