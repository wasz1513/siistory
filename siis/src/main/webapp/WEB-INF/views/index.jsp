<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!-- 
	주소 = siistory/
	회원가입 or 로그인 화면
-->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SiiStory</title>
<link href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/darkly/bootstrap.min.css" rel="stylesheet" integrity="sha384-rCA2D+D9QXuP2TomtQwd+uP50EHjpafN+wruul0sXZzX/Da7Txn4tB9aLMZV4DZm" crossorigin="anonymous">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/crypto/crypto-js.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/crypto/hmac-sha256.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/sha256.js"></script>
<style>
	article{
		width:80%;
	}

</style>


</head>
<body>

<article>
	
<div class="container">
    <div class="row">
            
            <!-- 	이미지 -->
       
        <div class="col-md">
            <img src="https://via.placeholder.com/150">
        </div>
        
        <!-- 	회원가입 / 로그인  -->
        <div class="col-md">
            <form action="./" method="post">
                <div>
                    <input type="text" name="email" placeholder="이메일주소" required>
                </div>
                <div>
                    <input type="text" name="member_name" placeholder="사용자 이름" required>
                </div>
                <div>
                    <input type="password" name="member_pw" placeholder="비밀번호" required>
                </div>
                <div>
                    <button type="submit">가입</button>
                </div>
            </form>
            <div>
                <a href="login">로그인</a>
            </div>
        </div>       
    </div>
</div>
	
</article>

</body>
</html>