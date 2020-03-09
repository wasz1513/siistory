<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<style>
article {
	width: 80%;
	margin: auto;
	margin-top: 150px;
}

/* div {
	box-sizing: border-box;
	border: 1px dotted gray;
} */

.scroll-div {
	overflow-y: scroll;
	height: 600px;
}

.scroll-div::-webkit-scrollbar {
	display: none;
}

.carousel-inner > .item > img, .carousel-inner > .item > a > img {
    width: 100%;
}

.carousel {
  max-height: 700px;
  overflow: hidden;
}
 .photo {
    width: 100%;
    height: auto;
  }


</style>


<article>
	<div class="container-fluid">
		<div class="row">
			<div class="offset-md-2 col-md-8">
				<!-- 
                    내부 배치 
                    .col-md-6 : 50%였다가 medium 시 100%가 되는 화면
                    .col-6 : 무조건 50%인 화면
                -->
				<div class="row">
					<div class="col-md-6">
						<div id="slide" class="carousel slide" data-ride="carousel" data-interval="false">
							<!-- The slideshow -->
							<div class="carousel-inner">
							<c:forEach var="pic" items="${photolist }" varStatus="status">
							<c:choose>
								<c:when test="${status.count == 1}">
									<div class="carousel-item active">
										<img class="photo" src="${pageContext.request.contextPath }/post/imageall/${photopost.member_no }/${pic}">
									</div>
								</c:when>
								<c:otherwise>
									<div class="carousel-item">
										<img class="photo" src="${pageContext.request.contextPath }/post/imageall/${photopost.member_no }/${pic}">
									</div>
								</c:otherwise>
							</c:choose>
							</c:forEach>
							</div>
							
							<c:if test="${fn:length(photolist) > 1}">
							<a class="carousel-control-prev" href="#slide" data-slide="prev">
								<span class="carousel-control-prev-icon"></span>
							</a> <a class="carousel-control-next" href="#slide" data-slide="next">
								<span class="carousel-control-next-icon"></span>
							</a>
							</c:if>

						</div>
					</div>
					<div class="col-md-6">

						<div class="container scroll-div">


							<!-- 글정보 -->
							<div class="row">
								<div class="col-12">
									<div class="media p-3">
										<img
											src="${pageContext.request.contextPath }/util/download?member_no=${photopost.member_no}"
											class="mr-3 mt-3 rounded-circle" style="width: 30px;">
										<div class="media-body">
											<h4>${photopost.board_writer }
												<small><i>Posted on February 19, 2016</i></small>
											</h4>
											<p>${photopost.board_content}</p>
										</div>
									</div>

									<c:forEach var="reply" items="${photopost.replylist }">
												<div class="media p-3">
													<img
														src="${pageContext.request.contextPath }/util/download?member_no=${reply.writer_no}"
														class="mr-3 mt-3 rounded-circle" style="width: 30px;">
													<div class="media-body" id="${reply.reply_no }">
														<h4>${reply.reply_writer }
															<small><i>Posted on ?? days ago</i></small>
														</h4>
														<p>${reply.reply_content}</p>
														<button class="btn">좋아요 ??개</button>
														<button class="btn replyadd">답글달기</button>
														<button class="btn commentview">답글 보기(??개)</button>

														<!-- 답글(대댓글) 추가하면 뜨는 곳 -->
													</div>

												</div>
									</c:forEach>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-12">
								<div class="input-group mb-3">
									<input type="text" class="form-control replyphotopost"
										placeholder="댓글 달기..">
									<div class="input-group-append">
										<button class="btn btn-primary submit" type="button">게시</button>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</article>

