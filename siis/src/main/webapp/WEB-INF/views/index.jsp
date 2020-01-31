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
</head>
<body>

<article>
	
<!-- 	이미지 -->
	<div>
	</div>
	
<!-- 	회원가입 / 로그인  -->
	<div>
		<form action="./" method="post">
			<input type="text" name="email" placeholder="이메일주소" required>
			<input type="text" name="member_name" placeholder="사용자 이름" required>
			<input type="password" name="member_pw" placeholder="비밀번호" required>
			<button type="submit">가입</button>
		</form>
		
		<a href="login">로그인</a>
	</div>
	
</article>

</body>
</html>