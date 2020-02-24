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
	
	.suspend-member{
		color:red;
	}
	
	.dormant-member{
		color:blue;
	}
	
	.withdraw-member{
		color:green;
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
	
		<div class="form-group">
			<c:if test="${memberInfo.member_state == '정지' }">
				<h1 class="suspend-member">정지회원</h1>
				<a href="${pageContext.request.contextPath}/admin/canclesuspend?member_no=${memberInfo.member_no}">[정지풀기]</a>
			</c:if>
			<c:if test="${memberInfo.member_state == '휴면' }">
				<h1 class="dormant-member">휴면회원</h1>
			</c:if>
			<c:if test="${memberInfo.member_state == '탈퇴' }">
				<h1 class="withdraw-member">탈퇴회원</h1>
			</c:if>
		</div>
		
		
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
			      <td>
				      <c:choose>
			      		<c:when test="${memberInfo.member_profile_state == '비공개'}">비공개</c:when>
			      		<c:when test="${memberInfo.member_profile_state == '친구공개'}">
			      			<c:choose>
			      				<c:when test="${memberInfo.friend_state==1 }">
			      					${memberInfo.member_birth}
			      				</c:when>
			      				<c:otherwise>
			      					비공개
			      				</c:otherwise>
			      			</c:choose>
			      		</c:when>
			      		<c:otherwise>${memberInfo.member_birth}</c:otherwise>
				      </c:choose>
			      </td>
			    </tr>
			    <tr class="table-secondary">
			      <th scope="row">성별</th>
			      <td>
			      	<c:choose>
			      		<c:when test="${memberInfo.member_profile_state == '비공개'}">비공개</c:when>
			      		<c:when test="${memberInfo.member_profile_state == '친구공개'}">
			      			<c:choose>
			      				<c:when test="${memberInfo.friend_state==1 }">
			      					${memberInfo.member_gender}
			      				</c:when>
			      				<c:otherwise>
			      					비공개
			      				</c:otherwise>
			      			</c:choose>
			      		</c:when>
			      		<c:otherwise>${memberInfo.member_gender}</c:otherwise>
			      	</c:choose>
			      </td>
			    </tr>
			    <tr class="table-secondary">
			      <th scope="row">전화번호</th>
			      <td>
			      	<c:choose>
			      		<c:when test="${memberInfo.member_profile_state == '비공개'}">비공개</c:when>
			      		<c:when test="${memberInfo.member_profile_state == '친구공개'}">
			      			<c:choose>
			      				<c:when test="${memberInfo.friend_state==1 }">
			      					${memberInfo.member_phone}
			      				</c:when>
			      				<c:otherwise>
			      					비공개
			      				</c:otherwise>
			      			</c:choose>
			      		</c:when>
			      		<c:otherwise>${memberInfo.member_phone}</c:otherwise>
			      	</c:choose>
			      </td>
			    </tr>
			    <tr class="table-secondary">
			      <th scope="row">공개설정</th>
			      <td>
			      ${memberInfo.member_profile_state}
			      </td>
			    </tr>
			    <tr class="table-secondary">
			      <th scope="row">지역</th>
			      <td>
			      	<c:choose>
			      		<c:when test="${memberInfo.member_profile_state == '비공개'}">비공개</c:when>
			      		<c:when test="${memberInfo.member_profile_state == '친구공개'}">
			      			<c:choose>
			      				<c:when test="${memberInfo.friend_state==1 }">
			      					${memberInfo.member_home}
			      				</c:when>
			      				<c:otherwise>
			      					비공개
			      				</c:otherwise>
			      			</c:choose>
			      		</c:when>
			      		<c:otherwise>${memberInfo.member_home}</c:otherwise>
			      	</c:choose>
			      </td>
			    </tr>
			    <tr class="table-secondary">
			      <th scope="row">학교</th>
			      <td>
			      	<c:choose>
			      		<c:when test="${memberInfo.member_profile_state == '비공개'}">비공개</c:when>
			      		<c:when test="${memberInfo.member_profile_state == '친구공개'}">
			      			<c:choose>
			      				<c:when test="${memberInfo.friend_state==1 }">
			      					${memberInfo.member_school}
			      				</c:when>
			      				<c:otherwise>
			      					비공개
			      				</c:otherwise>
			      			</c:choose>
			      		</c:when>
			      		<c:otherwise>${memberInfo.member_school}</c:otherwise>
			      	</c:choose>
			      </td>
			    </tr>
			    <tr class="table-secondary">
			      <th scope="row">직업</th>
			      <td>
			      	<c:choose>
			      		<c:when test="${memberInfo.member_profile_state == '비공개'}">비공개</c:when>
			      		<c:when test="${memberInfo.member_profile_state == '친구공개'}">
			      			<c:choose>
			      				<c:when test="${memberInfo.friend_state==1 }">
			      					${memberInfo.member_job}
			      				</c:when>
			      				<c:otherwise>
			      					비공개
			      				</c:otherwise>
			      			</c:choose>
			      		</c:when>
			      		<c:otherwise>${memberInfo.member_job}</c:otherwise>
			      	</c:choose>
			      </td>
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