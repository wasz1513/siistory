<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<style>
article {
	display: flex; /*바로안에있는것만적용됨*/
	flex-wrap: wrap;
	width: 80%;
	margin: auto;
}

.left-aside {
	background-color: EBFBFF;
	width: 20%;
	margin-top: 150px;
}

section {
	background-color: C8FFFF;
	width: 60%;
	margin-top: 150px;
}

.right-aside {
	background-color: BEEFFF;
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
</style>

<article>
	<section>
		<div class="container scroll-div">

			<!-- content -->
			<c:forEach var="content" items="${dtolist }">
				<div class="card mb-3">
					<div class="card-body" data-seq="${content.board_no }">
						<!-- 본문관련 -->
						<div class="media p-3">
							<img
								src="${pageContext.request.contextPath }/util/download?member_no=${sessionScope.member_no}"
								class="mr-3 mt-3 rounded-circle" style="width: 30px;">
							<div class="media-body">
								<h4>${content.board_writer }
									<small><i>Posted on February 19, 2016</i></small> 
									<select class="private_option" data-board_no="${content.board_no}">
										<option value="0" <c:if test="${content.board_state==0}">selected</c:if>>전체공개</option>
										<option value="1" <c:if test="${content.board_state==1}">selected</c:if>>친구공개</option>
										<option value="2" <c:if test="${content.board_state==2}">selected</c:if>>비공개</option>
									</select>
								</h4>
								<i class="fas fa-user-friends"></i>
								<c:if test="${content.photo == 1 }">
									<img
										src="${pageContext.request.contextPath }/dashboard/image?boardno=${content.board_no }"
										width="40%">
								</c:if>
								<p>${content.board_content }</p>
							</div>
						</div>
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
										<button class="btn">좋아요 ??개</button>
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