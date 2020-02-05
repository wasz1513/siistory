<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<table class="table table-hover">
	<thead>
		<td colspan="3">${memberVo.member_name} 프로필</td>
	</thead>
 	<tbody>
 		<tr class="table-secondary">
 			<td rowspan="9">
				<div>
					<img src="download?member_no=${memberVo.member_no}" width="100%" height="100%">
				</div>
	 		</td>
 		</tr>
	    <tr class="table-secondary">
	      <th scope="row">이름</th>
	      <td>${memberVo.member_name}</td>
	    </tr>
	    <tr class="table-secondary">
	      <th scope="row">생일</th>
	      <td>${memberVo.member_birth}</td>
	    </tr>
	    <tr class="table-secondary">
	      <th scope="row">성별</th>
	      <td>${memberVo.member_gender}</td>
	    </tr>
	    <tr class="table-secondary">
	      <th scope="row">전화번호</th>
	      <td>${memberVo.member_phone}</td>
	    </tr>
	    <tr class="table-secondary">
	      <th scope="row">공개설정</th>
	      <td>${memberVo.member_profile_state}</td>
	    </tr>
	    <tr class="table-secondary">
	      <th scope="row">지역</th>
	      <td>${memberVo.member_home}</td>
	    </tr>
	    <tr class="table-secondary">
	      <th scope="row">학교</th>
	      <td>${memberVo.member_school}</td>
	    </tr>
	    <tr class="table-secondary">
	      <th scope="row">직업</th>
	      <td>${memberVo.member_job}</td>
	    </tr>
	    <tr class="table-dark">
	      <th scope="row" colspan="3">
	      	<a href="modify"><button class="btn btn-outline-info">프로필수정</button></a>
	      </th>
	    </tr>
  	</tbody>
</table> 