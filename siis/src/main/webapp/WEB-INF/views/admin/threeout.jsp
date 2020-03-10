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
	
	$(".warning-no").hide();
	$(".member-no").hide();
	$(".board-no").hide();
	
	$(".receipt").click(function(e){
		e.preventDefault();
		
		var warning_no = $(this).prev().text();
		var member_no = $(this).prev().prev().text();
		var board_no = $(this).prev().prev().prev().text();
		var state = $(this);
		
		$.ajax({
			url : "receipt?warning_no="+warning_no+"&member_no="+member_no+"&board_no="+board_no,
			type : "get",
			success:function(resp){
				console.log(resp);
				if(resp){
					state.parent().prev().prev().prev().prev().prev().prev().text("접수");
					state.hide();
					state.next().hide();
				}
			}
			
		});
	});
	
	$(".hold").click(function(e){
		e.preventDefault();
		
		var warning_no = $(this).prev().prev().text();
		var state = $(this);
		
		$.ajax({
			url : "hold?warning_no="+warning_no,
			type : "get",
			success:function(resp){
				console.log(resp);
				if(resp){
					state.parent().prev().prev().prev().prev().prev().prev().text("보류");
					state.hide();
				}
			}
			
		});
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
	
	.w-count{
		color:red;
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
								<th>처리상태</th>
								<th>피신고인</th>
								<th>피신고인 접수건수</th>
								<th>신고자</th>
								<th>신고내용</th>
								<th>게시글</th>
								<th>관리</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="warning" items="${list}">
								<tr>
									<td>${warning.state}</td>
									<td>${warning.target_email}[${warning.target_name}]</td>
									<td class="w-count">${warning.w_count}</td>
									<td>${warning.pusher_email}[${warning.pusher_name}]</td>
									<td>${warning.content}</td>
									<td>
										<a href="${pageContext.request.contextPath}/post/${warning.board_no}">
											${warning.board_no}
										</a>
									</td>
									<td>
										<div class="board-no">${warning.board_no}</div>
										<div class="member-no">${warning.target_no}</div>
										<div class="warning-no">${warning.warning_no}</div>
										<c:if test="${empty warning.state}">
											<a href="receipt?warning_no=${warning.warning_no}&board_no=${warning.board_no}" class="receipt">[접수]</a> 
											<a href="hold?warning_no=${warning.warning_no}" class="hold">[보류]</a>
										</c:if>
										<c:if test="${warning.state == '접수' }"></c:if>
										<c:if test="${warning.state == '보류' }">
											<a href="receipt?warning_no=${warning.warning_no}&board_no=${warning.board_no}" class="receipt">[접수]</a>
										</c:if>
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