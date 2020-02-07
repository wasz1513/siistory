<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/resources/js/crypto/crypto-js.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/crypto/hmac-sha256.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/sha256.js"></script>
<script>
$(function(){
	
	$("#inputChangePw").on("blur", function(){
		var password = 	$("#inputChangePw").val();
		var passwordCheck = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
		if(!passwordCheck.test(password)){
			$("#inputChangePw").next().text("비밀번호를 확인해주세요");
			$("#changePwSubmit").prop("disabled", true);
		}else{
			$("#inputChangePw").next().text("");
			$("#changePwSubmit").prop("disabled", false);
		}
	});
	
	$("#inputChangePwCheck").on("blur", function(){
		var password = 	$("#inputChangePw").val();
		var check = $("#inputChangePwCheck").val();
		
		if(password===check){
			$("#inputChangePwCheck").next().text("");
			$("#changePwSubmit").prop("disabled", false);
		}else{
			$("#inputChangePwCheck").next().text("비밀번호가 일치하지 않습니다");
			$("#changePwSubmit").prop("disabled", true);
		}
		
	});
	
});

</script>
<style>
	article{
		width:30%;
        text-align:center;
        align-items: center;
        margin:auto;
        margin-top:100px;
	}
</style>


<article>

	<div class="container">
		<div class="row">
			<div class="col-md">
				<form class="changePw-form" action="changePw" method="post">
					<div class="form-group">
						<img src="${pageContext.request.contextPath}/resources/image/butterfly.svg" width="100%" height="200">
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">Email address</label>
				    	<input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="${email}" name="email" required>
					</div>
					<div class="form-group">
						<label for="inputChangePw">New pw</label>
						<input type="password" class="form-control" id="inputChangePw" name="member_pw" required>
						<small class="form-text text-muted"></small>
				      	<small id="passwordHelp" class="form-text text-muted">최소 8 자, 대문자 하나 이상, 소문자 하나, 숫자 하나 및 특수 문자 하나 이상</small>
					</div>
					<div class="form-group">
						<label for="inputChangePwCheck">New pw check</label>
						<input type="password" class="form-control" id="inputChangePwCheck" required>
						<small class="form-text text-muted"></small>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-outline-success btn-block" id="changePwSubmit">비밀번호 변경하기</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</article>