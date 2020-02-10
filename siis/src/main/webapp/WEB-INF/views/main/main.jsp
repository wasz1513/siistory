<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<style>
	article{
		display: flex; /*바로안에있는것만적용됨*/
        flex-wrap: wrap;
		width:80%;
		margin:auto;
	}
	
	.left-aside{
		background-color: rgb(228, 247, 65);
        width: 20%;
        margin-top:150px;
	}

	section{
		background-color: rgb(38, 233, 184, 0.356);
		width:60%;
		margin-top:150px;
	}

	.right-aside{
		background-color:red;
		flex-grow: 1;
		height:750px;
		margin-top:150px;
	}
	
	.container{
		overflow-y:scroll;
		height:750px;
	}
	
	.container::-webkit-scrollbar{
		display: none;
	}
	
</style>

<article>

	<aside class="left-aside">
	
	</aside>

	<section>
		
		<div class="container">
			
		</div>
		
	</section>

	<aside class="right-aside">
		<div class="container">
		</div>
	</aside>
	
</article>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
