<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<style>
	article{
		width:50%;
        text-align:center;
        align-items: center;
        margin:auto;
        margin-top:100px;
	}
	
	.scroll-div {
	overflow-y: scroll;
	height: 750px;
	}

	.scroll-div::-webkit-scrollbar {
		display: none;
	}
</style>

<article>
<!-- 게시판 -->
	<section>
		<div class="container scroll-div">
		
			<!-- content -->
			<c:forEach var="content" items="${dtolist }">
				<div class="card mb-3">
					<div class="card-body" data-seq="${content.board_no }">
						<!-- 본문관련 -->
						<div class="media p-3">
							<img
								src="${pageContext.request.contextPath }/util/download?member_no=${content.member_no}"
								class="mr-3 mt-3 rounded-circle" style="width: 30px;">
							<div class="media-body">
								<h4>${content.board_writer }
									<small><i>Posted on February 19, 2016</i></small>
								</h4>
								<c:if test="${content.photo == 1 }">
									<a href="post/${content.board_no }"> <img
										src="${pageContext.request.contextPath }/post/image/${content.board_no }"
										width="100%">
									</a>

								</c:if>
								<p>${content.board_content }</p>
							</div>
						</div>


						<c:choose>
							<c:when test="${content.board_like != sessionScope.member_no}">
								<button class="btn good-btn good-on"
									data-member_no="${member_no}" data-status="조건"
									data-target_no="${content.member_no}"
									data-pusher_no="${member_no}"
									data-content_no="${content.board_no}" data-content_type="board"
									data-content_play="good"><a href="#">좋아요</a></button>
							</c:when>


							<c:otherwise>
								<button class="btn good-btn good-off"
									data-member_no="${member_no}" data-status="조건"
									data-target_no="${content.member_no}"
									data-pusher_no="${member_no}"
									data-content_no="${content.board_no}" data-content_type="board"
									data-content_play="good"><a href="#">좋아요 취소</a></button>
							</c:otherwise>


						</c:choose>
						<a>(좋아요  total = </a>
										<a>${content.board_like_count}</a> 
										<a>개 )</a>	

					</div>

					<!-- 여기서부터 댓글 -->
					<c:forEach var="reply" items="${content.replylist }">
						<ul class="list-group list-group-flush r"
							data-seq="${reply.reply_no }"
							data-writer="${reply.reply_writer }">
							<li class="list-group-item rr">
								<div class="media p-3">
									<img
										src="${pageContext.request.contextPath }/util/download?member_no=${reply.writer_no}"
										class="mr-3 mt-3 rounded-circle" style="width: 30px;">
									<div class="media-body" id="${reply.reply_no }">
										<h4>${reply.reply_writer }
											<small><i>Posted on ?? days ago</i></small>
										</h4>
										<p>${reply.reply_content }</p>
										
										<c:choose>
										<c:when test="${reply.reply_like != sessionScope.member_no}">
										<button class="btn good-btn good-on" data-member_no="${member_no}"
											data-status="조건" data-target_no="${content.member_no}"
											data-pusher_no="${member_no}"
											data-content_no="${reply.reply_no }"
											data-content_type="reply" data-content_play="good"><a href="#">좋아요</a></button>
										</c:when>
										
										<c:otherwise>
										<button class="btn good-btn good-off" data-member_no="${member_no}"
											data-status="조건" data-target_no="${content.member_no}"
											data-pusher_no="${member_no}"
											data-content_no="${reply.reply_no }"
											data-content_type="reply" data-content_play="good"><a href="#">좋아요 취소</a></button>
											
										</c:otherwise>
										
										</c:choose>
										<a>(좋아요  total = </a>
										<a>${reply.reply_like_count}</a> 
										<a>개 )</a>	
										
										<button class="btn replyadd">답글달기</button>	
										<button class="btn commentview">답글 보기(??개)</button>

										<!-- 답글(대댓글) 추가하면 뜨는 곳 -->
									</div>

								</div>
							</li>
						</ul>
					</c:forEach>
					<!-- 여기까지 댓글 -->

					<c:if test="${content.board_reply_count > 2 }">
						<button class="btn replymore" data-click="0">
							댓글 <span>${content.board_reply_count }</span>개 모두 보기
						</button>
					</c:if>

					<ul class="list-group list-group-flush">
						<li class="list-group-item">
							<div class="input-group mb-3">
								<input type="text" class="form-control replycontent"
									placeholder="댓글 달기..">
								<div class="input-group-append">
									<button class="btn btn-primary submit" type="button">게시</button>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</c:forEach>
		</div>
	</section>
</article>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>