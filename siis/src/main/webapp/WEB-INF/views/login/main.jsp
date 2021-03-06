<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<script>
	$(function() {

		$(".dropdown-list").hide();

		$(".dropdown").click(function() {
			$(this).next().slideDown();
			$(this).removeClass("dropdown");
			$(this).addClass("updown");

		});

		$(".updown").click(function() {
			$(this).next().slideUp();
			$(this).removeClass("updown");
			$(this).addClass("dropdown");
		});		

	});
</script>

<style>
article {
	display: flex; /*바로안에있는것만적용됨*/
	flex-wrap: wrap;
	width: 80%;
	margin: auto;
}

.left-aside {
	/* 		background-color: EBFBFF; */
	width: 20%;
	margin-top: 150px;
}

section {
	/* 		background-color: C8FFFF; */
	width: 60%;
	margin-top: 150px;
}

.right-aside {
	/* 		background-color: BEEFFF; */
	flex-grow: 1;
	height: 750px;
	margin-top: 150px;
}

.scroll-div {
	overflow-y: scroll;
	height: 750px;
}

.scroll-div::-webkit-scrollbar {
	display: none;
}

.detail-list.detail-list {
	background-color: black;
}

.detail-list.detail-list:hover {
	background-color: gray;
}

.new-friend{
	color:orange;
}

.r * {
	font-size:0.8rem;
}

</style>

<article>

	<aside class="left-aside">
		<div class="list-group">
			<button class="list-group-item list-group-item-action dropdown">게시글</button>
			<div class="dropdown-list">
				<a href="${pageContext.request.contextPath}/member/myboard"
					class="list-group-item list-group-item-action detail-list">내
					게시글</a>
			</div>
			<button class="list-group-item list-group-item-action dropdown">
				친구
				<c:if test="${followingcount>=1}">
						[<span class="new-friend">new</span>]
				</c:if>
			</button>
			<div class="dropdown-list">
				<a href="${pageContext.request.contextPath}/member/friend"
					class="list-group-item list-group-item-action detail-list">친구목록</a>
				<a href="${pageContext.request.contextPath}/member/follow"
					class="list-group-item list-group-item-action detail-list">
					친구요청
					<c:if test="${followingcount>=1}">
						[<span class="new-friend">${followingcount}</span>]
					</c:if>
				</a>
			</div>
			<button class="list-group-item list-group-item-action dropdown">설정</button>
			<div class="dropdown-list">
				<a href="${pageContext.request.contextPath}/member/modify"
					class="list-group-item list-group-item-action detail-list">정보
					변경</a> <a href="${pageContext.request.contextPath}/member/withdraw"
					class="list-group-item list-group-item-action detail-list">회원탈퇴</a>
			</div>
		</div>

	</aside>

	<!-- 게시판 -->
	<section>
		<div class="container scroll-div">

			<!-- editor -->
			<div class="card mb-3 editor">
				<div class="media border p-3">
					<img src="${pageContext.request.contextPath }/util/download?member_no=${sessionScope.member_no}" class="mr-3 mt-3 rounded-circle" style="width: 50px;">
					<div class="media-body">
					<div class="d-flex">
						<div class="p-2">
							<h4>${sessionScope.member_name }</h4>
						</div>
						<div class="p-2 ml-auto">
							<select class="custom-select open">
							    <option value="0" >전체공개</option>
								<option value="1" >친구공개</option>
								<option value="2" >비공개</option>
						    </select>
						</div>
					</div>
						<textarea id="summernote" name="editordata" required="required"></textarea>
						<button type="button" onclick="fileUploadAction();" class="btn btn-secondary my_button">사진추가</button>
						<button type="button" class="btn btn-primary upload" disabled="disabled">게시</button>
						<form class="imgsupload" method="post" enctype="multipart/form-data">
							<div>
								<div class="input_wrap">
									<input type="file" id="input_imgs" name="sel_files" multiple />
								</div>
							</div>
							<div>
								<div class="imgs_wrap"><img id="img" /></div>
							</div>
						</form>
					</div>
				</div>
			</div>

			<br> <br> <br>

			<!-- content -->
			<c:forEach var="content" items="${dtolist }">
				<div class="card mb-3 ${content.member_no }">
					<div class="card-body" data-seq="${content.board_no }">
						<!-- 본문관련 -->
						<div class="media p-3">
							<img
								src="${pageContext.request.contextPath }/util/download?member_no=${content.member_no}"
								class="mr-3 mt-3 rounded-circle" style="width: 30px;">
							<div class="media-body">
								<div class="d-flex">
								<div class="p-2">
								<h4><a class='writer' href='/siistory/member/${content.board_writer }'>${content.board_writer }</a>
									<small><i></i></small>
								</h4>
								</div>
								<div class="p-2 ml-auto">
								<div class="dropdown">
									<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									    <i class="fas fa-ellipsis-h"></i>
									</button>
									<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
									<button class="btn warningadd dropdown-item" data-target_no="${content.member_no}"
											data-pusher_no="${member_no}" data-board_no="${content.board_no}" data-board_writer="${content.board_writer }">게시물 신고</button>
									<button class="btn dropdown-item following">친구 취소</button>
									</div>
								</div>
								</div>
								</div>
								<c:if test="${content.photo == 1 }">
									<a href="post/${content.board_no }"> <img
										src="${pageContext.request.contextPath }/post/image/${content.board_no }"
										width="500px">
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
						<a>${content.board_like_count} 개</a> 
					</div>

					<!-- 여기서부터 댓글 -->
					<c:forEach var="reply" items="${content.replylist }">
						<c:if test="${not empty reply.reply_writer}">
						<ul class="list-group list-group-flush r"
							data-seq="${reply.reply_no }"
							data-writer="${reply.reply_writer }">
							<li class="list-group-item rr">
								<div class="media p-3">
									<img
										src="${pageContext.request.contextPath }/util/download?member_no=${reply.writer_no}"
										class="mr-3 mt-3 rounded-circle" style="width: 30px;">
									<div class="media-body" id="${reply.reply_no }">
										<h4><a class='writer' href='/siistory/member/${reply.reply_writer }'>${reply.reply_writer }</a>
											<small><i></i></small>
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
										<a>${reply.reply_like_count} 개</a> 
										
										<button class="btn replyadd">답글달기</button>	
										<button class="btn commentview">답글 보기</button>

										<!-- 답글(대댓글) 추가하면 뜨는 곳 -->
									</div>

								</div>
							</li>
						</ul>
						</c:if>
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
									<button class="btn btn-primary submit" type="button" disabled="disabled">게시</button>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</c:forEach>
		</div>
	</section>

	<aside class="right-aside">
		<div class="card border-primary mb-3" style="max-width: 20rem;">
			<div class="card-header" style="text-align: center">접속 리스트</div>

			<div class="list-group friend-list"></div>

		</div>
	</aside>

</article>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>