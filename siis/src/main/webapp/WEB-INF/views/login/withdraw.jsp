<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/darkly/bootstrap.min.css" rel="stylesheet" integrity="sha384-rCA2D+D9QXuP2TomtQwd+uP50EHjpafN+wruul0sXZzX/Da7Txn4tB9aLMZV4DZm" crossorigin="anonymous">
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

<header>
</header>

<article>
	<section>
		<div class="container">
			<div class="row">
				<div class="col-md">
					<div>
						<h1>탈퇴한 계정입니다</h1>
					</div>
					<div class="form-group">
						<a href="${pageContext.request.contextPath}/login"><button class="dormant-btn btn btn-outline-success btn-block">로그인</button></a>
					</div>
					<div class="form-group">
						<a href="${pageContext.request.contextPath}"><button class="btn btn-outline-info btn-block">회원가입</button></a>
					</div>				
		
				</div>
			</div>	
		</div>
	</section>
</article>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>