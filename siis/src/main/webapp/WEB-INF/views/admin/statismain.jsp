<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<style>
	article{
		display: flex; /*바로안에있는것만적용됨*/
        flex-wrap: wrap;
		width:80%;
		margin:auto;
	}
	
	.left-aside{
/* 		background-color: EBFBFF; */
        width: 20%;
        margin-top:150px;
	}

	section{
/* 		background-color: C8FFFF; */
		flex-grow:1;
		margin-top:150px;
	}
</style>

<article>

	<aside class="left-aside">	
		<jsp:include page="/WEB-INF/views/template/adminmenu.jsp"></jsp:include>
	</aside>
	
	<section>
		<div class="container">
			<div class="row">
				<div class="col-md">
					방문 통계
				</div>
				<div class="col-md">
					가입자 통계				
				</div>
				<div class="col-md">
					게시글 통계
				</div>
			</div>
			<div class="row">
				ddd
			</div>
		</div>
	</section>
	
</article>



<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>