<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<script>

$(function(){
	
	$(".validate-form").hide();
	$(".changePw-form").hide();
	
	$("#exampleInputEmail1").on("blur", function(){  
        var email = $("#exampleInputEmail1").val();
        $.ajax({                                                          
            url:"idcheck?email="+email,
            type:"get",
            success:function(data){ 	
            	var emailCheck = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
            	if(!emailCheck.test(email)){
            		$("#exampleInputEmail1").next().text("이메일 주소를 확인해주세요");
            		$("#send-cert-button").prop("disabled", true);
            	}else{
            		if(data==1){
            			$("#exampleInputEmail1").next().text("");
                        $("#send-cert-button").prop("disabled", false);
                    }
                    else{
                       	$("#exampleInputEmail1").next().text("회원 이메일이 아닙니다. 확인해주세요");
                       	$("#send-cert-button").prop("disabled", true);
                    }	
            	}
            }
        });
    });
	
	$(".email-form").submit(function(e){
		e.preventDefault();
		
		$(this).find("#send-cert-button").prop("disabled",true);
		$(this).find("#send-cert-button-span").text("인증번호 발송중");
		
		var url = $(this).attr("action");
		var method = $(this).attr("method");
		var data = $(this).serialize();
		
		$.ajax({
			url: url,
			type: method,
			data: data, 
			success:function(resp){
				if(resp=="success"){
					$(".validate-form").slideDown();
				}
			}
		
		});
		
		var email = $("#exampleInputEmail1").val();
		$("#inputEmail").val(email);
	});

	$(".validate-form").submit(function(e){
		e.preventDefault();
		
		var url = $(this).attr("action");
		var method = $(this).attr("method");
		var data = $(this).serialize();
		
		$.ajax({
			url: url,
			type: method,
			data: data, 
			success:function(resp){
				if(resp=="success"){
					$(".changePw-form").slideDown();
					$("#changePw-button").prop("disabled",false);
				}
			}
		});
		
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
	<section>
	
		<div class="container">
			<div class="row">
				<div class="col-md">
					<div>
						<form action="getCert" method="get" class="email-form">
							<div class="form-group">
								<img src="${pageContext.request.contextPath}/resources/image/butterfly.svg" width="100%" height="200">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">Email address</label>
						    	<input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" name="email" required>
								<small class="form-text text-muted" id="email-check-span"></small>
							</div>
							<div class="form-group form-button">
				                <input type="submit" class="btn btn-outline-success btn-block" id="send-cert-button" value="인증번호 전송">
				           		<small class="form-text text-muted" id="send-cert-button-span"></small>
				            </div>
						</form>
					</div>
					<div>
						<form action="validate" method="get" class="validate-form">
							<div class="form-group">
								<input type="text" class="form-control" name="cert" placeholder="인증번호" required>
								<small class="form-text text-muted" id="check-cert-button-span">인증번호를 입력하세요</small>
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-outline-success btn-block" id="check-cert-button">인증번호 확인</button>
							</div>
						</form>
					</div>
					<div>
						<form action="changePw" method="get" class="changePw-form">
							<input type="hidden" name="email" id="inputEmail">
							<button type="submit" class="btn btn-outline-success btn-block" id="changePw-button" disabled>비밀번호 변경하기</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		
	</section>
</article>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>