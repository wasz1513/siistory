<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<form action="modify" method="post" enctype="multipart/form-data">
	<input type="hidden" name="member_no" value="${memberVo.member_no}">
	<table class="table table-hover">
		<thead>
			<td colspan="3">${memberVo.member_name} 프로필</td>
		</thead>
	 	<tbody>
	 		<tr class="table-secondary">
	 			<td rowspan="9">
	 				<div>
	 					<img src="https://via.placeholder.com/150">
	 				</div>
	 				<div>
	 					<input type="file" name="member_file" multiple accept="image/*">
	 				</div>
	 			</td>
	 		</tr>
		    <tr class="table-secondary">
		      	<th scope="row">이름</th>
			    <td>
			    	<input type="text" name="member_name" value="${memberVo.member_name}">
			    </td>
		    </tr>
		    <tr class="table-secondary">
				<th scope="row">생일</th>
				<td>
					<input type="text" name="member_birth" value="${memberVo.member_birth}">
				</td>
		    </tr>
		    <tr class="table-secondary">
			    <th scope="row">성별</th>
			    <td>
			    	<input type="text" name="member_gender" value="${memberVo.member_gender}">
			    </td>
		    </tr>
		    <tr class="table-secondary">
			    <th scope="row">전화번호</th>
			    <td>
			    	<input type="text" name="member_phone" value="${memberVo.member_phone}">
			    </td>
		    </tr>
		    <tr class="table-secondary">
			    <th scope="row">공개설정</th>
			    <td>
			    	<input type="text" name="member_profile_state" value="${memberVo.member_profile_state}">
				</td>
		    </tr>
		    <tr class="table-secondary">
			    <th scope="row">지역</th>
			    <td>
			   		<input type="text" name="member_home" value="${memberVo.member_home}">
			    </td>
		    </tr>
		    <tr class="table-secondary">
			    <th scope="row">학교</th>
			    <td>
			    	<input type="text" name="member_name" value="${memberVo.member_name}">
			    </td>
		    </tr>
		    <tr class="table-secondary">
			    <th scope="row">직업</th>
			    <td>
			    	<input type="text" name="member_job" value="${memberVo.member_job}">
			    </td>
		    </tr>
		    <tr class="table-dark">
			    <th scope="row" colspan="3">
			    	<a href="modify">프로필수정</a>
			    </th>
		    </tr>
	  	</tbody>
	</table> 
</form>