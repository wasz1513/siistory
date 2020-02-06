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
					<form action="findPw" method="post">
						<div class="form-group">
							<img src="${pageContext.request.contextPath}/resources/image/butterfly.svg" width="100%" height="200">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">Email address</label>
					    	<input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" name="email" required>
						</div>
						<div class="form-group">
			                <button type="submit" class="btn btn-outline-success btn-block" id="send-cert-button">인증번호 전송</button>
			            </div>
					</form>
				</div>
				
				<div>
					<form action="#" method="post">
						<input type="hidden" name="email">
						<div class="form-group">
							<input type="text" name="cert" placeholder="인증번호" required>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-outline-success btn-block" id="check-cert-button">인증번호 확인</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</article>