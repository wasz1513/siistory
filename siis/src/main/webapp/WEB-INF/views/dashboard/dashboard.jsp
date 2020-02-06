<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/darkly/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>

<script>
$(function(){
	if($("textarea[name=reply_content]").val() != null){
		$(this).parents("form").find(".submit").prop('disabled', false);
	}
	
	
	$("textarea[name=reply_content]").focus(function(){
		
		
		$("form").submit(function(e){
			e.preventDefault();
			
			$.ajax({
				url:"dashboard/replyinsert",
				type:"post",
				data:$(this).serialize(),
				success:function(resp){
					console.log("성공")
				}
			})
		})
		
		$(this).blur(function(){
			$(this).parents("form").find(".submit").prop('disabled', true);
		})
	})
	
	/* $("textarea[name=reply_content]").focusout(function(){
		$(this).parents("form").find(".submit").attr('disabled', true);
		$(this).val("");
	}) */
	
	$(".reply").click(function(){
		var writer = "@"+$(this).parent().find(".writer").text()+" ";
		$(this).parents(".mb-3").find("textarea[name=reply_content]").val(writer).focus();
	})
	
	
});

</script>

<style>
article{
width:50%;
margin:auto;
}
</style>

<article>
<c:forEach var="content" items="${list }">
	<div class="card mb-3">
		<div class="card-body">
			<p class="card-text">${content.board_writer} ${content.board_content}</p>
		</div>
		<ul class="list-group list-group-flush">
			<c:forEach var="reply" items="${content.replylist }">
				<li class="list-group-item">
					<span class="writer">${reply.reply_writer}</span>
					<span class="content">${reply.reply_content}</span>
					<button type="button" class="btn reply">답글달기</button>
				</li>
			</c:forEach>
		</ul>
		<div class="card-footer text-muted">2 days ago</div>
		<form>
			<fieldset>
				<div class="form-group">
      				<textarea class="form-control" rows="1" name="reply_content" placeholder="댓글달기..."></textarea>
    			</div>
    				<input type="hidden" name="member_no" value="${sessionScope.member_no }">
    				<input type="hidden" name="reply_writer" value="${sessionScope.member_name }">
    				<input type="hidden" name="board_no" value="${content.board_no }">
					<button type="submit" class="btn btn-primary submit" disabled="disabled">Submit</button>
			</fieldset>
		</form>
	</div>
</c:forEach>
</article>






