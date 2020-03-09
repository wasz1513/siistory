<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/darkly/bootstrap.min.css" rel="stylesheet" integrity="sha384-rCA2D+D9QXuP2TomtQwd+uP50EHjpafN+wruul0sXZzX/Da7Txn4tB9aLMZV4DZm" crossorigin="anonymous">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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
	
	#login-error{
		font-size: large;
		font-style: oblique;
    	font-family: -webkit-pictograph;
	}
</style>

<article>
	<section>
	
	<div class="container">
		<div class="row">
			<div class="col-md">
				<div>
					<form action="login" method="post">
						<div class="form-group">
							<a href="${pageContext.request.contextPath}">
								<img src="${pageContext.request.contextPath}/resources/image/butterfly.svg" width="100%" height="200">
							</a>
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">Email address</label>
					    	<input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" name="email" value="${email}"required>
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Password</label>
							<input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="member_pw" required>
						</div>
						<div class="form-group">
							<c:if test="${!empty param.error}">
								<small class="form-text text-muted" id="login-error">Please check email or password</small>
							</c:if>
						</div>
						<div class="form-group">
			                <button type="submit" class="btn btn-outline-success btn-block">로그인</button>
			            </div>
					</form>
				</div>
				<div class="form-group">
					<a href="findPw"><button class="btn btn-outline-info btn-block">비밀번호 찾기</button></a>
				</div>
				<div class="form-group">
						<a href="${pageContext.request.contextPath}"><button class="btn btn-outline-primary btn-block">회원가입</button></a>
				</div>
			</div>
		</div>
	</div>

	</section>
</article>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
