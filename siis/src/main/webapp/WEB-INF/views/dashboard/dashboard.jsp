<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/darkly/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>

<script>
$(function(){
	
	$("textarea[name=reply_content]").focus(function(){
		var check = /^[@*]/;
		var text = $(this).val();
		if(!check.test(text)){
			$(this).parents(".mb-3").find("form").submit(function(e){
				e.preventDefault();
					$.ajax({
					url:"dashboard/replyinsert",
					type:"post",
					data:$(this).serialize(),
					success:function(resp){
						console.log(resp)
						$("textarea[name=reply_content]").val("");
					}
				})
			})
		} else {
			$(this).parents(".mb-3").find("form").submit(function(e){
				e.preventDefault();
					$.ajax({
					url:"dashboard/replyinsert",
					type:"post",
					data:$(this).serialize(),
					success:function(resp){
						console.log("@성공")
						$("textarea[name=reply_content]").val("");
					}
				})
			})
		}
	})
	
	$(".reply").click(function(){
		var writer = "@"+$(this).parent().find(".writer").text()+" ";
		$(this).parents(".mb-3").find("textarea[name=reply_content]").val(writer).focus();
		
		var reply_no = $(this).prev().attr('value');
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
			<p class="card-text">
				<span data-no="${content.member_no }">${content.board_writer}</span>
				<span data-no="${content.board_no }"> ${content.board_content}</span>
			</p>
		</div>
		<ul class="list-group list-group-flush">
			<c:forEach var="reply" items="${content.replylist }">
				<li class="list-group-item" data-no="${reply.writer_no }">
					<span class="writer">${reply.reply_writer}</span>
					<span class="content" data-no="${reply.reply_no }">${reply.reply_content}</span>
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
    			<div class="hidden">
    				<input type="hidden" name="member_no" value="${sessionScope.member_no }">
    				<input type="hidden" name="reply_writer" value="${sessionScope.member_name }">
    				<input type="hidden" name="board_no" value="${content.board_no }">
    			</div>
					<button type="submit" class="btn btn-primary submit">Submit</button>
			</fieldset>
		</form>
	</div>
</c:forEach>
</article>






