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
	header{
		height:100px;
	}
	
	article{
		width:60%;
        text-align:center;
        align-items: center;
        margin:auto;
	}
    
    footer{
        height:100px;
    }
	
    .img{
        width:100%;
        height: 100%;
    }
	
	
</style>


</head>
<body>

<header>
</header>

<article>
	
<div class="container">
    <div class="row">
            
            <!-- 	이미지 -->
       
        <div class="col-md">
            <img src="https://via.placeholder.com/150" class="img">
        </div>
        
        <!-- 	회원가입 / 로그인  -->
        <div class="col-md">
        	<div>
	            <form action="./" method="post">
	            	<div class="form-group">
				      <label for="exampleInputEmail1">Email address</label>
				      <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" name="email" required>
				      <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
				    </div>
	                <div class="form-group">
						<label for="inputDefault">User name</label>
	                    <input type="text" name="member_name" placeholder="User name" required class="form-control" id="inputDefault">
	                </div>
	                <div class="form-group">
				      <label for="exampleInputPassword1">Password</label>
				      <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="member_pw" required>
				    </div>
	                <div class="form-group">
	                    <button type="submit" class="btn btn-outline-success btn-block">가입</button>
	                </div>
	            </form>
        	</div>
            <div>
                <a href="login"><button class="btn btn-outline-info btn-block">로그인</button></a>
            </div>
        </div>       
    </div>
</div>
	
</article>

<footer>

</footer>

</body>
</html>