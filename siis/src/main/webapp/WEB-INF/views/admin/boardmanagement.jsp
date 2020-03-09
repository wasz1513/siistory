<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<script>
$(function(){
	$(".view-detail").hide();
	
	
	$("#view-details").on("change", function(){

		if($("#view-details").is(':checked')){
			$(".view-detail").slideDown();
		}else{
			$(".view-detail").hide();
		}
	});
	
	$("#file-exist").on("change", function(){
		if($("#file-exist").is(':checked')){
			$("#photo-exist").attr('value', "1");
		}else{
			$("#photo-exist").attr('value', "0");
		}
	});
	
});

	
</script>

<style>
	article{
		display: flex; /*바로안에있는것만적용됨*/
        flex-wrap: wrap;
		width:80%;
		margin:auto;
	}
	
	.left-aside{
/* 		background-color: EBFBFF; */
        width: 20%;
        margin-top:150px;
	}

	section{
/* 		background-color: C8FFFF; */
		flex-grow:1;
		margin-top:150px;
	}

	.pagination{
		width:220px;
		margin:auto;
	}
	
	.suspend-exe{
		color:orange;
	}
</style>

<article>
	<aside class="left-aside">	
		<jsp:include page="/WEB-INF/views/template/adminmenu.jsp"></jsp:include>
	</aside>
	
	<section>
	
		<div class="container">
			<div class="row">
				<div class="col-md">
				
					<form action="boardmanagement" method="post">
						<table class="table table-hover">
							<thead>
								<tr>
									<td colspan="4">
										<h2>게시글 목록</h2>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="form-group">
											<select name="type" class="form-control">
												<option value="email">Email</option>
												<option value="board_writer">Name</option>
											</select>
										</div>
									</td>
									<td colspan="2">
										<div class="form-group">
											<input type="text" name="keyword" class="form-control">
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="right">
										<div class="form-group">
											<div class="custom-control custom-checkbox">
												<input type="checkbox" class="custom-control-input" id="view-details">
												<label class="custom-control-label" for="view-details">상세검색</label>
											</div>
										</div>
									</td>
								</tr>
							</thead>
							<tbody class="view-detail">
								<tr>
									<td>
										<div class="form-group">
											게시글상태
										</div>
									</td>
									<td>
										<div class="form-group">
											<select name="board_state" class="form-control">
												<option value=-1>선택</option>
												<option value=0>전체공개</option>
												<option value=1>친구공개</option>
												<option value=2>비공개</option>
											</select>
										</div>
									</td>
									<td>
										<div class="form-group">
											파일첨부 유/무
										</div>
									</td>
									<td>
										<div class="form-group">
											<div class="custom-control custom-checkbox">
												<input type="checkbox" class="custom-control-input" id="file-exist">
												<label class="custom-control-label" for="file-exist">파일첨부</label>
												<input type="hidden" name="photo" id="photo-exist" value="0">
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div class="form-group">
											글내용
										</div>
									</td>
									<td>
										<div class="form-group">
											<input type="text" name="board_content" class="form-control">
										</div>
									</td>
									<td>
										<div class="form-group">
											작성일
										</div>
									</td>
									<td>
										<div class="form-group">
											<input type="date" name="board_wdate">
										</div>
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="4" align="right">
										<div class="form-group">
											<button type="submit" class="btn btn-outline-success btn-block">게시글검색</button>
										</div>
									</td>
								</tr>
							</tfoot>
						</table>
					</form>
				</div>
			</div>
		</div>
		
		<div class="container">
			<div class="row">
				<div class="col-md">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Email</th>
								<th>Name</th>
								<th>State</th>
								<th>Content</th>
								<th>Writedate</th>
								<th>File-exist</th>
								<th>Management</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="board" items="${list}">
								<tr>
									<td>${board.email}</td>
									<td>${board.board_writer}</td>
									<td>
										<c:choose>
										<c:when test="${board.board_state==0}">
											전체공개
										</c:when>
										<c:when test="${board.board_state==1}">
											친구공개
										</c:when>
										<c:when test="${board.board_state==2}">
											비공개
										</c:when>
										<c:otherwise>
											신고게시글
										</c:otherwise>
										</c:choose>
									</td>
									<td>${board.board_content}</td>
									<td>${board.board_wdate}</td>
									<td>
										<c:choose>
										<c:when test="${board.photo==0}">
											파일없음
										</c:when>
										<c:otherwise>
											파일있음
										</c:otherwise>
										</c:choose>
									</td>
									<td>
									<a href="${pageContext.request.contextPath}/post/${board.board_no}">[상세보기]</a> 
									<a href="#">[삭제]</a> 
									</td>
								</tr>
							</c:forEach>
						</tbody>	
						<tfoot>
							<c:if test="${not empty pno}">
							<tr>
								<td colspan="7">
									<div>
										<ul class="pagination">
									
											<c:if test="${startBlock > 1}">
												<li class="page-item">
												<a class="page-link" href="boardlist?type=${type}&keyword=${keyword}&pno=${startBlock-1}&count=${count}
												&board_state=${board_state}&photo=${photo}&board_content=${board_content}&board_wdate=${board_wdate}">&laquo;</a>
												</li>
											</c:if>
										    
										    <c:forEach var="i" begin="${startBlock}" end="${finishBlock}">
										    	<c:choose>
										    		<c:when test="${i == pno}">
														<li class="page-item active"><a class="page-link" href="#">${i}</a></li>
													</c:when>
													<c:otherwise>
														<li class="page-item">
															<a class="page-link" href="boardlist?type=${type}&keyword=${keyword}&pno=${i}&count=${count}
															&board_state=${board_state}&photo=${photo}&board_content=${board_content}&board_wdate=${board_wdate}">${i}</a>
														</li>
													</c:otherwise>
												</c:choose>
											</c:forEach>

										    <c:if test="${finishBlock < pageCount}">
												<li class="page-item">
													<a class="page-link" href="boardlist?type=${type}&keyword=${keyword}&pno=${finishBlock+1}&count=${count}
													&board_state=${board_state}&photo=${photo}&board_content=${board_content}&board_wdate=${board_wdate}">&raquo;</a>
												</li>
											</c:if>
										</ul>
									</div>
								</td>
							</tr>
							</c:if>
						</tfoot>				
					</table>
				</div>
			</div>
		</div>		
		
	</section>
</article>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>