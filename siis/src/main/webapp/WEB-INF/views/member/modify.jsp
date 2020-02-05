<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<script>
function previewImage(target){
    
    if(target.files && target.files[0]){
        
        var reader = new FileReader();

        reader.onload = function(data){

            var img = document.querySelector("#preview");
            img.src = data.target.result;
            
        }
        reader.readAsDataURL(target.files[0]);
    }
}
</script>


<form action="modify" method="post" enctype="multipart/form-data">
	<input type="hidden" name="member_no" value="${memberVo.member_no}">
	<table class="table table-hover">
		<thead>
			<td colspan="3">${memberVo.member_name} 프로필</td>
		</thead>
	 	<tbody>
	 		<tr class="table-secondary">
	 			<td rowspan="8">
	 				<div>
	 					<img src="download?member_no=${memberVo.member_no}" width="100%" height="100%" id="preview">
	 				</div>
	 				<div>
	 					<input type="file" name="member_file" multiple accept="image/*" onchange="previewImage(this);">
	 				</div>
	 			</td>
	 		</tr>
		    <tr class="table-secondary">
				<th scope="row">생일</th>
				<td>
					<div class="form-group">
					  <input type="text" name="member_birth" value="${memberVo.member_birth}" class="form-control" id="inputDefault">
					</div>
				</td>
		    </tr>
		    <tr class="table-secondary">
			    <th scope="row">성별</th>
			    <td>
			    	<div class="form-group">
					    <select class="form-control" id="exampleSelect1" name="member_gender">
					    	<option class="current-status" value="${memberVo.member_gender}">${memberVo.member_gender}</option>
						    <option value="남">남</option>
						    <option value="여">여</option>
					     </select>
			    	 </div>
			    </td>
		    </tr>
		    <tr class="table-secondary">
			    <th scope="row">전화번호</th>
			    <td>
			    	<div class="form-group">
					  <input type="text" name="member_phone" value="${memberVo.member_phone}" class="form-control" id="inputDefault">
					</div>
			    </td>
		    </tr>
		    <tr class="table-secondary">
			    <th scope="row">공개설정</th>
			    <td>
			    	<div class="form-group">
					    <select class="form-control" id="exampleSelect1" name="member_profile_state">
					    	<option class="current-status" value="${memberVo.member_profile_state}">${memberVo.member_profile_state}</option>
						    <option value="전체공개">전체공개</option>
						    <option value="비공개">비공개</option>
						    <option value="친구공개">친구공개</option>
					     </select>
			    	 </div>
				</td>
		    </tr>
		    <tr class="table-secondary">
			    <th scope="row">지역</th>
			    <td>
			    	<div class="form-group">
					  <input type="text" name="member_home" value="${memberVo.member_home}" class="form-control" id="inputDefault">
					</div>			    
			    </td>
		    </tr>
		    <tr class="table-secondary">
			    <th scope="row">학교</th>
			    <td>
			    	<div class="form-group">
					  <input type="text" name="member_school" value="${memberVo.member_school}" class="form-control" id="inputDefault">
					</div>
			    </td>
		    </tr>
		    <tr class="table-secondary">
			    <th scope="row">직업</th>
			    <td>
			    	<div class="form-group">
					  <input type="text" name="member_job" value="${memberVo.member_job}" class="form-control" id="inputDefault">
					</div>
			    </td>
		    </tr>
		    <tr class="table-dark">
			    <th scope="row" colspan="3">
			    	<button type="submit" class="btn btn-outline-info">프로필수정</button>
			    	<a href="mypage"><button class="btn btn-outline-info">취소</button></a>
			    </th>
		    </tr>
	  	</tbody>
	</table> 
</form>