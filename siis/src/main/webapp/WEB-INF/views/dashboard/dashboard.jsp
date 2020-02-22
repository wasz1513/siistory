<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/darkly/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<script>
$(function(){
	// 댓글 쓰기 + 답글 쓰기 누르면 submit 전 정보 세팅 이벤트
	$(document).on("click", ".replyadd", function(){
		var writer = "@"+$(this).parents(".r").data("writer")+" ";
		$(this).parents(".mb-3").next("section").find(".reply_content").val(writer).focus();
		var replyseq = $(this).parents(".r").data("seq");
		$(this).parents(".mb-3").next("section").find(".reply_content").data("replyseq", replyseq);
	});
	
	// 댓글 입력 전송 이벤트
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
			context:this,
			success:function(data){
				if(data.depth == 0){
					$(this).parents("section").prev(".mb-3").find(".more").before(replyadd(data));
				} else {
					$(this).parents("section").prev(".mb-3").find("#"+data.super_no).after(commentadd(data));
					$(this).prev(".reply_content").data("replyseq", 0);
				}
				$(this).prev(".reply_content").val("");
			}
		});
		
	});
	
	// 댓글 더보기 버튼 (10개씩 불러옴)
	$(".replymore").click(function(){
		
		var board_no = $(this).parents(".mb-3").data("seq");
		var count = $(this).data("click");
		if(count == 0){
			$(this).parents(".b").find("ul").hide();
		}
		
		$.ajax({
			url:"dashboard/morereply",
			type:"get",
			data:{'board_no':board_no, 'start':(count*10)+1, 'end':(count*10)+10},
			context:this,
			success:function(data){
				for(var i in data){
					var a = data[i];
					$(this).parents(".mb-3").find(".more").before(replyadd(a));
					$(this).data("click", count+1);
				}
			}
		});
	});

	// 답글(comment) 더보기 버튼 이벤트
	$(document).on("click", ".commentview", function(){
		var super_no = $(this).parents(".rr").attr("id");
		var board_no = $(this).parents(".mb-3").data("seq");
		console.log(super_no);
		
		$.ajax({
			url:"dashboard/commentview?super_no="+super_no+"&board_no="+board_no,
			type:"get",
			context:this,
			success:function(data){
				for(var i in data){
					var a = data[i];
					$("#"+a.super_no).after(commentadd(a));
				}
			}
		});
	});
	
});
	
	
// 댓글 추가하면 jsp에 댓글 생성해주는 태그 함수
function replyadd(data){
	var html = "";
	html += '<ul class="list-group list-group-flush r" data-seq="'+data.reply_no+'" data-writer="'+data.reply_writer+'">';
	html += '<div class="card-body">';
	html += '<div><img src="${pageContext.request.contextPath }/util/download?member_no='+data.writer_no+'" width="20" height="20"></div>';
	html += '<div>';
	html += '<h3>'+data.reply_writer+'</h3>';
	html += '<span>'+data.reply_content+'</span>';
	html += '<div>';
	html += '<div>';
	html += '<time>1시간</time>';
	html += '<button class="btn">좋아요 ??개</button>';
	html += '<button class="btn replyadd">답글달기</button>';
	html += '</div>';
	html += '</div>';
	html += '</div>';
	html += '</div>';
	html += '<li class="list-group-item">';
	html += '<ul class="list-group list-group-flush">';
	html += '<li class="list-group-item rr" id="'+data.reply_no+'">';
	html += '<div>';
	html += '<button class="btn commentview">답글 보기(??개)</button>';
	html += '</div>';
	html += '</li>';
	html += '</ul>';
	html += '</li>';
	html += '</ul>';
	
	return $.parseHTML(html);
};

// 답글 추가하면 jsp에 답글 생성해주는 태그 함수
function commentadd(data){
	var html = "";
	html += '<div>';
	html += '<li class="list-group-item">';
	html += '<div><img src="${pageContext.request.contextPath }/util/download?member_no='+data.writer_no+'" width="20" height="20"></div>';
	html += '<div class="card-body">';
	html += '<h3>'+data.reply_writer+'</h3>';
	html += '<span>'+data.reply_content+'</span>';
	html += '<div>';
	html += '<div>';
	html += '<button class="btn replyadd">답글달기</button>';
	html += '</div>';
	html += '</div>';
	html += '</div>';
	html += '</li>';
	html += '</div>';
	
	return $.parseHTML(html);
};

</script>

<style>
article{
width:50%;
margin:auto;
}
</style>


<div>
<form action="write" method="post">
<input type="text" name="board_content">
<input type="submit">
</form>
</div>

<div>${sessionScope.member_no }</div>


<c:forEach var="content" items="${dtolist }">
<article>
<div>
	<div class="card mb-3" data-seq="${content.board_no }">
		<ul class="list-group list-group-flush b">
			<!-- 본문관련 -->
			<div class="card-body">
				<h2>${content.board_writer }</h2>
				<span>${content.board_content }</span>
				<i class="fa fa-plus" aria-hidden="true"></i>
			</div>
			
			<!-- 여기서부터 댓글 -->
			<c:forEach var="reply" items="${content.replylist }">
			<ul class="list-group list-group-flush r" data-seq="${reply.reply_no }" data-writer="${reply.reply_writer }">
				<div class="card-body">
					<div><img src="${pageContext.request.contextPath }/util/download?member_no=${reply.writer_no}" width="20" height="20"></div>
					<div>
						<h3>${reply.reply_writer }</h3>
						<span>${reply.reply_content }</span>
						<div>
							<div>
								<time>1시간</time>
								<button class="btn">좋아요 ??개</button>
								<button class="btn replyadd">답글달기</button>
							</div>
						</div>
					</div>
				</div>
				
				<!-- 여기서부터 대댓글 -->
				<li class="list-group-item">
					<ul class="list-group list-group-flush">
						<li class="list-group-item rr" id="${reply.reply_no }">
							<div>
								<button class="btn commentview">답글 보기(??개)</button>
							</div>
						</li>
						
					<!-- 여기서부터 대댓글 추가하면 뜨는 곳 -->
					
					</ul>
				</li>
			</ul>
			</c:forEach>
			<li class="list-group-item more">
				<div>
					<button class="btn replymore" data-click="0">댓글 <span>${content.board_reply_count }</span>개 모두 보기</button>
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
</article>
</c:forEach>





