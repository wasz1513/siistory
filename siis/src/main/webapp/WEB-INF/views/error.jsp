<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<style>
	article{
		width:50%;
        text-align:center;
        align-items: center;
        margin:auto;
        margin-top:100px;
	}
	
	.img{
		width:100%;
		height:500px;
	}
</style>



<article>
	<section>
	
		<div class="container">
    		<div class="row">
            	<c:choose>
					<c:when test="${errorcode==403}">
						<div class="col-md">
				            <img src="${pageContext.request.contextPath}/resources/image/403.jpg" class="img">
				        </div>
					</c:when>
					<c:when test="${errorcode==404}">
						<div class="col-md">
				            <img src="${pageContext.request.contextPath}/resources/image/404.jpg" class="img">
				        </div>
					</c:when>
					<c:when test="${errorcode==405}">
						<div class="col-md">
				            <img src="${pageContext.request.contextPath}/resources/image/405.jpg" class="img">
				        </div>
					</c:when>
					<c:otherwise>
						<div class="col-md">
				            <img src="${pageContext.request.contextPath}/resources/image/500.png" class="img">
				        </div>
					</c:otherwise>
				</c:choose>
       
		        
			</div>
		</div>
		

	</section>
</article>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>