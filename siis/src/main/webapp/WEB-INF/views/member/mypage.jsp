<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<style>
/* 	article{ */
/* 		width: 80%; */
/*         text-align:center; */
/*         align-items: center; */
/*         margin:auto; */
/*         margin-top:100px; */
/* 	} */
	
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
	
	article {
	display: flex; /*바로안에있는것만적용됨*/
	flex-wrap: wrap;
	width: 80%;
	margin: auto;
	text-align:center;
	}

.left-aside {
	/* 		background-color: EBFBFF; */
	width: 20%;
	margin-top: 150px;
	margin-right:30px;
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
	margin-left:30px;
	}
	
	.new-friend{
	color:orange;
}

.r * {
	font-size:0.8rem;
}

.detail-list.detail-list {
	background-color: black;
}

.detail-list.detail-list:hover {
	background-color: gray;
}

</style>

<script>
$(function(){
	$(".change-name-btn").click(function(){
		if($(this).text()=="이름수정"){
			var nameCell = $(this).parent().prev();
			var name = nameCell.text().trim();
			
			nameCell.empty();
			
			$("<input>").addClass("form-control").attr("type","text").attr("name","member_name").val(name).appendTo(nameCell);
			
			$(this).text("이름저장");			
		}else{
			var nameCell = $(this).parent().prev();
			var name = nameCell.children().val();
			
			var url = "changeName?member_name="+name;
			var method = "get";
			
			$.ajax({
				url: url,
				type: method,
				success:function(resp){
					if(resp==1){
						nameCell.empty();
						
						nameCell.text(name);
						
						$(".change-name-btn").text("이름수정");	
					}
				}
			
									
			});
			
		}
	});
});
</script>

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

	<section>

		<table class="table table-hover">
			<thead>	
				<tr>
					<td colspan="4">${memberVo.member_name} 프로필</td>
				</tr>
			</thead>
		 	<tbody>
		 		<tr class="table-secondary">
		 			<td rowspan="9" class="img-td">
						<div class="my-img">
							<img src="${pageContext.request.contextPath}/util/download?member_no=${memberVo.member_no}" width="100%" height="100%">
						</div>
			 		</td>
		 		</tr>
			    <tr class="table-secondary">
			      <th scope="row">이름</th>
			      <td>
			      ${memberVo.member_name}
			      </td>
			      <td class="change-name">
			      	<button class="btn btn-outline-info change-name-btn">이름수정</button>			      	
			      </td>
			    </tr>
			    <tr class="table-secondary">
			      <th scope="row">생일</th>
			      <td colspan="2">${memberVo.member_birth}</td>
			    </tr>
			    <tr class="table-secondary">
			      <th scope="row">성별</th>
			      <td colspan="2">${memberVo.member_gender}</td>
			    </tr>
			    <tr class="table-secondary">
			      <th scope="row">전화번호</th>
			      <td colspan="2">${memberVo.member_phone}</td>
			    </tr>
			    <tr class="table-secondary">
			      <th scope="row">공개설정</th>
			      <td colspan="2">${memberVo.member_profile_state}</td>
			    </tr>
			    <tr class="table-secondary">
			      <th scope="row">지역</th>
			      <td colspan="2">${memberVo.member_home}</td>
			    </tr>
			    <tr class="table-secondary">
			      <th scope="row">학교</th>
			      <td colspan="2">${memberVo.member_school}</td>
			    </tr>
			    <tr class="table-secondary">
			      <th scope="row">직업</th>
			      <td colspan="2">${memberVo.member_job}</td>
			    </tr>
			    <tr class="table-dark">
			      <th scope="row" colspan="2">
			      	<a href="modify"><button class="btn btn-outline-info">프로필수정</button></a>
			      </th>
			      <th scope="row" colspan="2">
			      	<a href="${pageContext.request.contextPath}/changePw?email=${memberVo.email}"><button class="btn btn-outline-info">비밀번호 변경</button></a>
			      </th>
			    </tr>
		  	</tbody>
		</table> 

	</section>
	
	<aside class="right-aside">
		<div class="card border-primary mb-3" style="max-width: 20rem;">
			<div class="card-header" style="text-align: center">접속 리스트</div>

			<div class="list-group friend-list"></div>

		</div>
	</aside>
</article>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>