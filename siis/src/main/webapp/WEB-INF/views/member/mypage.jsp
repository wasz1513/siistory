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


<article>
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
			      <th scope="row" colspan="4">
			      	<a href="modify"><button class="btn btn-outline-info">프로필수정</button></a>
			      </th>
			    </tr>
		  	</tbody>
		</table> 

	</section>
</article>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>