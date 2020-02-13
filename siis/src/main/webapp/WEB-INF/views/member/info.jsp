<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<style>
	article{
		width:50%;
        text-align:center;
        align-items: center;
        margin:auto;
        margin-top:100px;
	}
	
	.table .img-td{
		vertical-align:middle;
	}
	
	.img-td{
		width:250px;
	}

	.my-img{
		width:250px;
		height:250px;
	}
	
	.change-name{
		width:150px;
	}
</style>

<script>

$(function(){
	
	$(".follow-btn").click(function(){
		
		var friend_no = $(this).prev().val();
		var member_no = $(this).prev().prev().val();
		
		var url = "${pageContext.request.contextPath}/util/follow";
		var method = "post";
		
		var button = $(this);
		
		if(button.text()=="팔로우"){
			$.ajax({
				url : url,
				type : method,
				data : {'member_no':member_no, 'friend_no':friend_no, 'following':1},
				success:function(resp){
					if(resp==1){
// 						console.log($(this));
						button.removeClass("btn-primary");
						button.addClass("btn-outline-success");
						button.text("팔로잉");
					}
				}
			});			
		}else{
			$.ajax({
				url : url,
				type : method,
				data : {'member_no':member_no, 'friend_no':friend_no, 'following':0},
				success:function(resp){
					if(resp==1){
// 						console.log($(this));
						button.removeClass("btn-outline-success");
						button.addClass("btn-primary");
						button.text("팔로우");
					}
				}
			});			
		}
		
		
	});
	
});

</script>


<article>
	<section>

		<table class="table table-hover">
			<thead>	
				<tr>
					<td colspan="3">${memberInfo.member_name} 프로필</td>
				</tr>
			</thead>
		 	<tbody>
		 		<tr class="table-secondary">
		 			<td rowspan="9" class="img-td">
						<div class="my-img">
							<img src="${pageContext.request.contextPath}/util/download?member_no=${memberInfo.member_no}" width="100%" height="100%">
						</div>
			 		</td>
		 		</tr>
			    <tr class="table-secondary">
			      <th scope="row">이름</th>
			      <td>
			      ${memberInfo.member_name}
			      </td>
			    </tr>
			    <tr class="table-secondary">
			      <th scope="row">생일</th>
			      <td >${memberInfo.member_birth}</td>
			    </tr>
			    <tr class="table-secondary">
			      <th scope="row">성별</th>
			      <td >${memberInfo.member_gender}</td>
			    </tr>
			    <tr class="table-secondary">
			      <th scope="row">전화번호</th>
			      <td >${memberInfo.member_phone}</td>
			    </tr>
			    <tr class="table-secondary">
			      <th scope="row">공개설정</th>
			      <td >${memberInfo.member_profile_state}</td>
			    </tr>
			    <tr class="table-secondary">
			      <th scope="row">지역</th>
			      <td >${memberInfo.member_home}</td>
			    </tr>
			    <tr class="table-secondary">
			      <th scope="row">학교</th>
			      <td >${memberInfo.member_school}</td>
			    </tr>
			    <tr class="table-secondary">
			      <th scope="row">직업</th>
			      <td >${memberInfo.member_job}</td>
			    </tr>
			    <tr class="table-dark">
			      <th scope="row" colspan="3">
			      	<input type="hidden" value="${sessionScope.member_no}">
					<input type="hidden" value="${memberInfo.member_no}">
					<c:choose>
						<c:when test="${memberInfo.following == 1}">
							<button class="btn btn-outline-success follow-btn">팔로잉</button>
						</c:when>
						<c:otherwise>
							<button class="btn btn-primary follow-btn">팔로우</button>
						</c:otherwise>
					</c:choose>
			      </th>
			    </tr>
		  	</tbody>
		</table> 

	</section>
</article>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>