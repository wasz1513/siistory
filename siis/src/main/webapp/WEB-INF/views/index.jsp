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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/crypto/crypto-js.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/crypto/hmac-sha256.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/sha256.js"></script>
<style>
	header{
		height:200px;
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
<script>       
       
        $(function(){
                $("#exampleInputEmail1").on("blur", function(){  
                var email = $("#exampleInputEmail1").val();
                $.ajax({                                                          
                    url:"idcheck?email="+email,
                    type:"get",
                    success:function(data){ 	
                    	var emailCheck = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
                    	if(!emailCheck.test(email)){
                    		$("#exampleInputEmail1").next().text("이메일 주소를 확인해주세요");
                    		$("#registSubmit").prop("disabled", true);
                    	}else{
                    		if(data==1){
                                $("#exampleInputEmail1").next().text("이미 사용중인 아이디 입니다");
                                $("#registSubmit").prop("disabled", true);
                            }
                            else{
                               	$("#exampleInputEmail1").next().text("사용할 수 있는 아이디 입니다");
                               	$("#registSubmit").prop("disabled", false);
                            }	
                    	}
                    }
                });
            });
        });
        
        $(function(){
        	$("#exampleInputPassword1").on("blur", function(){
				var password = 	$("#exampleInputPassword1").val();
				var passwordCheck = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
				if(!passwordCheck.test(password)){
					$("#exampleInputPassword1").next().text("비밀번호를 확인해주세요");
					$("#registSubmit").prop("disabled", true);
				}else{
					$("#exampleInputPassword1").next().text("");
					$("#registSubmit").prop("disabled", false);
				}
        	});
        });

    </script>
</head>
<body>

<header>
</header>

<article>
	
<div class="container">
    <div class="row">
            
            <!-- 	이미지 -->
       
        <div class="col-md">
            <img src="${pageContext.request.contextPath}/resources/image/butterfly.svg" class="img">
        </div>
        
        <!-- 	회원가입 / 로그인  -->
        <div class="col-md">
        	<div>
	            <form action="./" method="post">
	            	<div class="form-group">
				      <label for="exampleInputEmail1">Email address</label>
				      <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" name="email" required>
				      <small class="form-text text-muted"></small>
				      <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
				    </div>
	                <div class="form-group">
						<label for="inputDefault">User name</label>
	                    <input type="text" name="member_name" placeholder="User name" required class="form-control" id="inputDefault">
	                </div>
	                <div class="form-group">
				      <label for="exampleInputPassword1">Password</label>
				      <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="member_pw" required>
				      <small class="form-text text-muted"></small>
				      <small id="passwordHelp" class="form-text text-muted">최소 8 자, 대문자 하나 이상, 소문자 하나, 숫자 하나 및 특수 문자 하나 이상</small>
				    </div>
	                <div class="form-group">
	                    <button type="submit" class="btn btn-outline-success btn-block" id="registSubmit">가입</button>
	                </div>
	                <div class="form-group">
                        <small id="terms" class="form-text text-muted">
                           	 가입하면 SiiStory의 <a href="https://www.kisa.or.kr/main.jsp">약관, 데이터정책, 및 쿠키 정책</a>에 동의하게 됩니다.
                        </small>
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