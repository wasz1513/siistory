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
	.pagination{
		width:220px;
		margin:auto;
	}
</style>

<article>
	<section>
	
		<div class="container">
			<div class="row">
				<div class="col-md">
				
					<form action="management" method="post">
						<table class="table table-hover">
							<thead>
								<tr>
									<td colspan="2">
										<div class="form-group">
											<select name="type" class="form-control">
												<option value="email">Email</option>
												<option value="member_name">Name</option>
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
											회원상태
										</div>
									</td>
									<td>
										<div class="form-group">
											<select name="member_state" class="form-control">
												<option value="">선택</option>
												<option value="정상">정상</option>
												<option value="정지">정지</option>
												<option value="휴면">휴면</option>
												<option value="탈퇴">탈퇴</option>
											</select>
										</div>
									</td>
									<td>
										<div class="form-group">
											전화번호
										</div>
									</td>
									<td>
										<div class="form-group">
											<input type="text" name="mebmer_phone" class="form-control">
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div class="form-group">
											나이
										</div>
									</td>
									<td>
										<div class="form-group">
											<input type="text" name="mebmer_birth" class="form-control">
										</div>
									</td>
									<td>
										<div class="form-group">
											성별
										</div>
									</td>
									<td>
										<div class="form-group">
											<select name="member_gender" class="form-control">
												<option value="">선택</option>
												<option value="남">남</option>
												<option value="여">여</option>
											</select>
										</div>
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="4" align="right">
										<div class="form-group">
											<button type="submit" class="btn btn-outline-success btn-block">회원 검색</button>
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
								<th>ProfileState</th>
								<th>Gender</th>
								<th>Phone</th>
								<th>Location</th>
								<th>Job</th>
								<th>Management</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="member" items="${list}">
								<tr>
									<td>${member.email}</td>
									<td>${member.member_name}</td>
									<td>${member.member_state}</td>
									<td>${member.member_profile_state}</td>
									<td>${member.member_gender}</td>
									<td>${member.member_phone}</td>
									<td>${member.member_home}</td>
									<td>${member.member_job}</td>
									<td>
									<a href="${pageContext.request.contextPath}/member/info?member_no=${member.member_no}">[정보]</a> 
									[정지] 
									[???]
									</td>
								</tr>
							</c:forEach>
						</tbody>	
						<tfoot>
							<c:if test="${not empty pno}">
							<tr>
								<td colspan="9">
									<div>
										<ul class="pagination">
									
											<c:if test="${startBlock > 1}">
												<li class="page-item">
												<a class="page-link" href="list?type=${type}&keyword=${keyword}&pno=${startBlock-1}&count=${count}
												&member_state=${member_state}&member_gender=${member_gender}&member_birth=${member_birth}&member_phone=${member_phone}">&laquo;</a>
												</li>
											</c:if>
										    
										    <c:forEach var="i" begin="${startBlock}" end="${finishBlock}">
										    	<c:choose>
										    		<c:when test="${i == pno}">
														<li class="page-item active"><a class="page-link" href="#">${i}</a></li>
													</c:when>
													<c:otherwise>
														<li class="page-item">
															<a class="page-link" href="list?type=${type}&keyword=${keyword}&pno=${i}&count=${count}
															&member_state=${member_state}&member_gender=${member_gender}&member_birth=${member_birth}&member_phone=${member_phone}">${i}</a>
														</li>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										    
										
										    <c:if test="${finishBlock < pageCount}">
												<li class="page-item">
													<a class="page-link" href="list?type=${type}&keyword=${keyword}&pno=${finishBlock+1}&count=${count}
													&member_state=${member_state}&member_gender=${member_gender}&member_birth=${member_birth}&member_phone=${member_phone}">&raquo;</a>
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