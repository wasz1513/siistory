<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<style>
	article{
		display: flex; /*바로안에있는것만적용됨*/
        flex-wrap: wrap;
		width:80%;
		margin:auto;
	}
	
	.left-aside{
		background-color: EBFBFF;
        width: 20%;
        margin-top:150px;
	}
	section{
		background-color: C8FFFF;
		width:60%;
		margin-top:150px;
	}
	.right-aside{
		background-color: BEEFFF;
		flex-grow: 1;
		height:750px;
		margin-top:150px;
	}
	
	.scroll-div{
		overflow-y:scroll;
		height:750px;
	}
	
	.scroll-div::-webkit-scrollbar{
		display: none;
	}
	
</style>

<article>

	<aside class="left-aside">
		<div class="row">
			<a href="member/follow">친구 요청</a>
		</div>
		<div class="row">
			<a href="#">내 게시글</a>
		</div>
		<div class="row">
			<a href="#">내정보</a>
		</div>
		<div class="row">
			<a href="#">친구관리</a>
		</div>
		<div class="row">
			<a href="#">설정</a>
		</div>
	</aside>

	<section>
		
		<div class="container scroll-div">
			<section>
		<c:forEach var="content" items="${dtolist }">
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
		</c:forEach>			
	</section>
			
		</div>
		
	</section>

	<aside class="right-aside">
		<div class="container scroll-div">
			<c:forEach var="myfriend" items="${myfriend}">
				<div class="row">
					<a href="#"><button class="btn btn-secondary btn-sm">${myfriend.member_name}</button></a>
				</div>
			</c:forEach>
		</div>
	</aside>
	
</article>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>