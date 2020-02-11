<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/darkly/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>

<script>
$(function(){
	
	$(".submit").click(function(e){
		e.preventDefault();
		var content = $(this).prev(".reply_content").val();
		var boardseq = $(this).parents("section").prev(".mb-3").data("seq");
		var replyseq = $(this).prev(".reply_content").data("replyseq");
		
		
		$.ajax({
			url:"dashboard/replyinsert",
			type:"post",
			data:{'board_no':boardseq, 'super_no':replyseq, 'reply_content':content},
			dataType:"JSON",
			success:function(data){
				console.log(data.reply_no)
			}
		})
		$(this).prev(".reply_content").val("");
		
	})
	
	$(".reply").click(function(){
		var writer = "@"+$(this).parents(".r").data("writer")+" ";
		$(this).parents(".mb-3").find(".reply_content").val(writer).focus();
		var replyseq = $(this).parents(".r").data("seq");
		$(this).parents(".mb-3").find(".reply_content").data("replyseq", replyseq);
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
<div>
	<div class="card mb-3" data-seq="${content.board_no }">
		<ul class="list-group list-group-flush">
			<!-- 본문관련 -->
			<div class="card-body">
				<h2>${content.board_writer }</h2>
				<span>${content.board_content }</span>
			</div>
			
			<!-- 여기서부터 댓글 -->
			<c:forEach var="reply" items="${content.replylist }">
			<ul class="list-group list-group-flush r" data-seq="${reply.reply_no }" data-writer="${reply.reply_writer }">
				<div class="card-body">
					<div>댓글 프로필 사진</div>
					<div>
						<h3>${reply.reply_writer }</h3>
						<span>${reply.reply_content }</span>
						<div>
							<div>
								<time>1시간</time>
								<button>좋아요 ??개</button>
								<button class="reply">답글달기</button>
							</div>
						</div>
					</div>
				</div>
				
				<!-- 여기서부터 대댓글 -->
				<li class="list-group-item">
					<ul class="list-group list-group-flush">
						<li class="list-group-item">답글보기 버튼위치</li>
						
					<!-- 	
						여기서부터 대댓글 추가하면 뜨는 곳
						<div>
							<li class="list-group-item">
								<div>대댓글 프로필사진</div>
								<div class="card-body">
									<h3>대댓글 제목</h3>
									<span>대댓글 내용</span>
								</div>
							</li>
						</div>
						 -->
					</ul>
				</li>
			</ul>
			</c:forEach>
			<li class="list-group-item">
				<div>
					<button class="btn btn-primary submit">load more comments</button>
				</div>
			</li>
		</ul>
	</div>
	<section>
		<div>
			<form>
				<div class="form-group">
      				<textarea class="form-control reply_content" rows="1" placeholder="댓글달기..."></textarea>
					<button type="submit" class="btn btn-primary submit">Submit</button>
    			</div>
			</form>
		</div>
	</section>
</div>
</c:forEach>
</article>






