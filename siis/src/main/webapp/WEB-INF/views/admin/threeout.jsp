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
		background-color: EBFBFF;
        width: 20%;
        margin-top:150px;
	}

	section{
		background-color: C8FFFF;
		flex-grow:1;
		margin-top:150px;
	}

	.pagination{
		width:220px;
		margin:auto;
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
				
					<form action="threeout" method="post">
						<table class="table table-hover">
							<thead>
								<tr>
									<td colspan="4">
										<h2>신고 목록</h2>
									</td>
								</tr>
								<tr>
									<td colspan="4" align="center">
									피신고인 (Email / Name)
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="form-group">
											<select name="target_type" class="form-control">
												<option value="target_email">Email</option>
												<option value="target_name">Name</option>
											</select>
										</div>
									</td>
									<td colspan="2">
										<div class="form-group">
											<input type="text" name="target_keyword" class="form-control">
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
									<td colspan="4" align="center">
									신고자 (Email / Name)
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="form-group">
											<select name="pusher_type" class="form-control">
												<option value="pusher_email">Email</option>
												<option value="pusher_name">Name</option>
											</select>
										</div>
									</td>
									<td colspan="2">
										<div class="form-group">
											<input type="text" name="pusher_keyword" class="form-control">
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div class="form-group">
											신고처리
										</div>
									</td>
									<td>
										<div class="form-group">
											<select name="state" class="form-control">
												<option value="all">선택</option>
												<option value="null">접수전</option>
												<option value="보류">접수보류</option>
												<option value="접수">접수완료</option>
											</select>
										</div>
									</td>
									<td>
										<div class="form-group">
											신고내용
										</div>
									</td>
									<td>
										<div class="form-group">
											<select name="content_keyword" class="form-control">
												<option value="">선택</option>
												<option value="음란성">음란성</option>
												<option value="광고성">광고성</option>
												<option value="언어적">언어적</option>
												<option value="분란유도">분란유도</option>
											</select>
										</div>
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="4" align="right">
										<div class="form-group">
											<button type="submit" class="btn btn-outline-success btn-block">신고 검색</button>
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
								<th>피신고인</th>
								<th>신고자</th>
								<th>신고내용</th>
								<th>게시글</th>
								<th>관리</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="warning" items="${list}">
								<tr>
									<td>${warning.target_email}[${warning.target_name}]</td>
									<td>${warning.pusher_email}[${warning.pusher_name}]</td>
									<td>${warning.content}</td>
									<td>${warning.board_no}</td>
									<td>접수 / 보류</td>						
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<c:if test="${not empty pno}">
							<tr>
								<td colspan="5">
									<div>
										<ul class="pagination">
									
											<c:if test="${startBlock > 1}">
												<li class="page-item">
												<a class="page-link" href="warninglist?pno=${startBlock-1}&count=${count}
												&target_type=${target_type}&pusher_type=${pusher_type}&target_keyword=${target_keyword}
												&pusher_keyword=${pusher_keyword}&content_keyword=${content_keyword}&state=${state}">&laquo;</a>
												</li>
											</c:if>
										    
										    <c:forEach var="i" begin="${startBlock}" end="${finishBlock}">
										    	<c:choose>
										    		<c:when test="${i == pno}">
														<li class="page-item active"><a class="page-link" href="#">${i}</a></li>
													</c:when>
													<c:otherwise>
														<li class="page-item">
															<a class="page-link" href="warninglist?pno=${i}&count=${count}
															&target_type=${target_type}&pusher_type=${pusher_type}&target_keyword=${target_keyword}
															&pusher_keyword=${pusher_keyword}&content_keyword=${content_keyword}&state=${state}">${i}</a>
														</li>
													</c:otherwise>
												</c:choose>
											</c:forEach>

										    <c:if test="${finishBlock < pageCount}">
												<li class="page-item">
													<a class="page-link" href="warninglist?pno=${startBlock-1}&count=${count}
													&target_type=${target_type}&pusher_type=${pusher_type}&target_keyword=${target_keyword}
													&pusher_keyword=${pusher_keyword}&content_keyword=${content_keyword}&state=${state}">&raquo;</a>
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