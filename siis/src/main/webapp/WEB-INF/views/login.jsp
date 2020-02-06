<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/crypto/crypto-js.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/crypto/hmac-sha256.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/sha256.js"></script>

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
				<div>
				<form action="login" method="post">
					<div class="form-group">
						<img src="${pageContext.request.contextPath}/resources/image/butterfly.svg" width="100%" height="200">
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">Email address</label>
				    	<input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" name="email" required>
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Password</label>
						<input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="member_pw" required>
					</div>
					<div class="form-group">
		                <button type="submit" class="btn btn-outline-success btn-block">로그인</button>
		            </div>
				</form>
				</div>
				<div>
					<a href="findPw"><button class="btn btn-outline-primary btn-block">비밀번호 찾기</button></a>
				</div>
			</div>
		</div>
	</div>

</article>

<footer>

	<h1>세션email = ${sessionScope.email }</h1>

</footer>
