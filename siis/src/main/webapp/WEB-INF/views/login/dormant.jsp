<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/darkly/bootstrap.min.css" rel="stylesheet" integrity="sha384-rCA2D+D9QXuP2TomtQwd+uP50EHjpafN+wruul0sXZzX/Da7Txn4tB9aLMZV4DZm" crossorigin="anonymous">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/crypto/crypto-js.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/crypto/hmac-sha256.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/sha256.js"></script>
<style>
	header{
		height:200px;
	}

	article{
		width:30%;
        text-align:center;
        align-items: center;
        margin:auto;
        margin-top:100px;
	}
</style>
<script>
$(function(){
	
	$(".dormantPw-form").hide();
	
	$(".dormant-btn").click(function(){
		$(".dormantPw-form").slideDown();
	});
});

</script>

<header>
</header>

<article>
	<section>
		<div class="container">
			<div class="row">
				<div class="col-md">
					<div>
						<c:choose>
							<c:when test="${!empty param.error}">
								<h1>비밀번호를 확인하세요</h1>							
							</c:when>
							<c:otherwise>
								<h1>6개월 이상 로그인하지 않은 계정은 휴면 계정으로 전환됩니다</h1>
								<h1>이 계정을 사용하시려면 비밀번호를 한번더 입력하세요</h1>
							</c:otherwise>						
						</c:choose>
					</div>
					<div class="form-group">
						<button class="dormant-btn btn btn-outline-success btn-block">계정사용하기</button>
					</div>
					<div class="form-group">
						<a href="${pageContext.request.contextPath}"><button class="btn btn-outline-info btn-block">회원가입</button></a>
					</div>	
					<div>
						<form class="dormantPw-form" action="#" method="post">
							<div class="form-group">
								<input type="hidden" name="email" value="${sessionScope.email}">
								<div class="form-group">
									<label for="exampleInputPassword1">Password</label>
									<input type="password" class="form-control" id="dormant-pw" placeholder="Password" name="member_pw" required>
								</div>
								<div class="form-group">
					                <button type="submit" class="btn btn-outline-success btn-block">로그인</button>
					            </div>
							</div>
						</form>
					</div>		
				</div>
			</div>
		</div>
	</section>
</article>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>