<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<h1>${memberVo.member_name} 정보</h1>

<div>
	이름
	${memberVo.member_name}
	<a href="#">수정</a>
</div>
<div>
	생일
	${memberVo.member_birth}
	<a href="#">수정</a>
</div>
<div>
	성별
	${memberVo.member_gender}
	<a href="#">수정</a>
</div>
<div>
	전화번호
	${memberVo.member_phone}
	<a href="#">수정</a>
</div>
<div>
	공개설정
	${memberVo.member_profile_state}
	<a href="#">수정</a>
</div>
<div>
	지역
	${memberVo.member_home}
	<a href="#">수정</a>
</div>
<div>
	학교
	${memberVo.member_school}
	<a href="#">수정</a>
</div>
<div>
	직업
	${memberVo.member_job}
	<a href="#">수정</a>
</div>