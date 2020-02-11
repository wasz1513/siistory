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
	
	.table td{
		vertical-align:middle;
	}
</style>

<script>

$(function(){
	
	$(".follow-btn").click(function(){
		
		var friend_no = $(this).prev().val();
		var member_no = $(this).prev().prev().val();
		
		var url = "follow";
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
			<tbody>
				<c:forEach var="memberFollowVo" items="${list}">
					<c:if test="${memberFollowVo.member_no != sessionScope.member_no}">
						<tr class="table-active">
							<td>
								<div class="card border-light mb-3" style="max-width: 50rem;">
								  <div class="card-body">
								  	<p class="card-title">${memberFollowVo.email}</p>
								    <p class="card-text form-text text-muted">name : ${memberFollowVo.member_name}</p>
								  </div>
								</div>
							</td>
							<td>
								<input type="hidden" value="${sessionScope.member_no}">
								<input type="hidden" value="${memberFollowVo.member_no}">
								<c:choose>
									<c:when test="${memberFollowVo.following == 1}">
										<button class="btn btn-outline-success follow-btn">팔로잉</button>
									</c:when>
									<c:otherwise>											
										<button class="btn btn-primary follow-btn">팔로우</button>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>				
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	
	</section>
</article>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>